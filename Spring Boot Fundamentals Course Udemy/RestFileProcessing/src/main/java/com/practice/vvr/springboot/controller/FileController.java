package com.practice.vvr.springboot.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
	
	private String UPLOAD_DIR = "E:\\Programming\\Spring\\Resources\\Project Support folders\\Uploads\\";

	@PostMapping("/upload")
	public boolean uploadFiler(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		file.transferTo(new File(UPLOAD_DIR + file.getOriginalFilename()));
		return true;
	}
	
}
