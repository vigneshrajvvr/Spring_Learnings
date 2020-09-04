package com.practice.vvr.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DriverFull {
	public static void main(String args[]) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			StudentFull studentFull = mapper.readValue(new File("data/sample-full.json"), StudentFull.class);
			System.out.println(studentFull.getId());
			System.out.println(studentFull.getFirstName());
			System.out.println(studentFull.getLastName());
			System.out.println(studentFull.getAddress().toString());
			System.out.println("Languages : ");
			for(String language : studentFull.getLanguages()) {
				System.out.println(language);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
				
	}
}
