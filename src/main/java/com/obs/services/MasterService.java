package com.obs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.domain.Master;
import com.obs.repositories.MasterRepository;

@Service
public class MasterService {
	
	private MasterRepository masterRepository;
	
	@Autowired
	public MasterService(MasterRepository masterRepository) {
		this.masterRepository = masterRepository;
	}
	
	public List<Master> list () {
		return masterRepository.findByOrderById();
	}
	
}
