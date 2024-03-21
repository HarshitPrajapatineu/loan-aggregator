package com.quikloan.server.service;

import java.util.Date;
import java.util.Map;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.aspectj.apache.bcel.classfile.Module.Require;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quikloan.server.dto.*;
import com.quikloan.server.entity.Loan;
import com.quikloan.server.repository.LoanRepository;


@Service
public class LoanService {
	private Connection connection;

	@Autowired
	private LoanRepository loanRepository;
	
	public static float weight_interestrate = -10;
	public static float weight_ratehold = 3;
	public static float weight_prepayment = 6;
	public static float weight_paymentincrease = 5;
	public static float weight_loantovalue = 6;
	public static float weight_preapproval = 8;
	public static float weight_isfixed = 4;

	public ArrayList<Loan> getAllLoans() {
		ArrayList<Loan> loans = new ArrayList<>();
		loanRepository.findAll().forEach(loans::add);
		return loans;
	}

	public ArrayList<Loan> getAllLoansByLoantype(String loantype) {
		ArrayList<Loan> loans = new ArrayList<>();
		if (loantype == null) {
			loanRepository.findAll().forEach(loans::add);
		} else {
			loanRepository.findByLoantype(loantype).forEach(loans::add);
		}
		return loans;
	}
	
	public ArrayList<CalculatedLoanDTO> sortLoans(
			ArrayList<Loan> scotiaObject, 
			ArrayList<Loan> tdObject, 
			ArrayList<Loan> cibcObject, 
			ArrayList<Loan> rbcObject, 
			float amount, 
			float duration){
		
		ArrayList<CalculatedLoanDTO> totalListings = new ArrayList<CalculatedLoanDTO>();
		if(scotiaObject != null) {
			for (Loan loan : scotiaObject) {
				CalculatedLoanDTO newLoanData = new CalculatedLoanDTO(loan);
				newLoanData.setWeight(calculateLoanweight(loan));
				newLoanData.setMonthlypayment(calculateMonthlyPayment(amount, loan.interestrate, duration));
				newLoanData.setProvider("scotia");
				totalListings.add(newLoanData);
			}
		}if(tdObject != null) {
			for (Loan loan : tdObject) {
				CalculatedLoanDTO newLoanData = new CalculatedLoanDTO(loan);
				newLoanData.setWeight(calculateLoanweight(loan));
				newLoanData.setMonthlypayment(calculateMonthlyPayment(amount, loan.interestrate, duration));
				newLoanData.setProvider("td");
				totalListings.add(newLoanData);
			}
		}if(cibcObject != null) {
			for (Loan loan : cibcObject) {
				CalculatedLoanDTO newLoanData = new CalculatedLoanDTO(loan);
				newLoanData.setWeight(calculateLoanweight(loan));
				newLoanData.setMonthlypayment(calculateMonthlyPayment(amount, loan.interestrate, duration));
				newLoanData.setProvider("cibc");
				totalListings.add(newLoanData);
			}
		}if(rbcObject != null) {
			for (Loan loan : rbcObject) {
				CalculatedLoanDTO newLoanData = new CalculatedLoanDTO(loan);
				newLoanData.setWeight(calculateLoanweight(loan));
				newLoanData.setMonthlypayment(calculateMonthlyPayment(amount, loan.interestrate, duration));
				newLoanData.setProvider("rbc");
				totalListings.add(newLoanData);
			}
		}
		
		Collections.sort(totalListings, new Comparator<CalculatedLoanDTO>() {
			@Override
		 	public int compare(CalculatedLoanDTO result2, CalculatedLoanDTO result1) {
		        return Float.valueOf(result1.getWeight()).compareTo(result2.getWeight());
		    }
		});
//		Collections.sort(totalListings, Collections.reverseOrder());
		
		return totalListings;
	}
	
	public double calculateMonthlyPayment(double amount, double interestrate, double duration) {
		
		try
		{

			URL url = new URL("https://api.api-ninjas.com/v1/mortgagecalculator?loan_amount=" +
					amount + "&interest_rate=" + interestrate +"&duration_years=" + duration);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("X-Api-Key", "esWP6Da54MiGa7tzr86iHg==VA9DWQ8BhVnT0FXA");
			InputStream responseStream = connection.getInputStream();

			ObjectMapper mapper = new ObjectMapper();

			JsonNode root = mapper.readTree(responseStream);
			JsonNode mp = root.get("monthly_payment");
			Double total = mp.get("total").asDouble();
			return total;

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return 0;
		}
	}
	
