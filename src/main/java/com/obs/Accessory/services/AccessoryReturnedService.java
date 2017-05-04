package com.obs.Accessory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Accessory.domain.AccessoryReturned;
import com.obs.Accessory.repositories.AccessoryReturnedRepository;

@Service
public class AccessoryReturnedService {
	
	private AccessoryReturnedRepository accessoryReturnedRepository;
	
	@Autowired
	public AccessoryReturnedService(AccessoryReturnedRepository accessoryReturnedRepository) {
		this.accessoryReturnedRepository = accessoryReturnedRepository;
	}
	
	public void save(AccessoryReturned accessoryReturned) {
		accessoryReturnedRepository.save(accessoryReturned);
	}
}