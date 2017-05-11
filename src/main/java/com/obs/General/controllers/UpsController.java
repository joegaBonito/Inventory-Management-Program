package com.obs.General.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obs.Accessory.services.impl.ItemAccessoryServiceImpl;
import com.obs.General.domain.UpsOrder;
import com.obs.General.services.impl.UpsServiceImpl;

@Controller
public class UpsController {
	
	@Autowired
	private UpsServiceImpl upsServiceImpl;
	
	@Autowired
	private ItemAccessoryServiceImpl itemAccessoryServiceImpl;
	
	public UpsController(UpsServiceImpl upsServiceImpl) {
		this.upsServiceImpl = upsServiceImpl;
	}
	
	@RequestMapping("/ups/create")
	public String upsCreate(Model model) {
		model.addAttribute("upsOrder", new UpsOrder());
		model.addAttribute("itemAccessories", itemAccessoryServiceImpl.list());
		return "/ups/inputForm";
	}
	
	@RequestMapping(value="/ups/save",method=RequestMethod.POST)
	public String upsSave(@ModelAttribute("upsOrder") UpsOrder upsOrder){
		upsServiceImpl.upsAccSetProductName(upsOrder);
		upsServiceImpl.upsSetShippingMethod(upsOrder);
		upsServiceImpl.save(upsOrder);
		return "redirect:/ups/list";
	} 
	
	@RequestMapping("/ups/list")
	public String upsList(Model model, @PageableDefault(value = 10) Pageable pageable) {
		/*
		 * By changing List<?> to Page<?>, the upsOrders variable now has the pagination ability.
		 */
		Page<UpsOrder> upsOrders = upsServiceImpl.findAll(pageable);
		model.addAttribute("upsOrders", upsOrders);
		return "/ups/list";
	}
	
	@RequestMapping("/ups/edit/{systemId}")
	public String upsEdit(@PathVariable("systemId") Long systemId, Model model) {
		model.addAttribute("upsOrder",upsServiceImpl.get(systemId));
		model.addAttribute("itemAccessories", itemAccessoryServiceImpl.list());
		return "/ups/inputForm";
	}
	@RequestMapping("/ups/delete/{systemId}")
	public String upsDelete(@PathVariable("systemId") Long systemId, Model model) {
		upsServiceImpl.delete(systemId);
		return "redirect:/ups/list";
	}
	
	@RequestMapping("/ups/deleteAll")
	public String upsDeletAll() {
		upsServiceImpl.deleteAll();
		return "redirect:/ups/list";
	}
	
}
