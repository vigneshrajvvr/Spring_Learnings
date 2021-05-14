package com.practice.vvr.springboot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RestFileProcessingApplicationTests {
	
	private static final String DOWNLOAD_URL = "http://localhost:8080/download/";
	private static final String DOWNLOAD_PATH = "E:\\Programming\\Spring\\Resources\\Project Support folders\\Downloads\\";
	private static final String UPLOAD_URL = "http://localhost:8080/upload";
	@Autowired
	RestTemplate restTemplate;

	@Test
	void testUpload() {
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new ClassPathResource("Capture.PNG"));

		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, header);
		
//		restTemplate.postForEntity(url, request, responseType)
		ResponseEntity<Boolean> request = restTemplate.postForEntity(UPLOAD_URL, httpEntity, Boolean.class);
		
		System.out.println(request.getBody());
		
	}
	
	@Test
	void testDowload() throws IOException {
		
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
		
		HttpEntity<String> httpEntity = new HttpEntity<>(httpHeader);
		
		String fileName = "Capture.PNG";
		
		ResponseEntity<byte[]> request = restTemplate.exchange(DOWNLOAD_URL + fileName, HttpMethod.GET, httpEntity, byte[].class);
		
		Files.write(Paths.get(DOWNLOAD_PATH + fileName), request.getBody());
	}

}
