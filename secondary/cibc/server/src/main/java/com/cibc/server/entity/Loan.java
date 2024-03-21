package com.cibc.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

@Entity
@Table(name ="loans")
public class Loan {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	@Column(unique= true)
	public String referencenumber;
	
	@Column
	public String name;
	
	@Column
	public String description;
	
	@Column
	public float interestrate;
	
	@Column
	public String status;
	
	@Column
	public String requirements;
	
	@Column
	public float ratehold;
	
	@Column
	public float prepayment;
	
	@Column
	public float paymentincrease;
	
	@Column
	public float loantovalue;
	
	@Column
	public int preapproval;
	
	@Column
	public int isfixed;
	
	@Column
	public String loantype;
	
	@Column
	public String tnc;
	
	@Column
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
	
}


