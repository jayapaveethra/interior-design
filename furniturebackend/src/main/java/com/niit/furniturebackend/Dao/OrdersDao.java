package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.Orders;

public interface OrdersDao 
{
	public boolean saveorupdate(Orders orders);
	public boolean delete(Orders orders);
	public Orders getOrders(String ordersId);
	public List<Orders> orderslist();


}
