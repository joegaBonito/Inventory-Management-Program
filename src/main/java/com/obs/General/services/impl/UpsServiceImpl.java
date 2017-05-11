package com.obs.General.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Accessory.services.impl.ItemAccessoryServiceImpl;
import com.obs.General.domain.UpsOrder;
import com.obs.General.repositories.UpsRepository;
import com.obs.General.services.UpsService;
import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.services.impl.ItemUnlockedPhonesServiceImpl;

@Service
public class UpsServiceImpl implements UpsService {

	private UpsRepository upsRepository;
	private ItemAccessoryServiceImpl itemAccessoryServiceImpl;
	private ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl;
	
	@Autowired
	public UpsServiceImpl(UpsRepository upsRepository, ItemAccessoryServiceImpl itemAccessoryServiceImpl, ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl) {
		this.upsRepository = upsRepository;
		this.itemAccessoryServiceImpl = itemAccessoryServiceImpl;
		this.itemUnlockedPhonesServiceImpl = itemUnlockedPhonesServiceImpl;
	}
	
	public List<UpsOrder> list() {
		return upsRepository.findByOrderByUpsOrderReceived();
	}
	
	/*
	 * Used for Pagination.
	 */
	public Page<UpsOrder> findAll(Pageable pageable) {
		return upsRepository.findAll(pageable);
	}
	
	public List<UpsOrder> systemIdList() {
		return upsRepository.findByOrderBySystemId();
	}
	
	public void save(UpsOrder upsOrder) {
		upsRepository.save(upsOrder);
	}

	public void delete(Long systemId) {
		/*
		 * Cascade delete to the Inventory Table when an order is deleted from the Order Table.
		 */
		for(ItemAccessory itemAccessory : itemAccessoryServiceImpl.list()){
			if(get(systemId).getUpsProductId().equals(itemAccessory.getProductId())) {
				itemAccessory.getAccessoryInventory().setSalesQuantity(itemAccessory.getAccessoryInventory().getSalesQuantity() - get(systemId).getUpsQuantity());
				itemAccessory.getAccessoryInventory().setSalesAmount(itemAccessory.getAccessoryInventory().getSalesAmount() - (get(systemId).getUpsQuantity() * itemAccessory.getSalesPrice()));
			}
		}
		for(ItemUnlockedPhone itemUnlockedPhone: itemUnlockedPhonesServiceImpl.list()){
			if(get(systemId).getUpsProductId().equals(itemUnlockedPhone.getProductId())){
				itemUnlockedPhone.getUnlockedPhonesInventory().setSalesQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity() - get(systemId).getUpsQuantity());
				itemUnlockedPhone.getUnlockedPhonesInventory().setSalesAmount(itemUnlockedPhone.getUnlockedPhonesInventory().getSalesAmount() - (get(systemId).getUpsQuantity() * itemUnlockedPhone.getSalesPrice()));
			}
		}
		upsRepository.delete(systemId);
	}
	public UpsOrder get(Long systemId) {
		return upsRepository.findOne(systemId);
	}
	public void deleteAll() {
		/*
		 * When all orders are deleted, all sales amount and quantity resets to 0.
		 */
		for(ItemAccessory itemAccessory : itemAccessoryServiceImpl.list()){
			itemAccessory.getAccessoryInventory().setSalesQuantity(0);
			itemAccessory.getAccessoryInventory().setSalesAmount(0);
		}
		for(ItemUnlockedPhone itemUnlockedPhone: itemUnlockedPhonesServiceImpl.list()){
			itemUnlockedPhone.getUnlockedPhonesInventory().setSalesQuantity(0);
			itemUnlockedPhone.getUnlockedPhonesInventory().setSalesAmount(0);
		}
		upsRepository.deleteAll();
	}
	
	/*
	 * Setting up the Product Name, Length, Height, Width, Weight by using Product ID
	 */
	public void upsAccSetProductName(UpsOrder upsOrder) {
		for(int i = 1; i <= itemAccessoryServiceImpl.list().size(); i++){
			if(upsOrder.getUpsProductId().equals(itemAccessoryServiceImpl.getProductId((long) i))){
				upsOrder.setUpsProductName(itemAccessoryServiceImpl.getProductName((long) i));
				upsOrder.setUpsLength(itemAccessoryServiceImpl.getLength((long) i));
				upsOrder.setUpsHeight(itemAccessoryServiceImpl.getHeight((long) i));
				upsOrder.setUpsWidth(itemAccessoryServiceImpl.getWidth((long) i));
				upsOrder.setUpsWeight(itemAccessoryServiceImpl.getWeight((long) i));
			}
		}
	}
	public void upsUpSetProductName(UpsOrder upsOrder) {
		for(int i = 1; i <= itemUnlockedPhonesServiceImpl.list().size(); i++){
				if(upsOrder.getUpsProductId().equals(itemUnlockedPhonesServiceImpl.getProductId((long) i))){
					upsOrder.setUpsProductName(itemUnlockedPhonesServiceImpl.getProductName((long) i));
					upsOrder.setUpsLength(itemUnlockedPhonesServiceImpl.getLength((long) i));
					upsOrder.setUpsHeight(itemUnlockedPhonesServiceImpl.getHeight((long) i));
					upsOrder.setUpsWidth(itemUnlockedPhonesServiceImpl.getWidth((long) i));
					upsOrder.setUpsWeight(itemUnlockedPhonesServiceImpl.getWeight((long) i));
			}
		}
	}
	
	public void upsSetShippingMethod(UpsOrder upsOrder) {
		if(upsOrder.getService().equals("GND")) {
			upsOrder.setUpsShippingMethod("Ground - lgus - US");
		} 
		if(upsOrder.getService().equals("1DP")) {
			upsOrder.setUpsShippingMethod("jabra - UPS Next Day");
		}
	}
	public void upsSetService(UpsOrder upsOrder) {
		if(upsOrder.getUpsShippingMethod().equals("Ground - lgus - US")) {
			upsOrder.setService("GND");
		} 
		if(upsOrder.getUpsShippingMethod().equals("jabra - UPS Next Day")) {
			upsOrder.setService("1DP");
		}
	}


}
