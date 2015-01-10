package com.bcp.inv.model;

import java.io.Serializable;
import java.util.Date;

public class PaymentInfo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean debit;
	
	private String text;
	
	private Date date;
	
	private Float amount;

		
	public boolean isDebit() {
		return debit;
	}

	public void setDebit(boolean debit) {
		this.debit = debit;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
	
}
