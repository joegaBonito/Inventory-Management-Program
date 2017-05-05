package com.obs.Member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.obs.General.domain.Role;

@Entity
@Table(name="member")
public class Member {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@NotNull(message="Email is required")
	@Size(min=1, message="Email is required")
	@Column(name="email")
	private String email;
	
	@NotNull(message="Password is required")
	@Size(min=1, message="Password is required")
	@Column(name="password")
	private String password;
	
	@NotNull(message="Confirming Password is required")
	@Size(min=1, message="Confirming Password is required")
	@Column(name="confirm_password")
	private String confirmPassword;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	public Member() {
		super();
	}

	public Member(Long id, String email, String password, String confirmPassword, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	} 
}
