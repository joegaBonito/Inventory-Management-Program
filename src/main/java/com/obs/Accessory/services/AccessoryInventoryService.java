package com.obs.Accessory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obs.Accessory.domain.AccessoryReceivedQuantity;
import com.obs.Accessory.domain.AccessoryReturned;
import com.obs.Accessory.domain.ItemAccessory;
import com.obs.General.domain.UpsOrder;
import com.obs.General.services.UpsService;

@Service
public class AccessoryInventoryService {
	
	private UpsService upsService;
	private ItemAccessoryService itemAccessoryService;
	private AccessoryReceivedQuantityService accessoryReceivedQuantityService;
	private AccessoryReturnedService accessoryReturnedService;
	
	@Autowired
	public AccessoryInventoryService(UpsService upsService,
							ItemAccessoryService itemAccessoryService,
							AccessoryReceivedQuantityService accessoryReceivedQuantityService, 
							AccessoryReturnedService accessoryReturnedService) {
		this.upsService = upsService;
		this.itemAccessoryService = itemAccessoryService;
		this.accessoryReceivedQuantityService = accessoryReceivedQuantityService;
		this.accessoryReturnedService =accessoryReturnedService;
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
					num +=itemAccessory.getSalesPrice() * upsOrder.getUpsQuantity();
					itemAccessory.getAccessoryInventory().setSalesAmount(num);
				}	 
			}
			itemAccessoryService.save(itemAccessory);
		}
	}
	public void setSalesAmountAfterReturn(ItemAccessory itemAccessory) {
		ItemAccessory ia = itemAccessoryService.get(itemAccessory.getItemAccessoryId());
		ia.getAccessoryInventory().setSalesQuantity(itemAccessory.getAccessoryInventory().getSalesQuantity()-itemAccessory.getAccessoryInventory().getReturnedQuantity());
		itemAccessoryService.save(ia);
	}
	public void setSalesQuantityAfterReturn(ItemAccessory itemAccessory) {
		ItemAccessory ia = itemAccessoryService.get(itemAccessory.getItemAccessoryId());
		ia.getAccessoryInventory().setSalesAmount(itemAccessory.getAccessoryInventory().getSalesAmount()-(itemAccessory.getAccessoryInventory().getReturnedQuantity()*itemAccessory.getSalesPrice()));
		itemAccessoryService.save(ia);
	}
	
	@Transactional
	public void setCurrentInventory(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setCurrentInventory(itemAccessory.getAccessoryInventory().getCurrentInventory() - itemAccessory.getAccessoryInventory().getSalesQuantity() + itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryService.save(itemAccessory);
		if(itemAccessory.getAccessoryInventory().getCurrentInventory() < 0) 
			throw new RuntimeException();
	}
	public void setPurchasedQuantity(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setPurchasedQuantity(itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryService.save(itemAccessory);
		/*
		 * This part records each received quantity for the day. 
		 */
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

	public void setReturnedInventory(ItemAccessory itemAccessory) {
		AccessoryReturned accessoryReturned = new AccessoryReturned();
		accessoryReturned.setReturnedQuantity(itemAccessory.getAccessoryInventory().getReturnedQuantity());
		accessoryReturned.setReturnedDate(itemAccessory.getAccessoryInventory().getReturnedDate());
		accessoryReturned.setItemAccessory(itemAccessory);
		accessoryReturnedService.save(accessoryReturned);
	}
}
