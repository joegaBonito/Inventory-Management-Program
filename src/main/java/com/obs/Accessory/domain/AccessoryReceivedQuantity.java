package com.obs.Accessory.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="AccessoryReceivedQuantity")
public class AccessoryReceivedQuantity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="received_quantity_id")
	private Long receivedQuantityId;
	
	@Column(name="received_date")
	@DateTimeFormat(pattern="dd-MMM-yyyy")
	private Date receivedDate;
	
	@Column(name="purchased_quantity")
	private int purchasedQuantity;
	
	@ManyToOne
	@JoinColumn(name="item_accessory_id")
	private ItemAccessory itemAccessory;

	public AccessoryReceivedQuantity() {}
	
	public AccessoryReceivedQuantity(Long receivedQuantityId, Date receivedDate, int purchasedQuantity,
			ItemAccessory itemAccessory) {
		super();
		this.receivedQuantityId = receivedQuantityId;
		this.receivedDate = receivedDate;
		this.purchasedQuantity = purchasedQuantity;
		this.itemAccessory = itemAccessory;
	}

	public Long getReceivedQuantityId() {
		return receivedQuantityId;
	}

	public void setReceivedQuantityId(Long receivedQuantityId) {
		this.receivedQuantityId = receivedQuantityId;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public int getPurchasedQuantity() {
		return purchasedQuantity;
	}

	public void setPurchasedQuantity(int purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}

	public ItemAccessory getItemAccessory() {
		return itemAccessory;
	}

	public void setItemAccessory(ItemAccessory itemAccessory) {
		this.itemAccessory = itemAccessory;
	}
}
