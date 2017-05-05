package com.obs.Master.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Accessory.services.ItemAccessoryService;
import com.obs.Master.domain.Master;
import com.obs.Master.repositories.MasterRepository;
import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.services.ItemUnlockedPhonesService;

@Service
public class MasterService {
	
	private MasterRepository masterRepository;
	private ItemAccessoryService itemAccessoryService;
	private ItemUnlockedPhonesService itemUnlockedPhonesService;
	
	@Autowired
	public MasterService(MasterRepository masterRepository, ItemAccessoryService itemAccessoryService, ItemUnlockedPhonesService itemUnlockedPhonesService) {
		this.masterRepository = masterRepository;
		this.itemAccessoryService =itemAccessoryService;
		this.itemUnlockedPhonesService = itemUnlockedPhonesService;
	}
	
	public void add(Master master) {
		masterRepository.save(master);
	}
	
	public void delete(String productId) {
		Master m = masterRepository.findByProductId(productId);
		m.setDeleteYN('Y');
		add(m);
	}
	
	public List<Master> list () {
		return masterRepository.findByDeleteYN();
	}
	
	
	/*
	 * Retrieves the grand total of every product's total purchased amount on /master/index.html 
	 */
	public double getGrandTotalPurchasedAmount() {
		double temp = 0;
		for(Master master : list()) {
			for(ItemAccessory itemAccessory : itemAccessoryService.list()){
				if(master.getProductId() == itemAccessory.getProductId()) {
					temp += itemAccessory.getAccessoryInventory().getTotalPurchasedAmount();
				}
			}
			for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()){
				if(master.getProductId() == itemUnlockedPhone.getProductId()) {
					temp += itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedAmount();
				}
			}
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total purchased quantity on /master/index.html 
	 */
	public int getGrandTotalPurchasedQuantity() {
		int temp = 0;
		for(Master master : list()) {
			for(ItemAccessory itemAccessory : itemAccessoryService.list()){
				if(master.getProductId() == itemAccessory.getProductId()) {
					temp += itemAccessory.getAccessoryInventory().getTotalPurchasedQuantity();
				}
			}
			for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()){
				if(master.getProductId() == itemUnlockedPhone.getProductId()) {
					temp += itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedQuantity();
				}
			}
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total sales amount on /master/index.html 
	 */
	public double getGrandTotalSalesAmount() {
		double temp = 0;
		for(Master master : list()) {
			for(ItemAccessory itemAccessory : itemAccessoryService.list()){
				if(master.getProductId() == itemAccessory.getProductId()) {
					temp += itemAccessory.getAccessoryInventory().getSalesAmount();
				}
			}
			for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()){
				if(master.getProductId() == itemUnlockedPhone.getProductId()) {
					temp += itemUnlockedPhone.getUnlockedPhonesInventory().getSalesAmount();
				}
			}
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total sales quantity on /master/index.html 
	 */
	public int getGrandTotalSalesQuantity() {
		int temp = 0;
		for(Master master : list()) {
			for(ItemAccessory itemAccessory : itemAccessoryService.list()){
				if(master.getProductId() == itemAccessory.getProductId()) {
					temp += itemAccessory.getAccessoryInventory().getSalesQuantity();
				}
			}
			for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()){
				if(master.getProductId() == itemUnlockedPhone.getProductId()) {
					temp += itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity();
				}
			}
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total current inventory on /master/index.html 
	 */
	public int getGrandTotalCurrentInventory() {
		int temp = 0;
		for(Master master : list()) {
			for(ItemAccessory itemAccessory : itemAccessoryService.list()){
				if(master.getProductId() == itemAccessory.getProductId()) {
					temp += itemAccessory.getAccessoryInventory().getCurrentInventory();
				}
			}
			for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()){
				if(master.getProductId() == itemUnlockedPhone.getProductId()) {
					temp += itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory();
				}
			}
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total current amount on /master/index.html 
	 */
	
	public double getGrandTotalCurrentInventoryAmount() {
		double temp = 0;
		for(Master master : list()) {
			for(ItemAccessory itemAccessory : itemAccessoryService.list()){
				if(master.getProductId() == itemAccessory.getProductId()) {
					temp += (itemAccessory.getAccessoryInventory().getCurrentInventory() * itemAccessory.getPurchasePrice());
				}
			}
			for(ItemUnlockedPhone itemUnlockedPhone : itemUnlockedPhonesService.list()){
				if(master.getProductId() == itemUnlockedPhone.getProductId()) {
					temp += (itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory() * itemUnlockedPhone.getPurchasePrice());
				}
			}
		}
		return temp;
	}

	public Master getMasterById(long id) {
		return masterRepository.findOne(id);
	}

	public void saveItemAccessory(ItemAccessory itemAccessory) {
		Master master = new Master();
		master.setProductId(itemAccessory.getProductId());
		master.setProductName(itemAccessory.getProductName());
		master.setPurchasePrice(itemAccessory.getPurchasePrice());
		master.setSalesPrice(itemAccessory.getSalesPrice());
		master.setTotalPurchasedAmount(itemAccessory.getAccessoryInventory().getTotalPurchasedAmount());
		master.setTotalPurchasedQuantity(itemAccessory.getAccessoryInventory().getTotalPurchasedQuantity());
		master.setTotalSalesAmount(itemAccessory.getAccessoryInventory().getSalesAmount());
		master.setTotalSalesQuantity(itemAccessory.getAccessoryInventory().getSalesQuantity());
		master.setCurrentInventoryAmount(itemAccessory.getAccessoryInventory().getCurrentInventoryAmount());
		master.setCurrentInventory(itemAccessory.getAccessoryInventory().getCurrentInventory());
		master.setDeleteYN(itemAccessory.getDeleteYN());
		masterRepository.save(master);
	}

	public void saveItemUnlockedPhone(ItemUnlockedPhone itemUnlockedPhone) {
		Master master = new Master();
		master.setProductId(itemUnlockedPhone.getProductId());
		master.setProductName(itemUnlockedPhone.getProductName());
		master.setPurchasePrice(itemUnlockedPhone.getPurchasePrice());
		master.setSalesPrice(itemUnlockedPhone.getSalesPrice());
		master.setTotalPurchasedAmount(itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedAmount());
		master.setTotalPurchasedQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getTotalPurchasedQuantity());
		master.setTotalSalesAmount(itemUnlockedPhone.getUnlockedPhonesInventory().getSalesAmount());
		master.setTotalSalesQuantity(itemUnlockedPhone.getUnlockedPhonesInventory().getSalesQuantity());
		master.setCurrentInventoryAmount(itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventoryAmount());
		master.setCurrentInventory(itemUnlockedPhone.getUnlockedPhonesInventory().getCurrentInventory());
		master.setDeleteYN(itemUnlockedPhone.getDeleteYN());
		masterRepository.save(master);
	}
}
