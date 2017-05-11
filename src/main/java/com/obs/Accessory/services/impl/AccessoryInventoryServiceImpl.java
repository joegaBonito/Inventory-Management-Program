package com.obs.Accessory.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obs.Accessory.domain.AccessoryReceivedQuantity;
import com.obs.Accessory.domain.AccessoryReturned;
import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Accessory.services.AccessoryInventoryService;
import com.obs.General.domain.UpsOrder;
import com.obs.General.services.impl.UpsServiceImpl;

@Service
public class AccessoryInventoryServiceImpl implements AccessoryInventoryService {
	
	private UpsServiceImpl upsServiceImpl;
	private ItemAccessoryServiceImpl itemAccessoryServiceImpl;
	private AccessoryReceivedQuantityServiceImpl accessoryReceivedQuantityServiceImpl;
	private AccessoryReturnedServiceImpl accessoryReturnedServiceImpl;
	
	@Autowired
	public AccessoryInventoryServiceImpl(UpsServiceImpl upsServiceImpl,
							ItemAccessoryServiceImpl itemAccessoryServiceImpl,
							AccessoryReceivedQuantityServiceImpl accessoryReceivedQuantityServiceImpl, 
							AccessoryReturnedServiceImpl accessoryReturnedServiceImpl) {
		this.upsServiceImpl = upsServiceImpl;
		this.itemAccessoryServiceImpl = itemAccessoryServiceImpl;
		this.accessoryReceivedQuantityServiceImpl = accessoryReceivedQuantityServiceImpl;
		this.accessoryReturnedServiceImpl =accessoryReturnedServiceImpl;
	}

	public void setSalesQuantity() {
		for(ItemAccessory itemAccessory : itemAccessoryServiceImpl.list()) {
			int num = 0;
			for(UpsOrder upsOrder : upsServiceImpl.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemAccessory.getProductId())) {
					num += upsOrder.getUpsQuantity();		
					itemAccessory.getAccessoryInventory().setSalesQuantity(num);
				}	
			}
			itemAccessoryServiceImpl.save(itemAccessory);
		}
	}
	
	public void setSalesAmount() {
		for(ItemAccessory itemAccessory : itemAccessoryServiceImpl.list()) {
			double num = 0;
			for(UpsOrder upsOrder : upsServiceImpl.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemAccessory.getProductId())) {
					num +=itemAccessory.getSalesPrice() * upsOrder.getUpsQuantity();
					itemAccessory.getAccessoryInventory().setSalesAmount(num);
				}	 
			}
			itemAccessoryServiceImpl.save(itemAccessory);
		}
	}
	public void setSalesAmountAfterReturn(ItemAccessory itemAccessory) {
		ItemAccessory ia = itemAccessoryServiceImpl.get(itemAccessory.getItemAccessoryId());
		ia.getAccessoryInventory().setSalesQuantity(itemAccessory.getAccessoryInventory().getSalesQuantity()-itemAccessory.getAccessoryInventory().getReturnedQuantity());
		itemAccessoryServiceImpl.save(ia);
	}
	public void setSalesQuantityAfterReturn(ItemAccessory itemAccessory) {
		ItemAccessory ia = itemAccessoryServiceImpl.get(itemAccessory.getItemAccessoryId());
		ia.getAccessoryInventory().setSalesAmount(itemAccessory.getAccessoryInventory().getSalesAmount()-(itemAccessory.getAccessoryInventory().getReturnedQuantity()*itemAccessory.getSalesPrice()));
		itemAccessoryServiceImpl.save(ia);
	}
	
	@Transactional
	public void setCurrentInventory(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setCurrentInventory(itemAccessory.getAccessoryInventory().getCurrentInventory() - itemAccessory.getAccessoryInventory().getSalesQuantity() + itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryServiceImpl.save(itemAccessory);
		if(itemAccessory.getAccessoryInventory().getCurrentInventory() < 0) 
			throw new RuntimeException();
	}
	public void setPurchasedQuantity(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setPurchasedQuantity(itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryServiceImpl.save(itemAccessory);
		/*
		 * This part records each received quantity for the day. 
		 */
		AccessoryReceivedQuantity accessoryReceivedQuantity = new AccessoryReceivedQuantity();
		accessoryReceivedQuantity.setPurchasedQuantity(itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		accessoryReceivedQuantity.setReceivedDate(itemAccessory.getAccessoryInventory().getReceivedDate());
		accessoryReceivedQuantity.setItemAccessory(itemAccessory);
		accessoryReceivedQuantityServiceImpl.save(accessoryReceivedQuantity);
	}
	
	public void setTotalPurchasedQuantity(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setTotalPurchasedQuantity(itemAccessory.getAccessoryInventory().getTotalPurchasedQuantity() + itemAccessory.getAccessoryInventory().getPurchasedQuantity());
		itemAccessoryServiceImpl.save(itemAccessory);
	}
	
	public void setPurchasedAmount(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setPurchasedAmount((itemAccessory.getAccessoryInventory().getCurrentInventory() + itemAccessory.getAccessoryInventory().getPurchasedQuantity())* itemAccessory.getPurchasePrice());
		itemAccessoryServiceImpl.save(itemAccessory);
	}
	
	public void setTotalPurchasedAmount(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setTotalPurchasedAmount(itemAccessory.getAccessoryInventory().getTotalPurchasedQuantity() * itemAccessory.getPurchasePrice());
		itemAccessoryServiceImpl.save(itemAccessory);
	}
	
	public void setCurrentInventoryAmount(ItemAccessory itemAccessory) {
		itemAccessory.getAccessoryInventory().setCurrentInventoryAmount((itemAccessory.getAccessoryInventory().getCurrentInventory() + itemAccessory.getAccessoryInventory().getPurchasedQuantity() - itemAccessory.getAccessoryInventory().getSalesQuantity())* itemAccessory.getPurchasePrice());
		itemAccessoryServiceImpl.save(itemAccessory);
	}

	public void setReturnedInventory(ItemAccessory itemAccessory) {
		AccessoryReturned accessoryReturned = new AccessoryReturned();
		accessoryReturned.setReturnedQuantity(itemAccessory.getAccessoryInventory().getReturnedQuantity());
		accessoryReturned.setReturnedDate(itemAccessory.getAccessoryInventory().getReturnedDate());
		accessoryReturned.setItemAccessory(itemAccessory);
		accessoryReturnedServiceImpl.save(accessoryReturned);
	}
}
