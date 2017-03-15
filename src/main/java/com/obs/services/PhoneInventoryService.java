package com.obs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obs.domain.ItemUnlockedPhone;
import com.obs.domain.PhonesReceivedQuantity;
import com.obs.domain.UnlockedPhonesInventory;
import com.obs.domain.UpsOrder;

@Service
public class PhoneInventoryService {
	
	private ItemUnlockedPhonesService itemUnlockedPhonesService;
	private UpsService upsService;
	private PhonesReceivedQuantityService phonesReceivedQuantityService;
	
	@Autowired
	public PhoneInventoryService(ItemUnlockedPhonesService itemUnlockedPhonesService, 
							     UpsService upsService,
							     PhonesReceivedQuantityService phonesReceivedQuantityService) {
		this.itemUnlockedPhonesService = itemUnlockedPhonesService;
		this.upsService = upsService;
		this.phonesReceivedQuantityService = phonesReceivedQuantityService;
	}
	
	public void setSalesQuantity() {
		
		for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()) {
			int num = 0;
			for(UpsOrder upsOrder : upsService.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemUnlockedPhone.getProductId())) {
					num += upsOrder.getUpsQuantity();	
					itemUnlockedPhone.getUnlockedPhonesInventory().setSalesQuantity(num);
				}	
			}
			itemUnlockedPhonesService.save(itemUnlockedPhone);
		}
	}
	
	public void setSalesAmount() {
		for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()) {
			double num = 0;
			for(UpsOrder upsOrder : upsService.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemUnlockedPhone.getProductId())) {
					num += itemUnlockedPhone.getSalesPrice() * upsOrder.getUpsQuantity();	
					itemUnlockedPhone.getUnlockedPhonesInventory().setSalesAmount(num);
				}	
			}
			itemUnlockedPhonesService.save(itemUnlockedPhone);
		}
	
	}
	@Transactional
	public void setCurrentInventory(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setCurrentInventory(itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() - 
																		   itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity() + 
																		   itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		itemUnlockedPhonesService.save(itemUnlockedPhone);
		if(itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() < 0) 
			throw new RuntimeException();
	}
	
	public void setPurchasedQuantity(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setPurchasedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		itemUnlockedPhonesService.save(itemUnlockedPhone);
		PhonesReceivedQuantity phonesReceivedQuantity = new PhonesReceivedQuantity();
		phonesReceivedQuantity.setPurchasedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		phonesReceivedQuantity.setReceivedDate(itemUnlockedPhone.getUnlockedPhonesInventory().getReceivedDate());
		phonesReceivedQuantity.setItemUnlockedPhone(itemUnlockedPhone);
		phonesReceivedQuantityService.save(phonesReceivedQuantity);
	}
	
	public void setTotalPurchasedQuantity(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setTotalPurchasedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedQuantity() + 
				                                                                 itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		itemUnlockedPhonesService.save(itemUnlockedPhone);
	}
	
	public void setPurchasedAmount(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setPurchasedAmount((itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() + 
				                                                           itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity()) *  
				                                                           itemUnlockedPhone.getPurchasePrice());
		itemUnlockedPhonesService.save(itemUnlockedPhone);
	}
	
	public void setTotalPurchasedAmount(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setTotalPurchasedAmount(itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedQuantity() * 
				                                                               itemUnlockedPhone.getPurchasePrice());
		itemUnlockedPhonesService.save(itemUnlockedPhone);
	}
	
	public void setCurrentInventoryAmount(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setCurrentInventoryAmount((itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() + 
				                                                                  itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity() - 
				                                                                  itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity()) * 
				                                                                  itemUnlockedPhone.getPurchasePrice());
		itemUnlockedPhonesService.save(itemUnlockedPhone);
	}
}
