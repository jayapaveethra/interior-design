package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.Shipping;

public interface ShippingDao 
{
	public boolean saveorupdate(Shipping shipping);
	public boolean delete(Shipping shipping);
	public Shipping getShipping(String shippingId);
	public List<Shipping> shippinglist();


}
