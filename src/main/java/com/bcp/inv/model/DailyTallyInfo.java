package com.bcp.inv.model;

import java.io.Serializable;
import java.util.List;

public class DailyTallyInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PaymentInfo> paymentInfos;
	
	private List<BilledInfo> bills;

	


	public List<PaymentInfo> getPaymentInfos() {
		return paymentInfos;
	}

	public void setPaymentInfos(List<PaymentInfo> paymentInfos) {
		this.paymentInfos = paymentInfos;
	}

	public List<BilledInfo> getBills() {
		return bills;
	}

	public void setBills(List<BilledInfo> bills) {
		this.bills = bills;
	}
	
	
	
	
}
