package com.obs.Master.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="master")
public class Master {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="delete_YN")
	private char deleteYN;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="purchase_price")
	private double purchasePrice;
	
	@Column(name="sales_price")
	private double salesPrice;
	
	@Column(name="sales_amount")
	private double salesAmount;
	
	@Column(name="sales_quantity")
	private Integer salesQuantity;
	
	@Column(name="total_purchased_amount")
	private double totalPurchasedAmount;
	
	@Column(name="total_purchased_quantity")
	private Integer totalPurchasedQuantity;
	
	@Column(name="total_sales_amount")
	private double totalSalesAmount;
	
	@Column(name="total_sales_quantity")
	private Integer totalSalesQuantity;
	
	@Column(name="current_inventory_amount")
	private double currentInventoryAmount;
	
	@Column(name="current_inventory")
	private Integer currentInventory;
	
	@Column(name="purchased_quantity")
	private Integer purchasedQuantity;
	
	@Column(name="purchased_amount")
	private double purchasedAmount;
	
	
	public Master() {}


	public Master(Long id, char deleteYN, String productId, String productName, double purchasePrice, double salesPrice,
			double salesAmount, Integer salesQuantity, double totalPurchasedAmount, Integer totalPurchasedQuantity,
			double totalSalesAmount, Integer totalSalesQuantity, double currentInventoryAmount,
			Integer currentInventory, Integer purchasedQuantity, double purchasedAmount) {
		super();
		this.id = id;
		this.deleteYN = deleteYN;
		this.productId = productId;
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.salesAmount = salesAmount;
		this.salesQuantity = salesQuantity;
		this.totalPurchasedAmount = totalPurchasedAmount;
		this.totalPurchasedQuantity = totalPurchasedQuantity;
		this.totalSalesAmount = totalSalesAmount;
		this.totalSalesQuantity = totalSalesQuantity;
		this.currentInventoryAmount = currentInventoryAmount;
		this.currentInventory = currentInventory;
		this.purchasedQuantity = purchasedQuantity;
		this.purchasedAmount = purchasedAmount;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	public double getSalesPrice() {
		return salesPrice;
	}


	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}


	public double getSalesAmount() {
		return salesAmount;
	}


	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}


	public Integer getSalesQuantity() {
		return salesQuantity;
	}


	public void setSalesQuantity(Integer salesQuantity) {
		this.salesQuantity = salesQuantity;
	}


	public double getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}


	public void setTotalPurchasedAmount(double totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}


	public Integer getTotalPurchasedQuantity() {
		return totalPurchasedQuantity;
	}


	public void setTotalPurchasedQuantity(Integer totalPurchasedQuantity) {
		this.totalPurchasedQuantity = totalPurchasedQuantity;
	}


	public double getTotalSalesAmount() {
		return totalSalesAmount;
	}


	public void setTotalSalesAmount(double totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}


	public Integer getTotalSalesQuantity() {
		return totalSalesQuantity;
	}


	public void setTotalSalesQuantity(Integer totalSalesQuantity) {
		this.totalSalesQuantity = totalSalesQuantity;
	}


	public double getCurrentInventoryAmount() {
		return currentInventoryAmount;
	}


	public void setCurrentInventoryAmount(double currentInventoryAmount) {
		this.currentInventoryAmount = currentInventoryAmount;
	}


	public Integer getCurrentInventory() {
		return currentInventory;
	}


	public void setCurrentInventory(Integer currentInventory) {
		this.currentInventory = currentInventory;
	}


	public Integer getPurchasedQuantity() {
		return purchasedQuantity;
	}


	public void setPurchasedQuantity(Integer purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}


	public double getPurchasedAmount() {
		return purchasedAmount;
	}


	public void setPurchasedAmount(double purchasedAmount) {
		this.purchasedAmount = purchasedAmount;
	}
}
