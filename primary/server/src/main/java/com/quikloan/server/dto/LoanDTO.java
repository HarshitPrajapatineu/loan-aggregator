package com.quikloan.server.dto;

import java.util.Date;

import javax.persistence.Column;

public class LoanDTO {
	
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
	public int getIsFixed() {
		return isfixed;
	}
	public void setIsFixed(int isfixed) {
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
	
}
