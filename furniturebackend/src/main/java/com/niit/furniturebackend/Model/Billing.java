package com.niit.furniturebackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component

public class Billing 
{
	
	@Id
	private String billId;
	private String billName;
	private String billAddress;
	private String billCity;
	private String billState;
	private String billPhoneNo;
	
	@OneToOne
	@JoinColumn(name="usersId")
	private Users users;
	
	
	public String getBillName() {
		return billName;
	}
	
    public Billing()
    {
    	this.billId="Billing"+UUID.randomUUID().toString().substring(30).toUpperCase();
    }
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillAddress() {
		return billAddress;
	}
	public void setBillName(String billName) {
		this.billAddress = billAddress;
	}
	public String getBillCity() {
		return billCity;
	}
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	public String getBillState() {
		return billState;
	}
	public void setBillState(String billState) {
		this.billState = billState;
	}
	public String getBillPhoneNo() {
		return billPhoneNo;
	}
	public void setBillPhoneNo(String billPhoneNo) {
		this.billPhoneNo = billPhoneNo;
	}
	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	

}
