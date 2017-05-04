package com.obs.Phones.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.obs.Master.domain.Master;
import com.obs.Phones.domain.PhonesReturned;

@Entity
@Table(name="ItemUnlockedPhone")
public class ItemUnlockedPhone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="item_unlocked_phone_id")
	private Long itemUnlockedPhoneId;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="purchase_price")
	private double purchasePrice;
	
	@Column(name="sales_price") 
	private double salesPrice;
	
	@Column(name="up_length")
	private double upLength;
	
	@Column(name="up_height")
	private double upHeight;
	
	@Column(name="up_width")
	private double upWidth;
	
	@Column(name="up_weight")
	private double upWeight;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="unlocked_phone_inventory_id")
	private UnlockedPhonesInventory unlockedPhonesInventory;

	@OneToMany(cascade={CascadeType.ALL},mappedBy="itemUnlockedPhone")
	private List<PhonesReceivedQuantity> phonesReceivedQuantity;
	
	@OneToOne(cascade={CascadeType.ALL},mappedBy="phone")
	private Master master;
	
	@Column(name="delete_YN")
	private char deleteYN;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="itemUnlockedPhone")
	private List<PhonesReturned> phonesReturned;
	
	public ItemUnlockedPhone(Long itemUnlockedPhoneId, String productId, String productName, double purchasePrice,
			double salesPrice, double upLength, double upHeight, double upWidth, double upWeight,
			UnlockedPhonesInventory unlockedPhonesInventory, List<PhonesReceivedQuantity> phonesReceivedQuantity,Master master, char deleteYN, List<PhonesReturned> phonesReturned) {
		super();
		this.itemUnlockedPhoneId = itemUnlockedPhoneId;
		this.productId = productId;
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.upLength = upLength;
		this.upHeight = upHeight;
		this.upWidth = upWidth;
		this.upWeight = upWeight;
		this.unlockedPhonesInventory = unlockedPhonesInventory;
		this.phonesReceivedQuantity = phonesReceivedQuantity;
		this.master = master;
		this.deleteYN = deleteYN;
		this.phonesReturned = phonesReturned;
	}

	public ItemUnlockedPhone() {
	}

	public Long getItemUnlockedPhoneId() {
		return itemUnlockedPhoneId;
	}

	public void setItemUnlockedPhoneId(Long itemUnlockedPhoneId) {
		this.itemUnlockedPhoneId = itemUnlockedPhoneId;
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

	public double getUpLength() {
		return upLength;
	}

	public void setUpLength(double upLength) {
		this.upLength = upLength;
	}

	public double getUpHeight() {
		return upHeight;
	}

	public void setUpHeight(double upHeight) {
		this.upHeight = upHeight;
	}

	public double getUpWidth() {
		return upWidth;
	}

	public void setUpWidth(double upWidth) {
		this.upWidth = upWidth;
	}

	public double getUpWeight() {
		return upWeight;
	}

	public void setUpWeight(double upWeight) {
		this.upWeight = upWeight;
	}

	public UnlockedPhonesInventory getUnlockedPhonesInventory() {
		return unlockedPhonesInventory;
	}

	public void setUnlockedPhonesInventory(UnlockedPhonesInventory unlockedPhonesInventory) {
		this.unlockedPhonesInventory = unlockedPhonesInventory;
	}

	public List<PhonesReceivedQuantity> getPhonesReceivedQuantity() {
		return phonesReceivedQuantity;
	}

	public void setPhonesReceivedQuantity(List<PhonesReceivedQuantity> phonesReceivedQuantity) {
		this.phonesReceivedQuantity = phonesReceivedQuantity;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}

	public List<PhonesReturned> getPhonesReturned() {
		return phonesReturned;
	}

	public void setPhonesReturned(List<PhonesReturned> phonesReturned) {
		this.phonesReturned = phonesReturned;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
