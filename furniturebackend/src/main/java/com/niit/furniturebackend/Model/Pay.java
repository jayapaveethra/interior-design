package com.niit.furniturebackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Pay 
{
	@Id
	private String PayId;
	private String PayMethod;
	private String Paystatus;
	
	public Pay()
	{
		this.PayId="PAY"+UUID.randomUUID().toString().substring(30).toUpperCase();
	}
	
	
	
	public String getPayId() {
		return PayId;
	}
	public void setPayId(String payId) {
		PayId = payId;
	}
	public String getPayMethod() {
		return PayMethod;
	}
	public void setPayMethod(String payMethod) {
		PayMethod = payMethod;
	}
	public String getPaystatus() {
		return Paystatus;
	}
	public void setPaystatus(String paystatus) {
		Paystatus = paystatus;
	}
	

}
