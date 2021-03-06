package com.example.springboot.filehandling.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping("/downloadView")
	public String showFiles(Model model) {
		File folder = new File(uploadDirectory);
		File[] listOfFiles = folder.listFiles();
		model.addAttribute("files", listOfFiles);
		return "showfiles";
	}
	
	@RequestMapping("/file/{fileName}")
	@ResponseBody
	public void show(@PathVariable("fileName") String fileName, HttpServletResponse response) {
		if(fileName.indexOf(".doc") > -1) {
			response.setContentType("application/msword");
		}
		if(fileName.indexOf(".docx") > -1) response.setContentType("application/msword");
		if(fileName.indexOf(".xls") > -1) response.setContentType("application/vnd.ms-excel");
		if(fileName.indexOf(".csv") > -1) response.setContentType("application/vnd.ms-excel");
		if(fileName.indexOf(".ppt") > -1) response.setContentType("application/ppt");
		if(fileName.indexOf(".pdf") > -1) response.setContentType("application/pdf");
		if(fileName.indexOf(".zip") > -1) response.setContentType("application/zip");
		if(fileName.indexOf(".jpg") > -1) response.setContentType("image/jpeg");
		if(fileName.indexOf(".PNG") > -1) response.setContentType("image/png");
		response.setHeader("Content-Disposition", "attachment: filename="+fileName);
		response.setHeader("Content-Tranfer-Encoding", "Binary");
		try {
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis = new FileInputStream(uploadDirectory + "\\" + fileName);
			int len;
			byte[] buf = new byte[1024];
			while((len = fis.read(buf)) > 0) {
				bos.write(buf, 0, len);
			}
			bos.close();
			response.flushBuffer();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

}
