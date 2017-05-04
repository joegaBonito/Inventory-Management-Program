package com.obs.Master.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Phones.domain.ItemUnlockedPhone;

@Entity
@Table(name="master")
public class Master {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="accessory")
	private ItemAccessory accessory;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="phone")
	private ItemUnlockedPhone phone;

	@Column(name="delete_YN")
	private char deleteYN;
	
	@Column(name="product_id")
	private String productId;
	
	public Master() {}
	
	public Master(Long id, ItemAccessory accessory, ItemUnlockedPhone phone, char deleteYN, String productId) {
		super();
		this.id = id;
		this.accessory = accessory;
		this.phone = phone;
		this.deleteYN = deleteYN;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemAccessory getAccessory() {
		return accessory;
	}

	public void setAccessory(ItemAccessory accessory) {
		this.accessory = accessory;
	}

	public ItemUnlockedPhone getPhone() {
		return phone;
	}

	public void setPhone(ItemUnlockedPhone phone) {
		this.phone = phone;
	}
	public char getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
