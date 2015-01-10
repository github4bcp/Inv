package com.bcp.inv.model;

import java.io.Serializable;

public class BilledItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	
	private int quantity;
	
	private Float total;
	
	private Item item;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
