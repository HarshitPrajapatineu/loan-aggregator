package com.scotia.server.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scotia.server.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>{
	
	ArrayList<Loan> findByLoantype(String loantype);
	Optional<Loan> findByname(String name);
	Optional<Loan> findByReferencenumber(String referencenumber);

}
