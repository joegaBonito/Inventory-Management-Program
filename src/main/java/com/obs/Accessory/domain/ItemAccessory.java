package com.obs.Accessory.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="ItemAccessory")
public class ItemAccessory extends ResourceSupport implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7807666512674286064L;

	@Id
	@GeneratedValue
	@Column(name="item_accessory_id")
	private Long itemAccessoryId;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="purchase_price")
	private double purchasePrice;
	
	@Column(name="sales_price")
	private double salesPrice;
	
	@Column(name="acc_length")
	private double accLength;
	
	@Column(name="acc_height")
	private double accHeight;
	
	@Column(name="acc_width")
	private double accWidth;
	
	@Column(name="acc_weight")
	private double accWeight;
	
	@JsonIgnore
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="accessory_inventory_id")
	private AccessoryInventory accessoryInventory;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="itemAccessory")
	private Set<AccessoryReceivedQuantity> accessoryReceivedQuantity;
	
	@Column(name="delete_YN")
	private char deleteYN;
	
	public ItemAccessory() {}
	@JsonCreator
	public ItemAccessory(@JsonProperty("itemAccessoryId") Long itemAccessoryId, @JsonProperty("productId") String productId, @JsonProperty("productName") String productName, @JsonProperty("purchasePrice") double purchasePrice,
			@JsonProperty("salesPrice") double salesPrice, @JsonProperty("accLength") double accLength, @JsonProperty("accHeight") double accHeight, @JsonProperty("accWidth") double accWidth, @JsonProperty("accWeight") double accWeight,
			AccessoryInventory accessoryInventory, Set<AccessoryReceivedQuantity> accessoryReceivedQuantity, char deleteYN) {
		super();
		this.itemAccessoryId = itemAccessoryId;
		this.productId = productId;
		this.productName = productName;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.accLength = accLength;
		this.accHeight = accHeight;
		this.accWidth = accWidth;
		this.accWeight = accWeight;
		this.accessoryInventory = accessoryInventory;
		this.accessoryReceivedQuantity = accessoryReceivedQuantity;
		this.deleteYN = deleteYN;
	}

	public Long getItemAccessoryId() {
		return itemAccessoryId;
	}

	public void setItemAccessoryId(Long itemAccessoryId) {
		this.itemAccessoryId = itemAccessoryId;
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

	public double getAccLength() {
		return accLength;
	}

	public void setAccLength(double accLength) {
		this.accLength = accLength;
	}

	public double getAccHeight() {
		return accHeight;
	}

	public void setAccHeight(double accHeight) {
		this.accHeight = accHeight;
	}

	public double getAccWidth() {
		return accWidth;
	}

	public void setAccWidth(double accWidth) {
		this.accWidth = accWidth;
	}

	public double getAccWeight() {
		return accWeight;
	}

	public void setAccWeight(double accWeight) {
		this.accWeight = accWeight;
	}

	public AccessoryInventory getAccessoryInventory() {
		return accessoryInventory;
	}

	public void setAccessoryInventory(AccessoryInventory accessoryInventory) {
		this.accessoryInventory = accessoryInventory;
	}

	public Set<AccessoryReceivedQuantity> getAccessoryReceivedQuantity() {
		return accessoryReceivedQuantity;
	}

	public void setAccessoryReceivedQuantity(Set<AccessoryReceivedQuantity> accessoryReceivedQuantity) {
		this.accessoryReceivedQuantity = accessoryReceivedQuantity;
	}

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}
}
