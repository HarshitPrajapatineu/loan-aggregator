package com.cibc.server.service;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibc.server.dto.LoanDTO;
import com.cibc.server.dto.UserDTO;
import com.cibc.server.entity.Loan;
import com.cibc.server.repository.DatabaseConnector;
import com.cibc.server.repository.LoanRepository;


@Service
public class LoanService {
	private Connection connection;

	@Autowired
	private LoanRepository loanRepository;

	public ArrayList<Loan> getAllLoans() {
		ArrayList<Loan> loans = new ArrayList<>();
		loanRepository.findAll().forEach(loans::add);
		return loans;
	}

	public ArrayList<Loan> getAllLoansByLoantype(String loantype) {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		PreparedStatement pst = null;
		try 
		{
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("SELECT * FROM loans WHERE loantype = (?) AND status = (?)");
			pst.setString(1, loantype);
			pst.setString(2, "active");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Loan loan = new Loan();
				loan.setId(Integer.parseInt(rs.getString("id")));
				loan.setReferencenumber(rs.getString("referencenumber"));
				loan.setName(rs.getString("name"));
				loan.setDescription(rs.getString("description"));
				loan.setInterestrate(Float.parseFloat(rs.getString("interestrate")));
				loan.setStatus(rs.getString("status"));
				loan.setRequirements(rs.getString("requirements"));
				loan.setRatehold(Float.parseFloat(rs.getString("ratehold")));
				loan.setPrepayment(Float.parseFloat(rs.getString("prepayment")));
				loan.setPaymentincrease(Float.parseFloat(rs.getString("paymentincrease")));
				loan.setLoantovalue(Float.parseFloat(rs.getString("loantovalue")));
				loan.setPreapproval(Integer.parseInt(rs.getString("preapproval")));
				loan.setLoantype(rs.getString("loantype"));
				loan.setIsfixed(Integer.parseInt(rs.getString("isfixed")));
				loan.setTnc(rs.getString("tnc"));
				loan.setDatemodified(rs.getDate("datemodified"));
				
				loans.add(loan);
			}
			return loans;
		} catch (Exception e) 
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
		return loans;
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
			connection = com.cibc.server.repository.DatabaseConnector.getInstance().getConnection();
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
