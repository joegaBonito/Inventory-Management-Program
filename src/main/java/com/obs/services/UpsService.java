package com.obs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.domain.UpsOrder;
import com.obs.repositories.UpsRepository;

@Service
public class UpsService {
	@Autowired
	private UpsRepository upsRepository;
	
	@Autowired
	private ItemAccessoryService itemAccessoryService;
	
	@Autowired
	private ItemUnlockedPhonesService itemUnlockedPhonesService;

	public UpsService(UpsRepository upsRepository, ItemAccessoryService itemAccessoryService, ItemUnlockedPhonesService itemUnlockedPhonesService) {
		this.upsRepository = upsRepository;
		this.itemAccessoryService = itemAccessoryService;
		this.itemUnlockedPhonesService = itemUnlockedPhonesService;
	}
	
	public List<UpsOrder> list() {
		return upsRepository.findByOrderByUpsOrderReceivedDesc();
	}
	
	public List<UpsOrder> systemIdList() {
		return upsRepository.findByOrderBySystemId();
	}
	
	public void save(UpsOrder upsOrder) {
		upsRepository.save(upsOrder);
	}

	public void delete(Long systemId) {
		upsRepository.delete(systemId);
	}
	public UpsOrder get(Long systemId) {
		return upsRepository.findOne(systemId);
	}
	public void deleteAll() {
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
