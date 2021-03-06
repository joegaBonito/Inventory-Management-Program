package com.obs.Member.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.obs.General.domain.Role;
import com.obs.Member.domain.Member;
import com.obs.Member.repositories.MemberRepository;
import com.obs.Member.services.MemberService;

@Service
public class MemberServiceImpl implements UserDetailsService, MemberService {
	
	
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	 
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public MemberServiceImpl() {}

	public List<Member> list() {
		return memberRepository.findAllByOrderByEmail();
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(username);
		if(member != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if (member.getRole()==Role.ADMIN) 
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			else if (member.getRole()==Role.USER) 
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			else 
				authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
			return new User(member.getEmail(),member.getPassword(),authorities); 
		} 
		throw new UsernameNotFoundException("User '" + username + "' not found.");
	}

	public void save(Member member) {
		/*
		 * Changes the password into Hash before saving into DB.
		 */
		member.setRole(Role.GUEST);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setConfirmPassword(member.getPassword());
		memberRepository.save(member);
	}
}
