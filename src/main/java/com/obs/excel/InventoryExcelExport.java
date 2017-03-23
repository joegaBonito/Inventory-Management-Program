package com.obs.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.obs.domain.Master;

@Component
public class InventoryExcelExport extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// change the file name
		String filename="LG.COM Inventory.xls";
        response.setHeader("Content-Disposition", "inline; filename="+filename+";");

        @SuppressWarnings("unchecked")
        List<Master> masters = (List<Master>) model.get("masters");

        // create excel xls sheet
        Sheet sheet3 = workbook.createSheet("Master Inventory List");
        Sheet sheet1 = workbook.createSheet("Mobile Accessory Inventory List");
        Sheet sheet2 = workbook.createSheet("Unlocked Phone Inventory List");
        
     // create sheet1 header row
        Row header1 = sheet1.createRow(0);
        header1.createCell(0).setCellValue("LG.COM Product ID");
        header1.createCell(1).setCellValue("Product Name");
        header1.createCell(2).setCellValue("Product Price");
        header1.createCell(3).setCellValue("Sales Price");
        header1.createCell(4).setCellValue("Purchased Amount");
        header1.createCell(5).setCellValue("Purchased Quantity");
        header1.createCell(6).setCellValue("Sales Amount");
        header1.createCell(7).setCellValue("Sales Quantity");
        header1.createCell(8).setCellValue("Inventory Amount");
        header1.createCell(9).setCellValue("Current Inventory");

        // Create data cells for sheet1
        int rowCount1 = 1;
        for (Master master : masters){
        	if(master.getPhone() == null) {
            Row courseRow = sheet1.createRow(rowCount1++);
            courseRow.createCell(0).setCellValue(master.getAccessory().getProductId());
            courseRow.createCell(1).setCellValue(master.getAccessory().getProductName());
            courseRow.createCell(2).setCellValue(master.getAccessory().getPurchasePrice());
            courseRow.createCell(3).setCellValue(master.getAccessory().getSalesPrice());
            courseRow.createCell(4).setCellValue(master.getAccessory().getAccessoryInventory().getTotalPurchasedAmount());
            courseRow.createCell(5).setCellValue(master.getAccessory().getAccessoryInventory().getTotalPurchasedQuantity());
            courseRow.createCell(6).setCellValue(master.getAccessory().getAccessoryInventory().getSalesAmount());
            courseRow.createCell(7).setCellValue(master.getAccessory().getAccessoryInventory().getSalesQuantity());
            courseRow.createCell(8).setCellValue(master.getAccessory().getAccessoryInventory().getCurrentInventoryAmount());
            courseRow.createCell(9).setCellValue(master.getAccessory().getAccessoryInventory().getCurrentInventory());
        	}
        }

     // create sheet2 header row
        Row header2 = sheet2.createRow(0);
        header2.createCell(0).setCellValue("LG.COM Product ID");
        header2.createCell(1).setCellValue("Product Name");
        header2.createCell(2).setCellValue("Product Price");
        header2.createCell(3).setCellValue("Sales Price");
        header2.createCell(4).setCellValue("Purchased Amount");
        header2.createCell(5).setCellValue("Purchased Quantity");
        header2.createCell(6).setCellValue("Sales Amount");
        header2.createCell(7).setCellValue("Sales Quantity");
        header2.createCell(8).setCellValue("Inventory Amount");
        header2.createCell(9).setCellValue("Current Inventory");
        

        // Create data cells for sheet2
        int rowCount2 = 1;
        for (Master master : masters){
        	if(master.getAccessory() == null) {
            Row courseRow = sheet2.createRow(rowCount2++);
            courseRow.createCell(0).setCellValue(master.getPhone().getProductId());
            courseRow.createCell(1).setCellValue(master.getPhone().getProductName());
            courseRow.createCell(2).setCellValue(master.getPhone().getPurchasePrice());
            courseRow.createCell(3).setCellValue(master.getPhone().getSalesPrice());
            courseRow.createCell(4).setCellValue(master.getPhone().getUnlockedPhonesInventory().getTotalPurchasedAmount());
            courseRow.createCell(5).setCellValue(master.getPhone().getUnlockedPhonesInventory().getTotalPurchasedQuantity());
            courseRow.createCell(6).setCellValue(master.getPhone().getUnlockedPhonesInventory().getSalesAmount());
            courseRow.createCell(7).setCellValue(master.getPhone().getUnlockedPhonesInventory().getSalesQuantity());
            courseRow.createCell(8).setCellValue(master.getPhone().getUnlockedPhonesInventory().getCurrentInventoryAmount());
            courseRow.createCell(9).setCellValue(master.getPhone().getUnlockedPhonesInventory().getCurrentInventory());
        	}
        }
        
     // create sheet2 header row
        Row header3 = sheet3.createRow(0);
        header3.createCell(0).setCellValue("LG.COM Product ID");
        header3.createCell(1).setCellValue("Product Name");
        header3.createCell(2).setCellValue("Product Price");
        header3.createCell(3).setCellValue("Sales Price");
        header3.createCell(4).setCellValue("Purchased Amount");
        header3.createCell(5).setCellValue("Purchased Quantity");
        header3.createCell(6).setCellValue("Sales Amount");
        header3.createCell(7).setCellValue("Sales Quantity");
        header3.createCell(8).setCellValue("Inventory Amount");
        header3.createCell(9).setCellValue("Current Inventory");
        

        // Create data cells for sheet2
        int rowCount3 = 1;
        for (Master master : masters){
        	if(master.getPhone() == null) {
                Row courseRow = sheet3.createRow(rowCount3++);
                courseRow.createCell(0).setCellValue(master.getAccessory().getProductId());
                courseRow.createCell(1).setCellValue(master.getAccessory().getProductName());
                courseRow.createCell(2).setCellValue(master.getAccessory().getPurchasePrice());
                courseRow.createCell(3).setCellValue(master.getAccessory().getSalesPrice());
                courseRow.createCell(4).setCellValue(master.getAccessory().getAccessoryInventory().getTotalPurchasedAmount());
                courseRow.createCell(5).setCellValue(master.getAccessory().getAccessoryInventory().getTotalPurchasedQuantity());
                courseRow.createCell(6).setCellValue(master.getAccessory().getAccessoryInventory().getSalesAmount());
                courseRow.createCell(7).setCellValue(master.getAccessory().getAccessoryInventory().getSalesQuantity());
                courseRow.createCell(8).setCellValue(master.getAccessory().getAccessoryInventory().getCurrentInventoryAmount());
                courseRow.createCell(9).setCellValue(master.getAccessory().getAccessoryInventory().getCurrentInventory());
            	}
        	if(master.getAccessory() == null) {
            Row courseRow = sheet3.createRow(rowCount3++);
           		courseRow.createCell(0).setCellValue(master.getPhone().getProductId());
            	courseRow.createCell(1).setCellValue(master.getPhone().getProductName());
            	courseRow.createCell(2).setCellValue(master.getPhone().getPurchasePrice());
            	courseRow.createCell(3).setCellValue(master.getPhone().getSalesPrice());
            	courseRow.createCell(4).setCellValue(master.getPhone().getUnlockedPhonesInventory().getTotalPurchasedAmount());
            	courseRow.createCell(5).setCellValue(master.getPhone().getUnlockedPhonesInventory().getTotalPurchasedQuantity());
            	courseRow.createCell(6).setCellValue(master.getPhone().getUnlockedPhonesInventory().getSalesAmount());
            	courseRow.createCell(7).setCellValue(master.getPhone().getUnlockedPhonesInventory().getSalesQuantity());
            	courseRow.createCell(8).setCellValue(master.getPhone().getUnlockedPhonesInventory().getCurrentInventoryAmount());
            	courseRow.createCell(9).setCellValue(master.getPhone().getUnlockedPhonesInventory().getCurrentInventory());
        	}
        }
	}
}
