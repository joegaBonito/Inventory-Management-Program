package com.obs.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UpsOrder")
public class UpsOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3855424089356133213L;
	@Id
	@GeneratedValue
	@Column(name="system_id")
	private Long systemId;
	
	@Column(name="ups_order_id")
	private String upsOrderId;
	
	@Column(name="ups_order_received")	
	private String upsOrderReceived;
	
	@Column(name="ups_product_id")
	private String upsProductId;
	
	@Column(name="ups_quantity")
	private int upsQuantity;
	
	@Column(name="ups_product_name")
	private String upsProductName;
	
	@Column(name="ups_shipping_method")
	private String upsShippingMethod;
	
	@Column(name="ups_fname")
	private String upsFname;
	
	@Column(name="ups_lname")
	private String upsLname;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name ="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="residential")
	private int residential = 1;
	
	@Column(name="service")
	private String service;
	
	@Column(name="package_type")
	private String packageType = "CP";
	
	@Column(name="bill_to")
	private String billTo = "SHP";
	
	@Column(name="ups_length")
	private double upsLength;
	
	@Column(name="ups_height")
	private double upsHeight;
	
	@Column(name="ups_width")
	private double upsWidth;
	
	@Column(name="ups_weight")
	private double upsWeight;
	
	@Column(name="country")
	private String country = "US";
	
	@Column(name="customer_name")
	private String customerName = "LG.COM";

	public UpsOrder(Long systemId, String upsOrderId, String upsOrderReceived, String upsProductId, int upsQuantity,
			String upsProductName, String upsShippingMethod, String upsFname, String upsLname, String address1,
			String address2, String city, String state, String zip, String phone, int residential, String service,
			String packageType, String billTo, double upsLength, double upsHeight, double upsWidth, double upsWeight, String country,
			String customerName) {
		super();
		this.systemId = systemId;
		this.upsOrderId = upsOrderId;
		this.upsOrderReceived = upsOrderReceived;
		this.upsProductId = upsProductId;
		this.upsQuantity = upsQuantity;
		this.upsProductName = upsProductName;
		this.upsShippingMethod = upsShippingMethod;
		this.upsFname = upsFname;
		this.upsLname = upsLname;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.residential = residential;
		this.service = service;
		this.packageType = packageType;
		this.billTo = billTo;
		this.upsLength = upsLength;
		this.upsHeight = upsHeight;
		this.upsWidth = upsWidth;
		this.upsWeight = upsWeight;
		this.country = country;
		this.customerName = customerName;
	}

	public UpsOrder() {
		// TODO Auto-generated constructor stub
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public String getUpsOrderId() {
		return upsOrderId;
	}

	public void setUpsOrderId(String upsOrderId) {
		this.upsOrderId = upsOrderId;
	}

	public String getUpsOrderReceived() {
		return upsOrderReceived;
	}

	public void setUpsOrderReceived(String upsOrderReceived) {
		this.upsOrderReceived = upsOrderReceived;
	}

	public String getUpsProductId() {
		return upsProductId;
	}

	public void setUpsProductId(String upsProductId) {
		this.upsProductId = upsProductId;
	}

	public int getUpsQuantity() {
		return upsQuantity;
	}

	public void setUpsQuantity(int upsQuantity) {
		this.upsQuantity = upsQuantity;
	}

	public String getUpsProductName() {
		return upsProductName;
	}

	public void setUpsProductName(String upsProductName) {
		this.upsProductName = upsProductName;
	}

	public String getUpsShippingMethod() {
		return upsShippingMethod;
	}

	public void setUpsShippingMethod(String upsShippingMethod) {
		this.upsShippingMethod = upsShippingMethod;
	}

	public String getUpsFname() {
		return upsFname;
	}

	public void setUpsFname(String upsFname) {
		this.upsFname = upsFname;
	}

	public String getUpsLname() {
		return upsLname;
	}

	public void setUpsLname(String upsLname) {
		this.upsLname = upsLname;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getResidential() {
		return residential;
	}

	public void setResidential(int residential) {
		this.residential = residential;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public double getUpsLength() {
		return upsLength;
	}

	public void setUpsLength(double upsLength) {
		this.upsLength = upsLength;
	}

	public double getUpsHeight() {
		return upsHeight;
	}

	public void setUpsHeight(double upsHeight) {
		this.upsHeight = upsHeight;
	}

	public double getUpsWidth() {
		return upsWidth;
	}

	public void setUpsWidth(double upsWidth) {
		this.upsWidth = upsWidth;
	}

	public double getUpsWeight() {
		return upsWeight;
	}

	public void setUpsWeight(double upsWeight) {
		this.upsWeight = upsWeight;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
