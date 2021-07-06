package com.practice.vvr.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.practice.vvr.model.FilteredBean;

@RestController
public class FilterController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieve() {
		
		FilteredBean filteredBean = new FilteredBean("Value11", "Value12", "Value13");
	
		SimpleBeanPropertyFilter filterProperty = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		FilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.findPropertyFilter("SomeBeanFilter", filterProperty);
		
		MappingJacksonValue mapping = new MappingJacksonValue(filteredBean);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public List<FilteredBean> retrieveList() {
		return Arrays.asList(new FilteredBean("Value11", "Value12", "Value13"),
				                 new FilteredBean("Value21", "Value22", "Value23"));
	}

}
