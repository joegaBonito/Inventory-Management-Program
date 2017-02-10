package com.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obs.domain.UpsOrder;
import com.obs.services.ItemAccessoryService;
import com.obs.services.UpsService;

@Controller
public class UpsController {
	
	@Autowired
	private UpsService upsService;
	
	@Autowired
	private ItemAccessoryService itemAccessoryService;
	
	public UpsController(UpsService upsService) {
		this.upsService = upsService;
	}
	
	@RequestMapping("/ups/create")
	public String upsCreate(Model model) {
		model.addAttribute("upsOrder", new UpsOrder());
		model.addAttribute("itemAccessories", itemAccessoryService.list());
		return "/ups/inputForm";
	}
	
	@RequestMapping(value="/ups/save",method=RequestMethod.POST)
	public String upsSave(@ModelAttribute("upsOrder") UpsOrder upsOrder){
		upsService.upsAccSetProductName(upsOrder);
		upsService.upsSetShippingMethod(upsOrder);
		upsService.save(upsOrder);
		return "redirect:/ups/list";
	} 
	
	@RequestMapping("/ups/list")
	public String upsList(Model model) {
		model.addAttribute("upsOrders",upsService.list());
		return "/ups/list";
	}
	@RequestMapping("/ups/edit/{systemId}")
	public String upsEdit(@PathVariable("systemId") Long systemId, Model model) {
		model.addAttribute("upsOrder",upsService.get(systemId));
		model.addAttribute("itemAccessories", itemAccessoryService.list());
		return "/ups/inputForm";
	}
	@RequestMapping("/ups/delete/{systemId}")
	public String upsDelete(@PathVariable("systemId") Long systemId, Model model) {
		upsService.delete(systemId);
		return "redirect:/ups/list";
	}
	
	@RequestMapping("/ups/deleteAll")
	public String upsDeletAll() {
		upsService.deleteAll();
		return "redirect:/ups/list";
	}
	
}
