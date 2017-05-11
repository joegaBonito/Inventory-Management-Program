package com.obs.Master.services;

import java.util.List;

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Master.domain.Master;
import com.obs.Phones.domain.ItemUnlockedPhone;

public interface MasterService {
	public void add(Master master);
	public void delete(String productId);
	public List<Master> list ();
	public double getGrandTotalPurchasedAmount();
	public int getGrandTotalPurchasedQuantity();
	public double getGrandTotalSalesAmount();
	public int getGrandTotalSalesQuantity();
	public int getGrandTotalCurrentInventory();
	public double getGrandTotalCurrentInventoryAmount();
	public Master getMasterById(long id);
	public void saveItemAccessory(ItemAccessory itemAccessory);
	public void saveItemUnlockedPhone(ItemUnlockedPhone itemUnlockedPhone);
	
}