	private float calculateLoanweight(Loan loan) {
		return loan.getInterestrate() * weight_interestrate +
			loan.getRatehold() * weight_ratehold +
			loan.getPrepayment() * weight_prepayment +
			loan.getPaymentincrease() * weight_paymentincrease +
			loan.getLoantovalue() * weight_loantovalue +
			loan.getPreapproval() * weight_preapproval +
			loan.getIsfixed() * weight_isfixed;
	}

	private int getNextId() {
		ArrayList<Loan> loans = getAllLoans();
		return loans.get(loans.size() - 1).getId() + 1;
	}

	public int getNextLoanId() {
		ArrayList<Loan> loans = getAllLoans();
		int currentSize = loans.size();
		int finalId;
		if (currentSize == 0) {
			finalId = 1;
		} else {
			finalId = loans.get(loans.size() - 1).getId() + 1;
		}
		return finalId;
	}

	public Loan insertLoan(LoanDTO loanDTO) {
		Loan loan = new Loan();
		loan.setName(loanDTO.getName());
		loan.setDescription(loanDTO.getDescription());
		loan.setReferencenumber(loanDTO.getReferencenumber());
		loan.setInterestrate(loanDTO.getInterestrate());
		loan.setStatus(loanDTO.getStatus());
		loan.setRequirements(loanDTO.getRequirements());
		loan.setRatehold(loanDTO.getRatehold());
		loan.setPrepayment(loanDTO.getPrepayment());
		loan.setPaymentincrease(loanDTO.getPaymentincrease());
		loan.setLoantovalue(loanDTO.getLoantovalue());
		loan.setPreapproval(loanDTO.getPreapproval());
		loan.setLoantype(loanDTO.getLoantype());
		loan.setIsfixed(loanDTO.getIsFixed());
		loan.setTnc(loanDTO.getTnc());
		loan.setDatemodified(loanDTO.getDatemodified());

		Loan savedLoan = null;

		try {
			savedLoan = loanRepository.save(loan);
		} catch (Exception ex) {
			System.out.println("Exception here - " + ex.getMessage());
		}
		return savedLoan;
	}

	public boolean deleteLoanByRepo(String name) {
		Optional<Loan> loan = loanRepository.findByname(name);
		if (loan.isPresent()) {
			loanRepository.delete(loan.get());
			return true;
		} else {
			return false;
		}
	}

	
	public boolean deleteLoan(String refid) 
	{
		boolean flag=false;PreparedStatement pst = null;
		try 
		{
			connection = com.quikloan.server.repository.DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("DELETE from loans "
					+ "WHERE  referencenumber= (?);");
			pst.setString(1, refid);
			int result = pst.executeUpdate();
			if( result > 0) 
			{
				flag=true;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}finally {
			try 
			{
				pst.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return flag;
	}

	public Loan getLoanByRefid(String refid) {
		Optional<Loan> loan = loanRepository.findByReferencenumber(refid);
		if (loan.isPresent()) {
			return loan.get();
		} else 
		{
			return null;
		}
	}

	public Loan updateLoan(LoanDTO loanModel) {
		Optional<Loan> loan = loanRepository.findByReferencenumber(loanModel.getReferencenumber());
		if (loan.isPresent()) {
			Loan _loan = loan.get();
			_loan.setName(loanModel.getName());
			_loan.setDescription(loanModel.getDescription());
			_loan.setReferencenumber(loanModel.getReferencenumber());
			_loan.setInterestrate(loanModel.getInterestrate());
			_loan.setStatus(loanModel.getStatus());
			_loan.setRequirements(loanModel.getRequirements());
			_loan.setRatehold(loanModel.getRatehold());
			_loan.setPrepayment(loanModel.getPrepayment());
			_loan.setPaymentincrease(loanModel.getPaymentincrease());
			_loan.setLoantovalue(loanModel.getLoantovalue());
			_loan.setPreapproval(loanModel.getPreapproval());
			_loan.setLoantype(loanModel.getLoantype());
			_loan.setIsfixed(loanModel.getIsFixed());
			_loan.setTnc(loanModel.getTnc());
			_loan.setDatemodified(new Date());
			_loan = loanRepository.save(_loan);
			return _loan;
		}
		return null;

	}

}
