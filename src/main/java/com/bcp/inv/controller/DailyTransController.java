package com.bcp.inv.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcp.inv.model.PaymentInfo;

@Controller
public class DailyTransController {
	
	public static List<PaymentInfo> pay = new ArrayList<PaymentInfo>();
	
	
	@PostConstruct
	public void init(){

		PaymentInfo p =  new PaymentInfo();
		p.setAmount(1000f);
		p.setText("Personal");
		
		PaymentInfo p2 =  new PaymentInfo();
		p2.setAmount(22000f);
		p2.setText("Bank");
		
		PaymentInfo p3 =  new PaymentInfo();
		p3.setAmount(22000f);
		p3.setText("CUST-100");
		
		pay.add(p);
		pay.add(p2);
		pay.add(p3);
	
	}

	@RequestMapping(value = URIConstants.CREATE_EXPENSE, method = RequestMethod.POST)
	public @ResponseBody PaymentInfo saveExpense(@RequestBody PaymentInfo payments) {
		System.out.println("Start save .");
		
		PaymentInfo saved = null;
		for(final PaymentInfo p : pay){
			if(p.getText().equals(payments.getText())){
				saved = p;
			}
		}
		if(null!=saved){
			saved.setAmount(payments.getAmount());
		}else{
			pay.add(payments);
		}
				
		return payments;
	}
	
	@RequestMapping(value = URIConstants.GET_ALL_EXPENSE, method = RequestMethod.GET)
	public @ResponseBody List<PaymentInfo> getAllItems() {
		System.out.println("Start getItem.");
		return pay;
	}
	
	@RequestMapping(value = URIConstants.GET_EXPENSE, method = RequestMethod.GET)
	public @ResponseBody PaymentInfo getItem(@PathVariable("id") int index) {
		System.out.println("Start getItem.");
		return pay.get(index);
	}
	
	
	
	
}
