package com.practice.vvr.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.vvr.model.FilteredBean;

@RestController
public class FilterController {
	
	@GetMapping("/filtering")
	public FilteredBean retrieve() {
		return new FilteredBean("Value11", "Value12", "Value13");
	}
	
	@GetMapping("/filtering-list")
	public List<FilteredBean> retrieveList() {
		return Arrays.asList(new FilteredBean("Value11", "Value12", "Value13"),
				                 new FilteredBean("Value21", "Value22", "Value23"));
	}

}
