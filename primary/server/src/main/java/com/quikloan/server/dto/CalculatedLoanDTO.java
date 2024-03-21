package com.quikloan.server.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import com.quikloan.server.entity.Loan;

public class CalculatedLoanDTO {
	
	public int id;
	
	public String referencenumber;
	
	public String name;
	
	public String description;
	
	public float interestrate;
	
	public String status;
	
	public String requirements;
	
	public float ratehold;
	
	public float prepayment;
	
	public float paymentincrease;
	
	public float loantovalue;
	
	public int preapproval;
	
	public int isfixed;

	public String loantype;
	
	public String tnc;
	
	public Date datemodified;
	
	public double monthlypayment;
	
	public float weight;
	
	public String provider;
	
	public CalculatedLoanDTO() {
		
	}
	
	public CalculatedLoanDTO(Loan loan) {
	    id = loan.id;
	    referencenumber = loan.referencenumber;
		name = loan.name;
		description = loan.description;
		interestrate = loan.interestrate;
		status = loan.status;
		requirements = loan.requirements;
		ratehold = loan.ratehold;
		prepayment = loan.prepayment;
		paymentincrease = loan.paymentincrease;
		loantovalue = loan.loantovalue;
		preapproval = loan.preapproval;
		isfixed = loan.isfixed;
		loantype = loan.loantype;
		tnc = loan.tnc;
		datemodified = loan.datemodified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReferencenumber() {
		return referencenumber;
	}

	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(float interestrate) {
		this.interestrate = interestrate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public float getRatehold() {
		return ratehold;
	}

	public void setRatehold(float ratehold) {
		this.ratehold = ratehold;
	}

	public float getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(float prepayment) {
		this.prepayment = prepayment;
	}

	public float getPaymentincrease() {
		return paymentincrease;
	}

	public void setPaymentincrease(float paymentincrease) {
		this.paymentincrease = paymentincrease;
	}

	public float getLoantovalue() {
		return loantovalue;
	}

	public void setLoantovalue(float loantovalue) {
		this.loantovalue = loantovalue;
	}

	public int getPreapproval() {
		return preapproval;
	}

	public void setPreapproval(int preapproval) {
		this.preapproval = preapproval;
	}

	public int getIsfixed() {
		return isfixed;
	}

	public void setIsfixed(int isfixed) {
		this.isfixed = isfixed;
	}

	public String getLoantype() {
		return loantype;
	}

	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}

	public String getTnc() {
		return tnc;
	}

	public void setTnc(String tnc) {
		this.tnc = tnc;
	}

	public Date getDatemodified() {
		return datemodified;
	}

	public void setDatemodified(Date datemodified) {
		this.datemodified = datemodified;
	}

	public double getMonthlypayment() {
		return monthlypayment;
	}

	public void setMonthlypayment(double monthlypayment) {
		this.monthlypayment = monthlypayment;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	
	
}


