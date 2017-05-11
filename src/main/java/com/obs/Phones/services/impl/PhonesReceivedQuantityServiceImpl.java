package com.obs.Phones.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Phones.domain.PhonesReceivedQuantity;
import com.obs.Phones.repositories.PhonesReceivedQuantityRepository;
import com.obs.Phones.services.PhonesReceviedQuantityService;

@Service
public class PhonesReceivedQuantityServiceImpl implements PhonesReceviedQuantityService {
	@Autowired
	private PhonesReceivedQuantityRepository phonesReceivedQuantityRepository;
	
	public PhonesReceivedQuantityServiceImpl(PhonesReceivedQuantityRepository phonesReceivedQuantityRepository) {
		this.phonesReceivedQuantityRepository = phonesReceivedQuantityRepository;
	}
	
	public List<PhonesReceivedQuantity> list() {
		return phonesReceivedQuantityRepository.findByOrderByReceivedDate();
	}
	
	public void save(PhonesReceivedQuantity phonesReceivedQuantity) {
		phonesReceivedQuantityRepository.save(phonesReceivedQuantity);
	}

}
