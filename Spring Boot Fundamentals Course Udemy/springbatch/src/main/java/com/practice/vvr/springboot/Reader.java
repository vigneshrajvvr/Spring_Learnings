package com.practice.vvr.springboot;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {
	
	private String[] courses = {"Java Web Services", "End to End Project", "Angular"};
	private int count= 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(count > courses.length) {
			count = 0;
		}
		
		String result = courses[count];
		count++;
		
		return result;
	}

}
