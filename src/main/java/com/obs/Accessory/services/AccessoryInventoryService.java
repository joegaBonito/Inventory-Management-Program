package com.obs.Accessory.services;

import com.obs.Accessory.domain.ItemAccessory;

public interface AccessoryInventoryService {
	public void setSalesQuantity();
	public void setSalesAmount();
	public void setSalesAmountAfterReturn(ItemAccessory itemAccessory);
	public void setSalesQuantityAfterReturn(ItemAccessory itemAccessory);
	public void setCurrentInventory(ItemAccessory itemAccessory);
	public void setPurchasedQuantity(ItemAccessory itemAccessory);
	public void setTotalPurchasedQuantity(ItemAccessory itemAccessory);
	public void setPurchasedAmount(ItemAccessory itemAccessory);
	public void setTotalPurchasedAmount(ItemAccessory itemAccessory);
	public void setCurrentInventoryAmount(ItemAccessory itemAccessory);
	public void setReturnedInventory(ItemAccessory itemAccessory);
}
