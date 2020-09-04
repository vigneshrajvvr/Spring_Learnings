package com.practice.vvr.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			//create object mapper 
			ObjectMapper mapper = new ObjectMapper();
			
			//read JSON file and map/convert to Java POJO:
			//data/sample-lite.json
			
			Student student1 = mapper.readValue(
					new File("data/sample-lite.json"), Student.class);
			
			//print student data
			System.out.println(student1.getId());
			System.out.println(student1.getFirstName());
			System.out.println(student1.getLastName());			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
