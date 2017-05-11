package com.obs.General.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.obs.General.domain.UpsOrder;

public interface UpsService {
	public List<UpsOrder> list();
	public Page<UpsOrder> findAll(Pageable pageable);
	public List<UpsOrder> systemIdList();
	public void save(UpsOrder upsOrder);
	public void delete(Long systemId);
	public UpsOrder get(Long systemId);
	public void deleteAll();
	public void upsAccSetProductName(UpsOrder upsOrder);
	public void upsUpSetProductName(UpsOrder upsOrder);
	public void upsSetShippingMethod(UpsOrder upsOrder);
	public void upsSetService(UpsOrder upsOrder);
}
