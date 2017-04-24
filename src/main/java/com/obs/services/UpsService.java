package com.obs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.domain.ItemAccessory;
import com.obs.domain.ItemUnlockedPhone;
import com.obs.domain.UpsOrder;
import com.obs.repositories.UpsRepository;

@Service
public class UpsService {

	private UpsRepository upsRepository;
	private ItemAccessoryService itemAccessoryService;
	private ItemUnlockedPhonesService itemUnlockedPhonesService;
	
	@Autowired
	public UpsService(UpsRepository upsRepository, ItemAccessoryService itemAccessoryService, ItemUnlockedPhonesService itemUnlockedPhonesService) {
		this.upsRepository = upsRepository;
		this.itemAccessoryService = itemAccessoryService;
		this.itemUnlockedPhonesService = itemUnlockedPhonesService;
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
		for(ItemAccessory itemAccessory : itemAccessoryService.list()){
			if(get(systemId).getUpsProductId().equals(itemAccessory.getProductId())) {
				itemAccessory.getAccessoryInventory().setSalesQuantity(itemAccessory.getAccessoryInventory().getSalesQuantity() - get(systemId).getUpsQuantity());
				itemAccessory.getAccessoryInventory().setSalesAmount(itemAccessory.getAccessoryInventory().getSalesAmount() - (get(systemId).getUpsQuantity() * itemAccessory.getSalesPrice()));
			}
		}
		for(ItemUnlockedPhone itemUnlockedPhone: itemUnlockedPhonesService.list()){
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
		for(ItemAccessory itemAccessory : itemAccessoryService.list()){
			itemAccessory.getAccessoryInventory().setSalesQuantity(0);
			itemAccessory.getAccessoryInventory().setSalesAmount(0);
		}
		for(ItemUnlockedPhone itemUnlockedPhone: itemUnlockedPhonesService.list()){
			itemUnlockedPhone.getUnlockedPhonesInventory().setSalesQuantity(0);
			itemUnlockedPhone.getUnlockedPhonesInventory().setSalesAmount(0);
		}
		upsRepository.deleteAll();
	}
	
	/*
	 * Setting up the Product Name, Length, Height, Width, Weight by using Product ID
	 */
	public void upsAccSetProductName(UpsOrder upsOrder) {
		for(int i = 1; i <= itemAccessoryService.list().size(); i++){
			if(upsOrder.getUpsProductId().equals(itemAccessoryService.getProductId((long) i))){
				upsOrder.setUpsProductName(itemAccessoryService.getProductName((long) i));
				upsOrder.setUpsLength(itemAccessoryService.getLength((long) i));
				upsOrder.setUpsHeight(itemAccessoryService.getHeight((long) i));
				upsOrder.setUpsWidth(itemAccessoryService.getWidth((long) i));
				upsOrder.setUpsWeight(itemAccessoryService.getWeight((long) i));
			}
		}
	}
	public void upsUpSetProductName(UpsOrder upsOrder) {
		for(int i = 1; i <= itemUnlockedPhonesService.list().size(); i++){
				if(upsOrder.getUpsProductId().equals(itemUnlockedPhonesService.getProductId((long) i))){
					upsOrder.setUpsProductName(itemUnlockedPhonesService.getProductName((long) i));
					upsOrder.setUpsLength(itemUnlockedPhonesService.getLength((long) i));
					upsOrder.setUpsHeight(itemUnlockedPhonesService.getHeight((long) i));
					upsOrder.setUpsWidth(itemUnlockedPhonesService.getWidth((long) i));
					upsOrder.setUpsWeight(itemUnlockedPhonesService.getWeight((long) i));
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
