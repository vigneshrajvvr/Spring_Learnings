package com.practice.vvr.springboot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {
	
	@JmsListener(destination="${springjms.myQueue}")
	public void receive(String message) {
		System.out.println("Message receive ==> " +message);
	}

}
