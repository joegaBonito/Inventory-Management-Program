package com.obs.Accessory.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.obs.Accessory.domain.ItemAccessory;

public interface ItemAccessoryService {
	public List<ItemAccessory> list();
	public List<ItemAccessory> productNameList();
	public ItemAccessory save(ItemAccessory itemAccessory);
	public ItemAccessory get(Long itemAccessoryId);
	public void deleteYN(Long itemAccessoryId);
	public String getProductId(Long itemAccessoryId);
	public String getProductName(Long itemAccessoryId);
	public double getLength(Long itemAccessoryId);
	public double getHeight(Long itemAccessoryId);
	public double getWidth(Long itemAccessoryId);
	public double getWeight(Long itemAccessoryId);
	public Page<ItemAccessory> findByDeleteYNPageableByProductId(Pageable pageable);
}
