package com.obs.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

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
import com.obs.services.AccessoryInventoryService;
import com.obs.services.AccessoryReceivedQuantityService;
import com.obs.services.MasterService;
import com.obs.services.OrdersExcelImportService;
import com.obs.services.PhoneInventoryService;
import com.obs.services.PhonesReceivedQuantityService;
import com.obs.services.UpsService;

@Controller
public class ExcelController {
	
	private UpsService upsService;
	private FileUploadValidator fileValidator; 
	private OrdersExcelImportService ordersExcelImportService;
	private AccessoryInventoryService accessoryInventoryService;
	private PhoneInventoryService phoneInventoryService;
	private MasterService masterService;
	
	@Autowired
	public ExcelController(UpsService upsService, FileUploadValidator fileValidator, OrdersExcelImportService ordersExcelImportService, 
			AccessoryInventoryService accessoryInventoryService, PhoneInventoryService phoneInventoryService, MasterService masterService) {
		this.upsService = upsService;
		this.fileValidator = fileValidator;
		this.ordersExcelImportService = ordersExcelImportService;
		this.accessoryInventoryService = accessoryInventoryService;
		this.phoneInventoryService = phoneInventoryService;
		this.masterService = masterService;
	}
	
	@RequestMapping(value= "/downloadOrderExcel",method=RequestMethod.GET)
	public ModelAndView excelOrderDownload() {
		return new ModelAndView("ordersExcelExport","upsOrders",upsService.list());
	}
	
	@RequestMapping(value="/downloadInventoryExcel",method=RequestMethod.GET)
	public ModelAndView excelInventoryDownload() {
		return new ModelAndView("inventoryExcelExport","masters",masterService.list());
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
	 public String getUploadForm() throws IOException {  
		 ordersExcelImportService.excelImport();
		 accessoryInventoryService.setSalesQuantity();
		 accessoryInventoryService.setSalesAmount();
		 phoneInventoryService.setSalesQuantity();
		 phoneInventoryService.setSalesAmount();
		 return "redirect:/ups/list";  
	 }  
}
