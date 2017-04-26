package com.obs.controllers;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.obs.domain.ItemAccessory;
import com.obs.domain.UpsOrder;
import com.obs.services.AccessoryInventoryService;
import com.obs.services.AccessoryReceivedQuantityService;
import com.obs.services.CalendarService;
import com.obs.services.ItemAccessoryService;
@Controller	
public class AccessoryInventoryController {
	
	private AccessoryInventoryService accessoryInventoryService;
	private ItemAccessoryService itemAccessoryService;
	private AccessoryReceivedQuantityService accessoryReceivedQuantityService;
	private CalendarService calendarService;
	
	@Autowired
	public AccessoryInventoryController(AccessoryInventoryService accessoryInventoryService, 
										ItemAccessoryService itemAccessoryService,
										AccessoryReceivedQuantityService accessoryReceivedQuantityService,
										CalendarService calendarService) {
		this.accessoryInventoryService = accessoryInventoryService;
		this.itemAccessoryService = itemAccessoryService;
		this.accessoryReceivedQuantityService = accessoryReceivedQuantityService;
		this.calendarService = calendarService;
	}
	
	/*
	 * Replaced by Pageable List View below.
	 */
/*	@RequestMapping("/accessoryInventory/list")
	public String inventoryList(Model model) {
		List<Date> days = calendarService.getAllDays();
		model.addAttribute("days",days);
		model.addAttribute("accessoryReceivedQuantities",accessoryReceivedQuantityService.list());
		model.addAttribute("itemAccessories", itemAccessoryService.productNameList());
		return "/accessoryInventory/list";
	}*/
	
	@RequestMapping("/accessoryInventory/list")
	public String inventoryPagination(Model model, @PageableDefault(value = 5) Pageable pageable) {
		/*
		 * By changing List<?> to Page<?>, the upsOrders variable now has the pagination ability.
		 */
		List<Date> days = calendarService.getAllDays();
		model.addAttribute("days",days);
		model.addAttribute("accessoryReceivedQuantities",accessoryReceivedQuantityService.list());
		Page<ItemAccessory> itemAccessories = itemAccessoryService.findAll(pageable);
		model.addAttribute("itemAccessories", itemAccessories);
		return "/accessoryInventory/list";
	}
	
	@RequestMapping("/accessoryInventory/inputReceivedItem/save/{id}")
	public String inputReceivedItem(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("itemAccessory",itemAccessoryService.get(id));
		return "/accessoryInventory/inputReceivedItem";
		
	}
	
	@RequestMapping(value="/accessoryInventory/save", method=RequestMethod.POST)
	public String inventorySave(@ModelAttribute("itemAccessory") ItemAccessory itemAccessory, Model model) {
		accessoryInventoryService.setPurchasedQuantity(itemAccessory);
		accessoryInventoryService.setPurchasedAmount(itemAccessory);
		accessoryInventoryService.setTotalPurchasedQuantity(itemAccessory);
		accessoryInventoryService.setTotalPurchasedAmount(itemAccessory);
		accessoryInventoryService.setCurrentInventoryAmount(itemAccessory);
		accessoryInventoryService.setCurrentInventory(itemAccessory);
		model.addAttribute("itemAccessories", itemAccessoryService.productNameList());
		return "redirect:/accessoryInventory/list";
	}
	

}
