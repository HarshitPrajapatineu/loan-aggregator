package com.rbc.server.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.rbc.server.service.LoanService;
import com.rbc.server.dto.LoanDTO;
import com.rbc.server.entity.Loan;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/rbc")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@RequestMapping(value= "/getLoanCount", method= RequestMethod.GET)
	public ResponseEntity<String> getAllLoanSize() {
		ArrayList<Loan> loans = loanService.getAllLoans();
		return new ResponseEntity<>(Integer.toString(loans.size()), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getLoans", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Loan>> getAllLoans() {
		ArrayList<Loan> loans = loanService.getAllLoans();
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getLoans/{loantype}", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Loan>> getAllLoansByRole(@PathVariable String loantype) {
		ArrayList<Loan> loans = loanService.getAllLoansByLoantype(loantype);
		return new ResponseEntity<>(loans, HttpStatus.OK);

	}
	
	@RequestMapping(value= "/addListing", method= RequestMethod.POST)
	public ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loanDTO) {
		Loan loans = loanService.insertLoan(loanDTO);
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/deleteLoan/{refid}", method= RequestMethod.GET)
	public ResponseEntity<Boolean> deleteLoan(@PathVariable String refid){
		boolean isDeleted = loanService.deleteLoan(refid);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getListing/{refid}", method= RequestMethod.GET)
	public ResponseEntity<Loan> getLoanByRefid(@PathVariable String refid){
		Loan loan = loanService.getLoanByRefid(refid);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/updateListing", method= RequestMethod.POST)
	public ResponseEntity<Loan> updateListing(@RequestBody LoanDTO loanModel){
		Loan loan = loanService.updateLoan(loanModel);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}
	
}
