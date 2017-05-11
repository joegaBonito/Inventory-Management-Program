package com.obs.Accessory.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Accessory.domain.AccessoryReturned;
import com.obs.Accessory.repositories.AccessoryReturnedRepository;
import com.obs.Accessory.services.AccessoryReturnedService;

@Service
public class AccessoryReturnedServiceImpl implements AccessoryReturnedService {
	
	private AccessoryReturnedRepository accessoryReturnedRepository;
	
	@Autowired
	public AccessoryReturnedServiceImpl(AccessoryReturnedRepository accessoryReturnedRepository) {
		this.accessoryReturnedRepository = accessoryReturnedRepository;
	}
	
	public void save(AccessoryReturned accessoryReturned) {
		accessoryReturnedRepository.save(accessoryReturned);
	}
}