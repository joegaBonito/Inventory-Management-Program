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

import com.obs.Master.domain.Master;

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
        Sheet sheet1 = workbook.createSheet("Master Inventory List");
        
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
            Row courseRow = sheet1.createRow(rowCount1++);
            courseRow.createCell(0).setCellValue(master.getProductId());
            courseRow.createCell(1).setCellValue(master.getProductName());
            courseRow.createCell(2).setCellValue(master.getPurchasePrice());
            courseRow.createCell(3).setCellValue(master.getSalesPrice());
            courseRow.createCell(4).setCellValue(master.getTotalPurchasedAmount());
            courseRow.createCell(5).setCellValue(master.getTotalPurchasedQuantity());
            courseRow.createCell(6).setCellValue(master.getSalesAmount());
            courseRow.createCell(7).setCellValue(master.getSalesQuantity());
            courseRow.createCell(8).setCellValue(master.getCurrentInventoryAmount());
            courseRow.createCell(9).setCellValue(master.getCurrentInventory());
        }
	}
}
