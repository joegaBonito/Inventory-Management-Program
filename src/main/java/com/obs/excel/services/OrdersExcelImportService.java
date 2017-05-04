package com.obs.excel.services;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.General.domain.UpsOrder;
import com.obs.General.services.UpsService;

@Service
public class OrdersExcelImportService { 
	
	@Autowired
	private UpsService upsService;
	
	public OrdersExcelImportService(UpsService upsService) {
		this.upsService = upsService;
	}
	
	@SuppressWarnings({ "resource", "unused" })
	public void excelImport(){
	 try
     {
         FileInputStream file = new FileInputStream(new File("../OBS/src/main/resources/files/MOBILE SALES TO DATE - Real Time Cust Trans Dtl.xls"));

         //Create Workbook instance holding reference to .xls file
         HSSFWorkbook workbook = new HSSFWorkbook(file);

         //Get first/desired sheet from the workbook
         Sheet sheet = workbook.getSheetAt(0);
         
         // Create an instance of SimpleDateFormat used for formatting 
         // the string representation of date (month/day/year)
         DateFormat df = new SimpleDateFormat("dd/MMM/yyyy HHmmss");

         //Iterate through each rows one by one
         Iterator<Row> rowIterator = sheet.iterator();
         while (rowIterator.hasNext()) 
         {
             Row row = rowIterator.next();
             if(row.getRowNum()==0){ 
            	   continue; //skip rows
             }
             UpsOrder upsOrder = new UpsOrder();
             
            	 //For each row, iterate through all the columns
            	 upsOrder.setUpsOrderId(row.getCell(4).getStringCellValue());
            	 upsOrder.setUpsOrderReceived(row.getCell(54).getStringCellValue());
            	 upsOrder.setUpsProductId(row.getCell(40).getStringCellValue());
            	 upsOrder.setUpsQuantity((int) row.getCell(42).getNumericCellValue());
            	 upsOrder.setUpsProductName(row.getCell(15).getStringCellValue());
            	 upsOrder.setUpsShippingMethod(row.getCell(56).getStringCellValue());
            	 upsOrder.setUpsFname(row.getCell(18).getStringCellValue());
            	 upsOrder.setUpsLname(row.getCell(19).getStringCellValue());
            	 upsOrder.setAddress1(row.getCell(22).getStringCellValue());
            	 if(row.getCell(23)==null) {
            		 upsOrder.setAddress2(" ");
            	 }
            	 else {
            		 upsOrder.setAddress2(row.getCell(23).getStringCellValue());
            	 }
            	 upsOrder.setCity(row.getCell(24).getStringCellValue());
            	 upsOrder.setState(row.getCell(25).getStringCellValue());
            	 upsOrder.setZip(row.getCell(26).getStringCellValue());
            	 upsOrder.setPhone(row.getCell(28).getStringCellValue());
            	 upsOrder.setResidential(1);
            	 upsOrder.setService(row.getCell(57).getStringCellValue());
            	 upsOrder.setPackageType("CP");
            	 upsOrder.setBillTo("SHP");
            	 upsOrder.setCountry("US");
            	 upsOrder.setCustomerName("LG.COM");
            	 upsService.upsAccSetProductName(upsOrder);
            	 upsService.upsUpSetProductName(upsOrder);
            	 upsService.upsSetService(upsOrder);
            	 upsService.save(upsOrder);
             }
         System.out.println("Done Uploading");
         file.close();
     } 
     catch (Exception e) 
     {
         e.printStackTrace();
     } 
	}
}

