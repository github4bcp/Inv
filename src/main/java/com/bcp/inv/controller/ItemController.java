package com.bcp.inv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcp.inv.model.Item;

/**
 * Handles requests for the Item service.
 */
@Controller
public class ItemController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	//Map to store employees, ideally we should use database
	private static Map<String, Item> data = new HashMap<String, Item>();
	
	@PostConstruct
	public void init(){
		
		Item value1 = new Item();
		value1.setCode("1");
		value1.setName("Cement");
		value1.setPrice(400f);
		value1.setDiscount(10f);
		value1.setTax(5.051f);
		value1.setLabour(2.00f);
		
		Item value2 = new Item();
		value2.setCode("2");
		value2.setName("Steel");
		value2.setPrice(10.00f);
		value2.setDiscount(.10f);
		value2.setTax(.51f);
		value2.setLabour(.500f);
		
		
		data.put("1", value1);
		data.put("2", value2);
	}
	
	@RequestMapping(value = URIConstants.ITEM, method = RequestMethod.GET)
	public @ResponseBody Item getDummyItem() {
		LOG.info("Start getDummyEmployee");
		Item i = new Item();
		i.setCode("1");
		i.setName("Dummy");
		i.setPrice(100f);
		data.put("1", i);
		return i;
	}
	
	@RequestMapping(value = URIConstants.GET_ITEM, method = RequestMethod.GET)
	public @ResponseBody Item getItem(@PathVariable("id") String itemId) {
		System.out.println("Start getItem. ID="+itemId);
		return data.get(itemId);
	}
	
	@RequestMapping(value = URIConstants.GET_ALL_ITEMS, method = RequestMethod.GET)
	public @ResponseBody List<Item> getAllItems() {
		System.out.println("Start getItem.");
		List<Item> items = new ArrayList<Item>();
		Set<String> itemsIds = data.keySet();
		for(String i : itemsIds){
			items.add(data.get(i));
		}
		return items;
	}
	
	@RequestMapping(value = URIConstants.CREATE_ITEM, method = RequestMethod.POST)
	public @ResponseBody Item createItem(@RequestBody Item item) {
		System.out.println("Start create Item."+item.getCode());
		data.put(item.getCode(), item);
		return item;
	}
	
	@RequestMapping(value = URIConstants.DELETE_ITEM, method = RequestMethod.PUT)
	public @ResponseBody Item deleteItem(@PathVariable("id") int itemId) {
		LOG.info("Start deleteItem.");
		Item emp = data.get(itemId);
		data.remove(itemId);
		return emp;
	}
	
}
