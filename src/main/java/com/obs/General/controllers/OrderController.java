package com.obs.General.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.obs.Accessory.services.impl.ItemAccessoryServiceImpl;
import com.obs.General.domain.OrderEntity;
import com.obs.General.services.impl.OrderServiceImpl;

@Controller
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private ItemAccessoryServiceImpl itemAccessoryServiceImpl;
	
	public OrderController(OrderServiceImpl orderServiceImpl, ItemAccessoryServiceImpl itemAccessoryServiceImpl) {
		super();
		this.orderServiceImpl = orderServiceImpl;
		this.itemAccessoryServiceImpl = itemAccessoryServiceImpl;
	}
	
	@RequestMapping("/order/create") 
	public String orderCreate(Model model) {
		model.addAttribute("orderEntity", new OrderEntity());
		model.addAttribute("itemAccessories",itemAccessoryServiceImpl.list());
		return "/order/inputForm";
	}
	
	@RequestMapping(value = "/order/save", method=RequestMethod.POST)
	public String orderSave(@ModelAttribute("orderEntity") @Valid OrderEntity orderEntity, BindingResult bindingResultOrderEntity) {
/*		if (bindingResultOrderEntity.hasErrors()) {
	        return "index";
	    }*/
		orderServiceImpl.save(orderEntity);
		return "redirect:/order/list";
	}
	
	@RequestMapping("/order/list")
	public String orderList(Model model) {
		model.addAttribute("orderEntities", orderServiceImpl.list());
		return "/order/list";
	}
	
	@RequestMapping("/order/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
						orderServiceImpl.delete(id);
		redirectAttrs.addFlashAttribute("message", "Order was deleted!");
		return "redirect:/order/list";
	}
}
 