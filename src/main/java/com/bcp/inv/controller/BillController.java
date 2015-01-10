package com.bcp.inv.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcp.inv.model.BilledInfo;
import com.bcp.inv.model.BilledItem;
import com.bcp.inv.model.Customer;
import com.bcp.inv.model.Item;

@Controller
public class BillController {

private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	//Map to store employees, ideally we should use database
	public static Map<String, List<BilledInfo>> data = new HashMap<String, List<BilledInfo>>();
	
	@RequestMapping(value = URIConstants.BILL, method = RequestMethod.GET)
	public @ResponseBody BilledInfo getDummyBill() {
		LOG.info("Start Total Info");
		BilledInfo i = new BilledInfo();
		i.setCustomer(new Customer());
		i.setDate(new Date());
		BilledItem billedItem = new BilledItem();
		billedItem.setTotal(100f);
		billedItem.setItem(new Item());
		i.setItems(Arrays.asList(billedItem,billedItem));
		
		data.put("1", Arrays.asList(i));
	
		return i;
	}
	
	@RequestMapping(value = URIConstants.CREATE_BILL, method = RequestMethod.POST)
	public @ResponseBody BilledInfo saveBill(@RequestBody BilledInfo bInfo) {
		System.out.println("Save bill Info");
		
		if(data.get("1")==null){
			List<BilledInfo> bills = new ArrayList<BilledInfo>();
			data.put("1",bills);
		}
		
		data.get("1").add(bInfo);
	
		return bInfo;
	}
}
