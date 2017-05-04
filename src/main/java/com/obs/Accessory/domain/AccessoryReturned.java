package com.obs.Accessory.domain;

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
@Table(name="AccessoryReturned")
public class AccessoryReturned {
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
	@JoinColumn(name="item_accessory_id")
	private ItemAccessory itemAccessory;

	public AccessoryReturned() {}
	
	public AccessoryReturned(Long id, Date returnedDate, Integer returnedQuantity, ItemAccessory itemAccessory) {
		super();
		this.id = id;
		this.returnedDate = returnedDate;
		this.returnedQuantity = returnedQuantity;
		this.itemAccessory = itemAccessory;
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

	public ItemAccessory getItemAccessory() {
		return itemAccessory;
	}

	public void setItemAccessory(ItemAccessory itemAccessory) {
		this.itemAccessory = itemAccessory;
	}

}
