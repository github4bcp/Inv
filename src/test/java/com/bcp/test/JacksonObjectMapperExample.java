package com.bcp.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.bcp.inv.model.Item;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonObjectMapperExample {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		 //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter str = new StringWriter();
        List<Item> items = new ArrayList<Item>();
        items.add(new Item());
        objectMapper.writeValue(str, items);
        System.out.println("JSON is\n"+str);

	}

}
