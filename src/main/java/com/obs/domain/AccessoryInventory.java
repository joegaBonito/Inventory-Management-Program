package com.obs.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="accessory_inventory")
public class AccessoryInventory {
	
	@Id
	@GeneratedValue
	@Column(name="accessory_inventory_id")
	private Long accessoryInventoryId;
	
	@Column(name="current_inventory")
	private int currentInventory;
	
	@Column(name="current_inventory_amount")
	private double currentInventoryAmount;
	
	@Column(name="sales_quantity")
	private int salesQuantity;
	
	@Column(name="sales_amount")
	private double salesAmount;
	
	@Column(name="purchased_quantity")
	private int purchasedQuantity;
	
	@Column(name="received_date")
	@DateTimeFormat(pattern="dd-MMM-yyyy")
	private Date receivedDate = new Date();
	
	@Column(name="purchased_amount")
	private double purchasedAmount;
		
	@Column(name="total_purchased_quantity")
	private int totalPurchasedQuantity;
	
	@Column(name="total_purchased_amount")
	private double totalPurchasedAmount;
	
	@OneToOne(cascade={CascadeType.ALL},mappedBy="accessoryInventory")
	private ItemAccessory itemAccessory;

	public AccessoryInventory() {}

	public AccessoryInventory(Long accessoryInventoryId, int currentInventory, double currentInventoryAmount,
			int salesQuantity, double salesAmount, int purchasedQuantity, Date receivedDate, double purchasedAmount,
			int totalPurchasedQuantity, double totalPurchasedAmount, ItemAccessory itemAccessory) {
		super();
		this.accessoryInventoryId = accessoryInventoryId;
		this.currentInventory = currentInventory;
		this.currentInventoryAmount = currentInventoryAmount;
		this.salesQuantity = salesQuantity;
		this.salesAmount = salesAmount;
		this.purchasedQuantity = purchasedQuantity;
		this.receivedDate = receivedDate;
		this.purchasedAmount = purchasedAmount;
		this.totalPurchasedQuantity = totalPurchasedQuantity;
		this.totalPurchasedAmount = totalPurchasedAmount;
		this.itemAccessory = itemAccessory;
	}

	public Long getAccessoryInventoryId() {
		return accessoryInventoryId;
	}

	public void setAccessoryInventoryId(Long accessoryInventoryId) {
		this.accessoryInventoryId = accessoryInventoryId;
	}

	public int getCurrentInventory() {
		return currentInventory;
	}

	public void setCurrentInventory(int currentInventory) {
		this.currentInventory = currentInventory;
	}

	public double getCurrentInventoryAmount() {
		return currentInventoryAmount;
	}

	public void setCurrentInventoryAmount(double currentInventoryAmount) {
		this.currentInventoryAmount = currentInventoryAmount;
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public int getPurchasedQuantity() {
		return purchasedQuantity;
	}

	public void setPurchasedQuantity(int purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public double getPurchasedAmount() {
		return purchasedAmount;
	}

	public void setPurchasedAmount(double purchasedAmount) {
		this.purchasedAmount = purchasedAmount;
	}

	public int getTotalPurchasedQuantity() {
		return totalPurchasedQuantity;
	}

	public void setTotalPurchasedQuantity(int totalPurchasedQuantity) {
		this.totalPurchasedQuantity = totalPurchasedQuantity;
	}

	public double getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}

	public void setTotalPurchasedAmount(double totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}

	public ItemAccessory getItemAccessory() {
		return itemAccessory;
	}

	public void setItemAccessory(ItemAccessory itemAccessory) {
		this.itemAccessory = itemAccessory;
	}
}
