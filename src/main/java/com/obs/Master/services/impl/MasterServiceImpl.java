package com.obs.Master.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Accessory.services.impl.ItemAccessoryServiceImpl;
import com.obs.Master.domain.Master;
import com.obs.Master.repositories.MasterRepository;
import com.obs.Master.services.MasterService;
import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.services.impl.ItemUnlockedPhonesServiceImpl;

@Service
public class MasterServiceImpl implements MasterService {
	
	private MasterRepository masterRepository;
	
	@Autowired
	public MasterServiceImpl(MasterRepository masterRepository) {
		this.masterRepository = masterRepository;;
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
			temp += master.getPurchasedQuantity()*master.getPurchasedAmount();
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total purchased quantity on /master/index.html 
	 */
	public int getGrandTotalPurchasedQuantity() {
		int temp = 0;
		for(Master master : list()) {
			temp += master.getPurchasedQuantity();
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total sales amount on /master/index.html 
	 */
	public double getGrandTotalSalesAmount() {
		double temp = 0;
		for(Master master : list()) {
			temp += master.getSalesAmount();
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total sales quantity on /master/index.html 
	 */
	public int getGrandTotalSalesQuantity() {
		int temp = 0;
		for(Master master : list()) {
			temp += master.getSalesQuantity();
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total current inventory on /master/index.html 
	 */
	public int getGrandTotalCurrentInventory() {
		int temp = 0;
		for(Master master : list()) {
			temp += master.getCurrentInventory();
		}
		return temp;
	}
	
	/*
	 * Retrieves the grand total of every product's total current amount on /master/index.html 
	 */
	
	public double getGrandTotalCurrentInventoryAmount() {
		double temp = 0;
		for(Master master : list()) {
			temp += master.getCurrentInventoryAmount();
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
