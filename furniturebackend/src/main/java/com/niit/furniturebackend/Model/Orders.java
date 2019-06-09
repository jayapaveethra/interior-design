package com.niit.furniturebackend.Model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Orders {
	@Id
	private String OrderId;
	private Double grandTotal;

	@OneToMany(mappedBy = "orders")
	private List<OrderItems> orderItems;
	
	@OneToOne
	@JoinColumn(name = "billId")
	private Billing billing;
	@OneToOne
	@JoinColumn(name = "shipId")
	private Shipping shipping;

	@OneToOne
	@JoinColumn(name = "UserId")
	private Users user;
	
	@OneToOne
	@JoinColumn(name = "PayId")
	private Pay pay;

	public Orders()
	{
		this.OrderId="ORDERS"+UUID.randomUUID().toString().substring(30).toUpperCase();
	}
	public List<OrderItems> getorderitems() {
		return orderItems;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}
	

	public String getOrderId() {
		return OrderId;
	}

	public void setOrderId(String orderId) {
		OrderId = orderId;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

}
