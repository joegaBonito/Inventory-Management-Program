package com.obs.Phones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.Phones.domain.PhonesReturned;
import com.obs.Phones.repositories.PhonesReturnedRepository;

@Service
public class PhonesReturnedService {
	
	private PhonesReturnedRepository phonesReturnedRepository;
	
	@Autowired
	public PhonesReturnedService(PhonesReturnedRepository phonesReturnedRepository) {
		this.phonesReturnedRepository = phonesReturnedRepository;
	}
	
	public void save(PhonesReturned phonesReturned) {
		phonesReturnedRepository.save(phonesReturned);
	}
}