package com.obs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.domain.PhonesReceivedQuantity;
import com.obs.repositories.PhonesReceivedQuantityRepository;

@Service
public class PhonesReceivedQuantityService {
	@Autowired
	private PhonesReceivedQuantityRepository phonesReceivedQuantityRepository;
	
	public PhonesReceivedQuantityService(PhonesReceivedQuantityRepository phonesReceivedQuantityRepository) {
		this.phonesReceivedQuantityRepository = phonesReceivedQuantityRepository;
	}
	
	public List<PhonesReceivedQuantity> list() {
		return phonesReceivedQuantityRepository.findByOrderByReceivedDate();
	}
	
	public PhonesReceivedQuantity save(PhonesReceivedQuantity phonesReceivedQuantity) {
		return phonesReceivedQuantityRepository.save(phonesReceivedQuantity);
	}
}
