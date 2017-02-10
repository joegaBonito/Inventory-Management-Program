package com.obs.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.obs.excel.FileUpload;
import com.obs.excel.FileUploadValidator;
import com.obs.services.OrdersExcelImportService;
import com.obs.services.UpsService;

@Controller
public class ExcelController {
	
	@Autowired
	private UpsService upsService;
	
	@Autowired  
	private FileUploadValidator fileValidator; 
	
	@Autowired
	private OrdersExcelImportService ordersExcelImportService;
	
	public ExcelController(UpsService upsService, FileUploadValidator fileValidator, OrdersExcelImportService ordersExcelImportService) {
		this.upsService = upsService;
		this.fileValidator = fileValidator;
		this.ordersExcelImportService = ordersExcelImportService;
	}
	
	@RequestMapping("/downloadExcel")
	public ModelAndView excelDownload() {
		return new ModelAndView("ordersExcelExport","upsOrders",upsService.list());
	}
	 
	 @RequestMapping(value="/submitFileUpload", method=RequestMethod.POST)  
	 public String fileUploaded(  
	   @ModelAttribute("uploadedFile") FileUpload uploadedFile,  
	   BindingResult result) {  
	  InputStream inputStream = null;  
	  OutputStream outputStream = null;  
	  
	  // Getting uploaded file  
	  MultipartFile file = uploadedFile.getFile();  
	  fileValidator.validate(uploadedFile, result); 
	  
	  String fileName = file.getOriginalFilename();  
	  
	  // If it has error, redirect it to same page  
	  if (result.hasErrors()) {  
	   return "redirect:/ups/list";  
	  }  
	  
	  try {  
	   inputStream = file.getInputStream();  
	  
	   File newFile = new File("../OBS/src/main/resources/files/" + fileName);  
	   if (!newFile.exists()) {  
	    newFile.createNewFile();  
	   }  
	   outputStream = new FileOutputStream(newFile);  
	   int read = 0;  
	   byte[] bytes = new byte[1024];  
	  
	   while ((read = inputStream.read(bytes)) != -1) {  
	    outputStream.write(bytes, 0, read);  
	   }  
	  } catch (IOException e) {  
	   // TODO Auto-generated catch block  
	   e.printStackTrace();  
	  }  
	  
	  return "redirect:/uploadExcel";
	 }  
	 @RequestMapping("/uploadExcel")  
	 public String getUploadForm() {  
		 ordersExcelImportService.excelImport();
		 return "redirect:/ups/list";  
	 }  
}
