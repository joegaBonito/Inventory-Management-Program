package com.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.obs.services.MasterService;

@Controller
public class MasterController {
	
	private MasterService masterService;
	
	@Autowired
	public MasterController(MasterService masterService) {
		this.masterService = masterService;
	}
	
	@RequestMapping("/master/list") 
	public String master(Model model) {
		model.addAttribute("masters",masterService.list());
		model.addAttribute("grandTotalPurchasedAmount",masterService.getGrandTotalPurchasedAmount());
		model.addAttribute("grandTotalPurchasedQuantity",masterService.getGrandTotalPurchasedQuantity());
		model.addAttribute("grandTotalSalesAmount",masterService.getGrandTotalSalesAmount());
		model.addAttribute("grandTotalSalesQuantity",masterService.getGrandTotalSalesQuantity());
		model.addAttribute("grandTotalCurrentInventory",masterService.getGrandTotalCurrentInventory());
		model.addAttribute("grandTotalCurrentInventoryAmount",masterService.getGrandTotalCurrentInventoryAmount());
		return "/master/list";
	}
}
