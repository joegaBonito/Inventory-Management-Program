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

import com.obs.domain.UpsOrder;

@Component
public class OrdersExcelExport extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"LG.COM ORDERS.xls\"");

        @SuppressWarnings("unchecked")
        List<UpsOrder> upsOrders = (List<UpsOrder>) model.get("upsOrders");

        // create excel xls sheet
        Sheet sheet1 = workbook.createSheet("FNS File");
        Sheet sheet2 = workbook.createSheet("UPS File");
        
     // create sheet1 header row
        Row header1 = sheet1.createRow(0);
        header1.createCell(0).setCellValue("OrderID");
        header1.createCell(1).setCellValue("OrderDateTime");
        header1.createCell(2).setCellValue("Product#");
        header1.createCell(3).setCellValue("Quantity");
        header1.createCell(4).setCellValue("Product Name");
        header1.createCell(5).setCellValue("Shipping Method");
        header1.createCell(6).setCellValue("Name");
        header1.createCell(7).setCellValue("Address 1");
        header1.createCell(8).setCellValue("Address 2");
        header1.createCell(9).setCellValue("City");
        header1.createCell(10).setCellValue("Phone");

        // Create data cells for sheet1
        int rowCount1 = 1;
        for (UpsOrder upsOrder : upsOrders){
            Row courseRow = sheet1.createRow(rowCount1++);
            courseRow.createCell(0).setCellValue(upsOrder.getUpsOrderId());
            courseRow.createCell(1).setCellValue(upsOrder.getUpsOrderReceived());
            courseRow.createCell(2).setCellValue(upsOrder.getUpsProductId());
            courseRow.createCell(3).setCellValue(upsOrder.getUpsQuantity());
            courseRow.createCell(4).setCellValue(upsOrder.getUpsProductName());
            courseRow.createCell(5).setCellValue(upsOrder.getUpsShippingMethod());
            courseRow.createCell(6).setCellValue(upsOrder.getUpsFname() + " " + upsOrder.getUpsLname());
            courseRow.createCell(7).setCellValue(upsOrder.getAddress1());
            courseRow.createCell(8).setCellValue(upsOrder.getAddress2());
            courseRow.createCell(9).setCellValue(upsOrder.getCity() + " " + upsOrder.getState() + " " + upsOrder.getZip());
            courseRow.createCell(10).setCellValue(upsOrder.getPhone());
        }

        // create sheet2 header row
        Row header2 = sheet2.createRow(0);
        header2.createCell(0).setCellValue("OrderID");
        header2.createCell(1).setCellValue("OrderDateTime");
        header2.createCell(2).setCellValue("Product#");
        header2.createCell(3).setCellValue("Quantity");
        header2.createCell(4).setCellValue("ProductName");
        header2.createCell(5).setCellValue("ShippingMethod");
        header2.createCell(6).setCellValue("Name");
        header2.createCell(7).setCellValue("Address1");
        header2.createCell(8).setCellValue("Address2");
        header2.createCell(9).setCellValue("City");
        header2.createCell(10).setCellValue("State");
        header2.createCell(11).setCellValue("Zip");
        header2.createCell(12).setCellValue("Phone");
        header2.createCell(13).setCellValue("Residential");
        header2.createCell(14).setCellValue("Service");
        header2.createCell(15).setCellValue("PackingType");
        header2.createCell(16).setCellValue("BillTo");
        header2.createCell(17).setCellValue("L");
        header2.createCell(18).setCellValue("H");
        header2.createCell(19).setCellValue("W");
        header2.createCell(20).setCellValue("Weight");
        header2.createCell(21).setCellValue("Country");
        header2.createCell(22).setCellValue("CustomerName");
        

        // Create data cells for sheet2
        int rowCount2 = 1;
        for (UpsOrder upsOrder : upsOrders){
            Row courseRow = sheet2.createRow(rowCount2++);
            courseRow.createCell(0).setCellValue(upsOrder.getUpsOrderId());
            courseRow.createCell(1).setCellValue(upsOrder.getUpsOrderReceived());
            courseRow.createCell(2).setCellValue(upsOrder.getUpsProductId());
            courseRow.createCell(3).setCellValue(upsOrder.getUpsQuantity());
            courseRow.createCell(4).setCellValue(upsOrder.getUpsProductName());
            courseRow.createCell(5).setCellValue(upsOrder.getUpsShippingMethod());
            courseRow.createCell(6).setCellValue(upsOrder.getUpsFname() + " " + upsOrder.getUpsLname());
            courseRow.createCell(7).setCellValue(upsOrder.getAddress1());
            courseRow.createCell(8).setCellValue(upsOrder.getAddress2());
            courseRow.createCell(9).setCellValue(upsOrder.getCity());
            courseRow.createCell(10).setCellValue(upsOrder.getState());
            courseRow.createCell(11).setCellValue(upsOrder.getZip());
            courseRow.createCell(12).setCellValue(upsOrder.getPhone());
            courseRow.createCell(13).setCellValue(upsOrder.getResidential());
            courseRow.createCell(14).setCellValue(upsOrder.getService());
            courseRow.createCell(15).setCellValue(upsOrder.getPackageType());
            courseRow.createCell(16).setCellValue(upsOrder.getBillTo());
            courseRow.createCell(17).setCellValue(upsOrder.getUpsLength());
            courseRow.createCell(18).setCellValue(upsOrder.getUpsHeight());
            courseRow.createCell(19).setCellValue(upsOrder.getUpsWidth());
            courseRow.createCell(20).setCellValue(upsOrder.getUpsWeight());
            courseRow.createCell(21).setCellValue(upsOrder.getCountry());
            courseRow.createCell(22).setCellValue(upsOrder.getCustomerName());
        }
		
	}

}
