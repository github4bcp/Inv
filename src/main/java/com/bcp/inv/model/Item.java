package com.bcp.inv.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Item implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;
	
	private String code;
	
	private String name;
	
	private Float price;
	
	private Float labour;
	
	private Float tax;
	
	private Float discount;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}
	

	public Float getLabour() {
		return labour;
	}

	public void setLabour(Float labour) {
		this.labour = labour;
	}

	public Float getTax() {
		return tax;
	}

	public void setTax(Float tax) {
		this.tax = tax;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	
	
	
	
}
