package com.example.springboot.filehandling.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	private Logger testLogger = Logger.getLogger("FileUploadController");
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/upload";
	
	@RequestMapping("/")
	public String uploadPage(Model model) {
		return "uploadview";
	}
	
	@RequestMapping("/upload")
	public String uploadModel(Model model, @RequestParam("files") MultipartFile[] files) {
//		testLogger.info(""+files.length+files[0]);
//		testLogger.info(uploadDirectory);
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		model.addAttribute("msg", "Successfully uploaded the files " + fileNames.toString());
		
		return "uploadView";		
	}

}
