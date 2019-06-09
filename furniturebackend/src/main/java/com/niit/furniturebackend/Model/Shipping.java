package com.niit.furniturebackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Shipping
{
	@Id
	 private String ShipId;
	 private String ShipName;
	 private String ShipCity;
	 private String ShipState;
	 private String PhoneNo;
	 private String ShipAddress;
	 private String ShipZipCode;
	 private String ShipCountry;
	 private String email;
	 
	 @ManyToOne
		@JoinColumn(name="uId")
		private Users user;
	 
	 public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Shipping()
	 {
		 this.ShipId="SHIPPING"+UUID.randomUUID().toString().substring(30).toUpperCase();
				
	 }
	 
	 
	public String getShipAddress() {
		return ShipAddress;
	}
	public void setShipAddress(String shipAddress) {
		ShipAddress = shipAddress;
	}
	public String getShipZipCode() {
		return ShipZipCode;
	}
	public void setShipZipCode(String shipZipCode) {
		ShipZipCode = shipZipCode;
	}
	public String getShipCountry() {
		return ShipCountry;
	}
	public void setShipCountry(String shipCountry) {
		ShipCountry = shipCountry;
	}
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getShipId() {
		return ShipId;
	}
	public void setShipId(String shipId) {
		ShipId = shipId;
	}
	public String getShipName() {
		return ShipName;
	}
	public void setShipName(String shipName) {
		ShipName = shipName;
	}
	public String getShipCity() {
		return ShipCity;
	}
	public void setShipCity(String shipCity) {
		ShipCity = shipCity;
	}
	public String getShipState() {
		return ShipState;
	}
	public void setShipState(String shipState) {
		ShipState = shipState;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

}
