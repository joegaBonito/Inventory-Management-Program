package com.obs.Phones.services;

import com.obs.Phones.domain.ItemUnlockedPhone;

public interface PhoneInventoryService {
	public void setSalesQuantity();
	public void setSalesAmount();
	public void setSalesAmountAfterReturn(ItemUnlockedPhone itemUnlockedPhone);
	public void setSalesQuantityAfterReturn(ItemUnlockedPhone itemUnlockedPhone);
	public void setCurrentInventory(ItemUnlockedPhone itemUnlockedPhone);
	public void setPurchasedQuantity(ItemUnlockedPhone itemUnlockedPhone);
	public void setTotalPurchasedQuantity(ItemUnlockedPhone itemUnlockedPhone);
	public void setPurchasedAmount(ItemUnlockedPhone itemUnlockedPhone);
	public void setTotalPurchasedAmount(ItemUnlockedPhone itemUnlockedPhone);
	public void setCurrentInventoryAmount(ItemUnlockedPhone itemUnlockedPhone);
	public void setReturnedInventory(ItemUnlockedPhone itemUnlockedPhone);
}
