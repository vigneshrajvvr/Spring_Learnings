package com.practice.vvr.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practice.vvr.springboot.dao.FileUploadResponse;
import com.practice.vvr.springboot.service.FileStorageService;

@RestController
public class UploadDownloadWithFileSystemController {
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("single/upload")
	FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		
		// fromCurrentContextPath : http://localhost:8081
		// /download
		// /filename.extenstion
		// http://localhost:8081/download/abc.jpg
		String url = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/download")
					.path(fileName)
					.toUriString();
		
		String contentType = file.getContentType();
		
		FileUploadResponse response = new FileUploadResponse(fileName, contentType, url);
		
		return response;
	}
	
	@GetMapping("/download/{fileName}")
	ResponseEntity<Resource> downloadSingleFile(@PathVariable String fileName) {
		Resource resource = fileStorageService.downloadFile(fileName);
				
		MediaType contentType = MediaType.IMAGE_JPEG;
		
		return ResponseEntity.ok().contentType(contentType)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+resource.getFilename())
				.body(resource);
	}
}
