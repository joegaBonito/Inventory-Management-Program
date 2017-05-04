package com.obs.General.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="OrderEntity")
public class OrderEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3501345235794385511L;

	@Id
	@GeneratedValue
	@Column(name="order_id")
	private Long orderId;
	
	@Column(name="order_num")
	private int orderNum;
	
	@DateTimeFormat (pattern="MM-dd-yyyy HH:mm")
	@Column(name="order_received")	
	private Date orderReceived = new Date();
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
//	@JoinTable(name="ORDER_ITEMACCE_TABLE", 
//			   joinColumns= @JoinColumn(name="ORDER_ID"),
//	           inverseJoinColumns=@JoinColumn(name="ID"))
	@Column(name="item_accessory")
	private String itemAccessory;
	
	public OrderEntity() {}

	public OrderEntity(Long orderId, int orderNum, Date orderReceived, String itemAccessory) {
		super();
		this.orderId = orderId;
		this.orderNum = orderNum;
		this.orderReceived = orderReceived;
		this.itemAccessory = itemAccessory;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Date getOrderReceived() {
		return orderReceived;
	}

	public void setOrderReceived(Date orderReceived) {
		this.orderReceived = orderReceived;
	}

	public String getItemAccessory() {
		return itemAccessory;
	}

	public void setItemAccessory(String itemAccessory) {
		this.itemAccessory = itemAccessory;
	}
}
