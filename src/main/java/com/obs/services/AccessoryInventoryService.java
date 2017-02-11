package com.obs.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.domain.AccessoryInventory;
import com.obs.domain.ItemAccessory;
import com.obs.domain.AccessoryReceivedQuantity;
import com.obs.domain.UpsOrder;

@Service
public class AccessoryInventoryService {
	
	private UpsService upsService;
	private ItemAccessoryService itemAccessoryService;
	private AccessoryReceivedQuantityService accessoryReceivedQuantityService;
	
	@Autowired
	public AccessoryInventoryService(UpsService upsService,
							ItemAccessoryService itemAccessoryService,
							AccessoryReceivedQuantityService accessoryReceivedQuantityService) {
		this.upsService = upsService;
		this.itemAccessoryService = itemAccessoryService;
		this.accessoryReceivedQuantityService = accessoryReceivedQuantityService;
	}

	public void setSalesQuantity() {
		
		for(ItemAccessory itemAccessory : itemAccessoryService.list()) {
			int num = 0;
			for(UpsOrder upsOrder : upsService.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemAccessory.getProductId())) {
					num += upsOrder.getUpsQuantity();	
					itemAccessory.getAccessoryInventory().setSalesQuantity(num);
				}	
			}
			itemAccessoryService.save(itemAccessory);
		}
	}
	
	public void setSalesAmount() {
		for(ItemAccessory itemAccessory : itemAccessoryService.list()) {
			double num = 0;
			for(UpsOrder upsOrder : upsService.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemAccessory.getProductId())) {
					num += itemAccessory.getSalesPrice() * upsOrder.getUpsQuantity();	
					itemAccessory.getAccessoryInventory().setSalesAmount(num);
				}	
			}
			itemAccessoryService.save(itemAccessory);
		}
	
	}
	
	public void setCurrentInventory(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setCurrentInventory(itemAccessory.getAccessoryInventory().getCurrentInventory() - itemAccessory.getAccessoryInventory().getSalesQuantity() + itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryService.save(itemAccessory);
	}
	public void setPurchasedQuantity(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setPurchasedQuantity(itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryService.save(itemAccessory);
		AccessoryReceivedQuantity accessoryReceivedQuantity = new AccessoryReceivedQuantity();
		accessoryReceivedQuantity.setPurchasedQuantity(itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		accessoryReceivedQuantity.setReceivedDate(itemAccessory.getAccessoryInventory().getReceivedDate());
		accessoryReceivedQuantity.setItemAccessory(itemAccessory);
		accessoryReceivedQuantityService.save(accessoryReceivedQuantity);
	}
	
	public void setTotalPurchasedQuantity(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setTotalPurchasedQuantity(itemAccessory.getAccessoryInventory().getTotalPurchasedQuantity() + itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryService.save(itemAccessory);
	}
	
	public void setPurchasedAmount(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setPurchasedAmount((itemAccessory.getAccessoryInventory().getCurrentInventory() + itemAccessory.getAccessoryInventory().getPurchasedQuantity())* itemAccessory.getPurchasePrice());
		itemAccessoryService.save(itemAccessory);
	}
	
	public void setTotalPurchasedAmount(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setTotalPurchasedAmount(itemAccessory.getAccessoryInventory().getTotalPurchasedQuantity() * itemAccessory.getPurchasePrice());
		itemAccessoryService.save(itemAccessory);
	}
	
	public void setCurrentInventoryAmount(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setCurrentInventoryAmount((itemAccessory.getAccessoryInventory().getCurrentInventory() + itemAccessory.getAccessoryInventory().getPurchasedQuantity() - itemAccessory.getAccessoryInventory().getSalesQuantity())* itemAccessory.getPurchasePrice());
		itemAccessoryService.save(itemAccessory);
	}
	
	public void setTotals(AccessoryInventory accessoryInventory) {
		int num1 = 0;
		int num2 = 0;
		double num3 = 0;
		double num4 = 0;
		for(ItemAccessory itemAccessory: itemAccessoryService.list()){
			num1 += itemAccessory.getAccessoryInventory().getSalesQuantity();
			num2 += itemAccessory.getAccessoryInventory().getCurrentInventory();
			num3 += itemAccessory.getAccessoryInventory().getSalesAmount();
			num4 += itemAccessory.getAccessoryInventory().getPurchasedAmount();
		}
		accessoryInventory.setTotalSalesQuantity(num1);
		accessoryInventory.setTotalCurrentInventory(num2);
		accessoryInventory.setTotalSalesAmount(num3);
		accessoryInventory.setTotalPurchasedAmount(num4);
	}

}
