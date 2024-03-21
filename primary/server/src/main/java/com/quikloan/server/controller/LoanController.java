package com.quikloan.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.quikloan.server.service.LoanService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quikloan.server.dto.CalculatedLoanDTO;
import com.quikloan.server.dto.LoanDTO;
import com.quikloan.server.dto.LoanFilter;
import com.quikloan.server.entity.Loan;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/quikloan")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@Autowired
	@Qualifier("eurekaTemplate")
	private RestTemplate restEeurekaTemplate;
	
	@Autowired
	@Qualifier("template")
	private RestTemplate restTemplate;
//	
	@RequestMapping(value= "/getLoanCount", method= RequestMethod.GET)
	public ResponseEntity<String> getAllLoanSize() {
		ArrayList<Loan> loans = loanService.getAllLoans();
		return new ResponseEntity<>(Integer.toString(loans.size()), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getLoans", method= RequestMethod.POST)
	public ResponseEntity<ArrayList<CalculatedLoanDTO>> getAllLoans(@RequestBody LoanFilter filter) {

//		ResponseEntity<Object> scotiaObject = 
//				restEeurekaTemplate.exchange("http://scotia-server/scotia/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        if(scotiaObject == null) {
//        	scotiaObject = 
//    				restTemplate.exchange("http://localhost:8082/scotia/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        }
//        
//
//        ResponseEntity<Object> tdObject = 
//				restEeurekaTemplate.exchange("http://td-server/td/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        if(tdObject == null) {
//        	tdObject = 
//    				restTemplate.exchange("http://localhost:8083/td/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        }
//
//        ResponseEntity<Object> cibcObject = 
//				restEeurekaTemplate.exchange("http://cibc-server/cibc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        if(cibcObject == null) {
//        	cibcObject = 
//    				restTemplate.exchange("http://localhost:8084/cibc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        }
//
//        ResponseEntity<Object> rbcObject = 
//				restEeurekaTemplate.exchange("http://rbc-server/rbc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        if(rbcObject == null) {
//        	rbcObject = 
//    				restTemplate.exchange("http://localhost:8085/rbc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
//        }

//		ObjectMapper mapper = new ObjectMapper();
////		ArrayList<Loan> newList = mapper.convertValue(loans, new TypeReference<ArrayList<Loan>>() { });
//        ArrayList<Loan> scotia = mapper.convertValue((ArrayList<Loan>) scotiaObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
//		ArrayList<Loan> td = mapper.convertValue((ArrayList<Loan>) tdObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
//		ArrayList<Loan> cibc = mapper.convertValue((ArrayList<Loan>) cibcObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
//		ArrayList<Loan> rbc = mapper.convertValue((ArrayList<Loan>) rbcObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
		
		ArrayList<Loan> scotia = getScotiaLoans(filter);
		ArrayList<Loan> td = getTdLoans(filter);
		ArrayList<Loan> cibc = getCibcLoans(filter);
		ArrayList<Loan> rbc = getRbcLoans(filter);
        
        ArrayList<CalculatedLoanDTO> loans = loanService.sortLoans( scotia, td, cibc, rbc, filter.getAmount(), filter.getDuration());
        
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}
	

	public ArrayList<Loan> getScotiaLoans(@RequestBody LoanFilter filter) {
		ArrayList<Loan> scotia = new ArrayList<>();
		try {
			ResponseEntity<Object> scotiaObject = 
					restEeurekaTemplate.exchange("http://scotia-server/scotia/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        if(scotiaObject == null) {
	        	scotiaObject = 
	    				restTemplate.exchange("http://localhost:8082/scotia/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        }
	        
			ObjectMapper mapper = new ObjectMapper();
//			ArrayList<Loan> newList = mapper.convertValue(loans, new TypeReference<ArrayList<Loan>>() { });
	        scotia = mapper.convertValue((ArrayList<Loan>) scotiaObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return scotia;
	}
	

	public ArrayList<Loan> getTdLoans(@RequestBody LoanFilter filter) {
		ArrayList<Loan> td = new ArrayList<>();
		try {
			ResponseEntity<Object> tdObject = 
					restEeurekaTemplate.exchange("http://td-server/td/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        if(tdObject == null) {
	        	tdObject = 
	    				restTemplate.exchange("http://localhost:8083/td/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        }
	        
			ObjectMapper mapper = new ObjectMapper();
//			ArrayList<Loan> newList = mapper.convertValue(loans, new TypeReference<ArrayList<Loan>>() { });
			td = mapper.convertValue((ArrayList<Loan>) tdObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return td;
	}
	

	public ArrayList<Loan> getCibcLoans(@RequestBody LoanFilter filter) {
		ArrayList<Loan> cibc = new ArrayList<>();
		try {
			ResponseEntity<Object> cibcObject = 
					restEeurekaTemplate.exchange("http://cibc-server/cibc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        if(cibcObject == null) {
	        	cibcObject = 
	    				restTemplate.exchange("http://localhost:8084/cibc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        }
	        
			ObjectMapper mapper = new ObjectMapper();
//			ArrayList<Loan> newList = mapper.convertValue(loans, new TypeReference<ArrayList<Loan>>() { });
			cibc = mapper.convertValue((ArrayList<Loan>) cibcObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return cibc;
	}
	

	public ArrayList<Loan> getRbcLoans(@RequestBody LoanFilter filter) {
		ArrayList<Loan> rbc = new ArrayList<>();
		try {
			ResponseEntity<Object> rbcObject = 
					restEeurekaTemplate.exchange("http://rbc-server/rbc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        if(rbcObject == null) {
	        	rbcObject = 
	    				restTemplate.exchange("http://localhost:8085/rbc/getLoans/" + filter.getLoantype(), HttpMethod.GET, null, Object.class);
	        }
	        
			ObjectMapper mapper = new ObjectMapper();
//			ArrayList<Loan> newList = mapper.convertValue(loans, new TypeReference<ArrayList<Loan>>() { });
			rbc = mapper.convertValue((ArrayList<Loan>) rbcObject.getBody(), new TypeReference<ArrayList<Loan>>() { });
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return rbc;
	}

	@RequestMapping(value= "/test", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Loan>> test() {
		
		try
		{
			ResponseEntity<Object> cibcObject = 
					restEeurekaTemplate.exchange("http://cibc-server/cibc/getLoans/", HttpMethod.GET, null, Object.class);
//			System.out.println(cibcObject.getBody());
			ArrayList<Loan> loans = (ArrayList<Loan>) cibcObject.getBody();
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Loan> newList = mapper.convertValue(loans, new TypeReference<ArrayList<Loan>>() { });
			return new ResponseEntity<>(newList, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value= "/getLoans/{loantype}", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Loan>> getAllLoansByRole(@PathVariable String loantype) {
		try
		{
			ArrayList<Loan> loans = loanService.getAllLoansByLoantype(loantype);
			return new ResponseEntity<>(loans, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
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
