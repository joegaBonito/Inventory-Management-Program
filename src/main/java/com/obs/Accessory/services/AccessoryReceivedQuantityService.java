package com.obs.Accessory.services;

import java.util.List;

import com.obs.Accessory.domain.AccessoryReceivedQuantity;

public interface AccessoryReceivedQuantityService {
	public List<AccessoryReceivedQuantity> list();
	public AccessoryReceivedQuantity save(AccessoryReceivedQuantity accessoryReceivedQuantity);
}
