package com.bcp.inv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BilledInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Float total;

	private Date date;
		
	private Customer customer;
	
	private List<BilledItem> items;
	
	
	
	
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<BilledItem> getItems() {
		return items;
	}

	public void setItems(List<BilledItem> items) {
		this.items = items;
	}
	
	
	
	
}
