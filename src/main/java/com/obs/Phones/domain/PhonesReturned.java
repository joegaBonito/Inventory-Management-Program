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

import com.obs.Phones.domain.ItemUnlockedPhone;

@Entity
@Table(name="PhonesReturned")
public class PhonesReturned {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="returned_date")
	@DateTimeFormat(pattern="dd-MMM-yyyy")
	private Date returnedDate;
	
	@Column(name="returned_quantity")
	private Integer returnedQuantity;
	
	@ManyToOne
	@JoinColumn(name="item_unlocked_phone_id")
	private ItemUnlockedPhone itemUnlockedPhone;

	public PhonesReturned() {}

	public PhonesReturned(Long id, Date returnedDate, Integer returnedQuantity, ItemUnlockedPhone itemUnlockedPhone) {
		super();
		this.id = id;
		this.returnedDate = returnedDate;
		this.returnedQuantity = returnedQuantity;
		this.itemUnlockedPhone = itemUnlockedPhone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Integer getReturnedQuantity() {
		return returnedQuantity;
	}

	public void setReturnedQuantity(Integer returnedQuantity) {
		this.returnedQuantity = returnedQuantity;
	}

	public ItemUnlockedPhone getItemUnlockedPhone() {
		return itemUnlockedPhone;
	}

	public void setItemUnlockedPhone(ItemUnlockedPhone itemUnlockedPhone) {
		this.itemUnlockedPhone = itemUnlockedPhone;
	}
}
