package com.obs.Accessory.controllers;

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

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Accessory.services.impl.AccessoryInventoryServiceImpl;
import com.obs.Accessory.services.impl.AccessoryReceivedQuantityServiceImpl;
import com.obs.Accessory.services.impl.ItemAccessoryServiceImpl;
import com.obs.General.services.impl.CalendarServiceImpl;

@Controller	
public class AccessoryInventoryController {
	
	private AccessoryInventoryServiceImpl accessoryInventoryServiceImpl;
	private ItemAccessoryServiceImpl itemAccessoryServiceImpl;
	private AccessoryReceivedQuantityServiceImpl accessoryReceivedQuantityServiceImpl;
	private CalendarServiceImpl calendarServiceImpl;
	
	@Autowired
	public AccessoryInventoryController(AccessoryInventoryServiceImpl accessoryInventoryServiceImpl, 
										ItemAccessoryServiceImpl itemAccessoryServiceImpl,
										AccessoryReceivedQuantityServiceImpl accessoryReceivedQuantityServiceImpl,
										CalendarServiceImpl calendarServiceImpl) {
		this.accessoryInventoryServiceImpl = accessoryInventoryServiceImpl;
		this.itemAccessoryServiceImpl = itemAccessoryServiceImpl;
		this.accessoryReceivedQuantityServiceImpl = accessoryReceivedQuantityServiceImpl;
		this.calendarServiceImpl = calendarServiceImpl;
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
		List<Date> days = calendarServiceImpl.getAllDays();
		model.addAttribute("days",days);
		model.addAttribute("accessoryReceivedQuantities",accessoryReceivedQuantityServiceImpl.list());
		Page<ItemAccessory> itemAccessories = itemAccessoryServiceImpl.findByDeleteYNPageable(pageable);
		model.addAttribute("itemAccessories", itemAccessories);
		return "/accessoryInventory/list";
	}
	
	@RequestMapping("/accessoryInventory/inputReceivedItem/save/{id}")
	public String inputReceivedItem(@PathVariable("id") Long id, Model model) {
		model.addAttribute("itemAccessory",itemAccessoryServiceImpl.get(id));
		return "/accessoryInventory/inputReceivedItem";
		
	}
	
	@RequestMapping("/accessoryInventory/inputReturnedItem/save/{id}")
	public String inputReturnedItem(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("itemAccessory", itemAccessoryServiceImpl.get(id));
		return "/accessoryInventory/inputReturnedItem";
	}
	
	@RequestMapping(value="/accessoryInventory/save", method=RequestMethod.POST)
	public String inventorySave(@ModelAttribute("itemAccessory") ItemAccessory itemAccessory, Model model) {
		accessoryInventoryServiceImpl.setPurchasedQuantity(itemAccessory);
		accessoryInventoryServiceImpl.setPurchasedAmount(itemAccessory);
		accessoryInventoryServiceImpl.setTotalPurchasedQuantity(itemAccessory);
		accessoryInventoryServiceImpl.setTotalPurchasedAmount(itemAccessory);
		accessoryInventoryServiceImpl.setCurrentInventoryAmount(itemAccessory);
		accessoryInventoryServiceImpl.setCurrentInventory(itemAccessory);
		accessoryInventoryServiceImpl.setSalesAmount();
		accessoryInventoryServiceImpl.setSalesQuantity();
		model.addAttribute("itemAccessories", itemAccessoryServiceImpl.productNameList());
		return "redirect:/accessoryInventory/list";
	}
	
	@RequestMapping(value="/accessoryInventory/saveReturns", method=RequestMethod.POST)
	public String saveReturns(@ModelAttribute("itemAccessory") ItemAccessory itemAccessory, Model model) {
		accessoryInventoryServiceImpl.setReturnedInventory(itemAccessory);
		accessoryInventoryServiceImpl.setSalesAmountAfterReturn(itemAccessory);
		accessoryInventoryServiceImpl.setSalesQuantityAfterReturn(itemAccessory);
		return "redirect:/accessoryInventory/list";
	}
	

}
