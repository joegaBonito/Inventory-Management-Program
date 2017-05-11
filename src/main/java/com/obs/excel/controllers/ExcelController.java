package com.obs.excel.controllers;

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

import com.obs.Accessory.services.impl.AccessoryInventoryServiceImpl;
import com.obs.General.services.impl.UpsServiceImpl;
import com.obs.Master.services.MasterService;
import com.obs.Phones.services.impl.PhoneInventoryServiceImpl;
import com.obs.excel.FileUpload;
import com.obs.excel.FileUploadValidator;
import com.obs.excel.services.impl.OrdersExcelImportServiceImpl;

@Controller
public class ExcelController {
	
	private UpsServiceImpl upsServiceImpl;
	private FileUploadValidator fileValidator; 
	private OrdersExcelImportServiceImpl ordersExcelImportServiceImpl;
	private AccessoryInventoryServiceImpl accessoryInventoryServiceImpl;
	private PhoneInventoryServiceImpl phoneInventoryServiceImpl;
	private MasterService masterService;
	
	@Autowired
	public ExcelController(UpsServiceImpl upsServiceImpl, FileUploadValidator fileValidator, OrdersExcelImportServiceImpl ordersExcelImportServiceImpl, 
			AccessoryInventoryServiceImpl accessoryInventoryServiceImpl, PhoneInventoryServiceImpl phoneInventoryServiceImpl, MasterService masterService) {
		this.upsServiceImpl = upsServiceImpl;
		this.fileValidator = fileValidator;
		this.ordersExcelImportServiceImpl = ordersExcelImportServiceImpl;
		this.accessoryInventoryServiceImpl = accessoryInventoryServiceImpl;
		this.phoneInventoryServiceImpl = phoneInventoryServiceImpl;
		this.masterService = masterService;
	}
	
	@RequestMapping(value= "/downloadOrderExcel",method=RequestMethod.GET)
	public ModelAndView excelOrderDownload() {
		return new ModelAndView("ordersExcelExport","upsOrders",upsServiceImpl.list());
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
	   e.printStackTrace();  
	  }  
	  
	  return "redirect:/uploadExcel";
	 }  
	 @RequestMapping("/uploadExcel")  
	 public String getUploadForm() throws IOException {  
		 ordersExcelImportServiceImpl.excelImport();
		 accessoryInventoryServiceImpl.setSalesQuantity();
		 accessoryInventoryServiceImpl.setSalesAmount();
		 phoneInventoryServiceImpl.setSalesQuantity();
		 phoneInventoryServiceImpl.setSalesAmount();
		 return "redirect:/ups/list";  
	 }  
}
