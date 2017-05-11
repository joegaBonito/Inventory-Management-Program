package com.obs.Phones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Phones.domain.PhonesReturned;
import com.obs.Phones.repositories.PhonesReturnedRepository;
import com.obs.Phones.services.PhonesReturnedService;

@Service
public class PhonesReturnedServiceImpl implements PhonesReturnedService {
	
	private PhonesReturnedRepository phonesReturnedRepository;
	
	@Autowired
	public PhonesReturnedServiceImpl(PhonesReturnedRepository phonesReturnedRepository) {
		this.phonesReturnedRepository = phonesReturnedRepository;
	}
	
	public void save(PhonesReturned phonesReturned) {
		phonesReturnedRepository.save(phonesReturned);
	}
}