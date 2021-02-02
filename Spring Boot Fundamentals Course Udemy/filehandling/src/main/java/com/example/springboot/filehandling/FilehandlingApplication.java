package com.example.springboot.filehandling;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springboot.filehandling.controller.FileUploadController;

@SpringBootApplication
public class FilehandlingApplication {

	public static void main(String[] args) {
		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(FilehandlingApplication.class, args);
	}

}
