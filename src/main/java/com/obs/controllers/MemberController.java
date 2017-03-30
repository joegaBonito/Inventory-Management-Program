package com.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obs.domain.Member;
import com.obs.services.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("members", memberService.list());
		return "members/list";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/view")
	public String view(Model model){
		return "members/view";
	}
	
	@RequestMapping("/createMember")
	public String createAuthor(Model model) {
		model.addAttribute("member", new Member());
		return "auth/createaccount";
	}
	
	@RequestMapping(value = "/processForm", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("member") Member member, Model model) {
		/*
		 * Checks if a password == confirm_password before a member gets created.
		 */
		if (member.getEmail().equals("")) {
			return "redirect:/members/createMember";
		}
		if (member.getPassword().equals("")) {
			return "redirect:/members/createMember";
		}
		if (member.getConfirmPassword().equals("")) {
			return "redirect:/members/createMember";
		}
		if (!member.getPassword().equals(member.getConfirmPassword())) {
			return "redirect:/members/createMember";
		}
		memberService.save(member);
		return "redirect:/";
	}
}
