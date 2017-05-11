package com.obs.Accessory.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Accessory.domain.AccessoryReceivedQuantity;
import com.obs.Accessory.repositories.AccessoryReceivedQuantityRepository;
import com.obs.Accessory.services.AccessoryReceivedQuantityService;

@Service
public class AccessoryReceivedQuantityServiceImpl implements AccessoryReceivedQuantityService {
		@Autowired
		private AccessoryReceivedQuantityRepository accessoryReceivedQuantityRepository;
		
		public AccessoryReceivedQuantityServiceImpl(AccessoryReceivedQuantityRepository accessoryReceivedQuantityRepository) {
			this.accessoryReceivedQuantityRepository = accessoryReceivedQuantityRepository;
		}
		
		public List<AccessoryReceivedQuantity> list() {
			return accessoryReceivedQuantityRepository.findByOrderByReceivedDate();
		}
		
		public AccessoryReceivedQuantity save(AccessoryReceivedQuantity accessoryReceivedQuantity) {
			return accessoryReceivedQuantityRepository.save(accessoryReceivedQuantity);
		}
}
