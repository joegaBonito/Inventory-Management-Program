package com.obs.Phones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Phones.domain.PhonesReceivedQuantity;
import com.obs.Phones.repositories.PhonesReceivedQuantityRepository;

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
	
	public void save(PhonesReceivedQuantity phonesReceivedQuantity) {
		phonesReceivedQuantityRepository.save(phonesReceivedQuantity);
	}

}
