package com.obs.Phones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.obs.General.domain.UpsOrder;
import com.obs.General.services.impl.UpsServiceImpl;
import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.domain.PhonesReceivedQuantity;
import com.obs.Phones.domain.PhonesReturned;
import com.obs.Phones.services.PhoneInventoryService;


@Service
public class PhoneInventoryServiceImpl implements PhoneInventoryService {
	
	private ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl;
	private UpsServiceImpl upsServiceImpl;
	private PhonesReceivedQuantityServiceImpl phonesReceivedQuantityServiceImpl;
	private PhonesReturnedServiceImpl phonesReturnedServiceImpl;
	
	@Autowired
	public PhoneInventoryServiceImpl(ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl, 
							     UpsServiceImpl upsServiceImpl,
							     PhonesReceivedQuantityServiceImpl phonesReceivedQuantityServiceImpl,
							     PhonesReturnedServiceImpl phonesReturnedServiceImpl) {
		this.itemUnlockedPhonesServiceImpl = itemUnlockedPhonesServiceImpl;
		this.upsServiceImpl = upsServiceImpl;
		this.phonesReceivedQuantityServiceImpl = phonesReceivedQuantityServiceImpl;
		this.phonesReturnedServiceImpl =phonesReturnedServiceImpl;
	}
	
	public void setSalesQuantity() {
		
		for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesServiceImpl.list()) {
			int num = 0;
			for(UpsOrder upsOrder : upsServiceImpl.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemUnlockedPhone.getProductId())) {
					num += upsOrder.getUpsQuantity();
					itemUnlockedPhone.getUnlockedPhonesInventory().setSalesQuantity(num);
				}	
			}
			itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
		}
	}
	
	public void setSalesAmount() {
		for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesServiceImpl.list()) {
			double num = 0;
			for(UpsOrder upsOrder : upsServiceImpl.systemIdList()) {
				if(upsOrder.getUpsProductId().equals(itemUnlockedPhone.getProductId())) {
					num += itemUnlockedPhone.getSalesPrice() * upsOrder.getUpsQuantity();	
					itemUnlockedPhone.getUnlockedPhonesInventory().setSalesAmount(num);
				}	
			}
			itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
		}
	
	}
	
	public void setSalesAmountAfterReturn(ItemUnlockedPhone itemUnlockedPhone) {
		ItemUnlockedPhone ip = itemUnlockedPhonesServiceImpl.get(itemUnlockedPhone.getItemUnlockedPhoneId());
		ip.getUnlockedPhonesInventory().setSalesQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity()-itemUnlockedPhone.getUnlockedPhonesInventory().getReturnedQuantity());
		itemUnlockedPhonesServiceImpl.save(ip);
	}
	public void setSalesQuantityAfterReturn(ItemUnlockedPhone itemUnlockedPhone) {
		ItemUnlockedPhone ip = itemUnlockedPhonesServiceImpl.get(itemUnlockedPhone.getItemUnlockedPhoneId());
		ip.getUnlockedPhonesInventory().setSalesAmount(itemUnlockedPhone.getUnlockedPhonesInventory().getSalesAmount()-(itemUnlockedPhone.getUnlockedPhonesInventory().getReturnedQuantity()*itemUnlockedPhone.getSalesPrice()));
		itemUnlockedPhonesServiceImpl.save(ip);
	}
	
	@Transactional
	public void setCurrentInventory(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setCurrentInventory(itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() - 
																		   itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity() + 
																		   itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
		if(itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() < 0) 
			throw new RuntimeException();
	}
	
	public void setPurchasedQuantity(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setPurchasedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
		PhonesReceivedQuantity phonesReceivedQuantity = new PhonesReceivedQuantity();
		phonesReceivedQuantity.setPurchasedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		phonesReceivedQuantity.setReceivedDate(itemUnlockedPhone.getUnlockedPhonesInventory().getReceivedDate());
		phonesReceivedQuantity.setItemUnlockedPhone(itemUnlockedPhone);
		phonesReceivedQuantityServiceImpl.save(phonesReceivedQuantity);
	}
	
	public void setTotalPurchasedQuantity(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setTotalPurchasedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedQuantity() + 
				                                                                 itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity());
		itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
	}
	
	public void setPurchasedAmount(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setPurchasedAmount((itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() + 
				                                                           itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity()) *  
				                                                           itemUnlockedPhone.getPurchasePrice());
		itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
	}
	
	public void setTotalPurchasedAmount(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setTotalPurchasedAmount(itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedQuantity() * 
				                                                               itemUnlockedPhone.getPurchasePrice());
		itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
	}
	
	public void setCurrentInventoryAmount(ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.getUnlockedPhonesInventory().setCurrentInventoryAmount((itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() + 
				                                                                  itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity() - 
				                                                                  itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity()) * 
				                                                                  itemUnlockedPhone.getPurchasePrice());
		itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
	}

	public void setReturnedInventory(ItemUnlockedPhone itemUnlockedPhone) {
		
		PhonesReturned phonesReturned = new PhonesReturned();
		phonesReturned.setReturnedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getReturnedQuantity());
		phonesReturned.setReturnedDate(itemUnlockedPhone.getUnlockedPhonesInventory().getReturnedDate());
		phonesReturned.setItemUnlockedPhone(itemUnlockedPhone);
		phonesReturnedServiceImpl.save(phonesReturned);
	}
}
