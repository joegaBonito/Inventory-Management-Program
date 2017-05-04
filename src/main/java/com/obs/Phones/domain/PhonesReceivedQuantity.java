package com.obs.Phones.domain;

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
@Table(name="PhonesReceivedQuantity")
public class PhonesReceivedQuantity {
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
	@JoinColumn(name="item_unlocked_phone_id")
	private ItemUnlockedPhone itemUnlockedPhone;

	public PhonesReceivedQuantity() {}
	
	public PhonesReceivedQuantity(Long receivedQuantityId, Date receivedDate, int purchasedQuantity, 
			ItemUnlockedPhone itemUnlockedPhone) {
		super();
		this.receivedQuantityId = receivedQuantityId;
		this.receivedDate = receivedDate;
		this.purchasedQuantity = purchasedQuantity;
		this.itemUnlockedPhone = itemUnlockedPhone;
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

	public ItemUnlockedPhone getItemUnlockedPhone() {
		return itemUnlockedPhone;
	}

	public void setItemUnlockedPhone(ItemUnlockedPhone itemUnlockedPhone) {
		this.itemUnlockedPhone = itemUnlockedPhone;
	}
}
