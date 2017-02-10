package com.obs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.domain.ReceivedQuantity;
import com.obs.repositories.ReceivedQuantityRepository;

@Service
public class ReceivedQuantityService {
		@Autowired
		private ReceivedQuantityRepository receivedQuantityRepository;
		
		public ReceivedQuantityService(ReceivedQuantityRepository receivedQuantityRepository) {
			this.receivedQuantityRepository = receivedQuantityRepository;
		}
		
		public List<ReceivedQuantity> list() {
			return receivedQuantityRepository.findByOrderByReceivedDate();
		}
		
		public ReceivedQuantity save(ReceivedQuantity receivedQuantity) {
			return receivedQuantityRepository.save(receivedQuantity);
		}
}
