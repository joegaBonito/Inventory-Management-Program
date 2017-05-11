package com.obs.Master.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.obs.Master.services.impl.MasterServiceImpl;

@Controller
public class MasterController {
	
	private MasterServiceImpl masterServiceImpl;
	
	@Autowired
	public MasterController(MasterServiceImpl masterServiceImpl) {
		this.masterServiceImpl = masterServiceImpl;
	}
	
	@RequestMapping("/master/list") 
	public String master(Model model) {
		model.addAttribute("masters",masterServiceImpl.list());
		model.addAttribute("grandTotalPurchasedAmount",masterServiceImpl.getGrandTotalPurchasedAmount());
		model.addAttribute("grandTotalPurchasedQuantity",masterServiceImpl.getGrandTotalPurchasedQuantity());
		model.addAttribute("grandTotalSalesAmount",masterServiceImpl.getGrandTotalSalesAmount());
		model.addAttribute("grandTotalSalesQuantity",masterServiceImpl.getGrandTotalSalesQuantity());
		model.addAttribute("grandTotalCurrentInventory",masterServiceImpl.getGrandTotalCurrentInventory());
		model.addAttribute("grandTotalCurrentInventoryAmount",masterServiceImpl.getGrandTotalCurrentInventoryAmount());
		return "/master/list";
	}
}
