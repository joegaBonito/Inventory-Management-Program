package com.obs.Phones.services;

import java.util.List;

import com.obs.Phones.domain.PhonesReceivedQuantity;

public interface PhonesReceviedQuantityService {
	public List<PhonesReceivedQuantity> list();
	public void save(PhonesReceivedQuantity phonesReceivedQuantity);
}
