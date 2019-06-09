package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.OrderItems;

public interface OrderItemsDao 
{
	public boolean saveorupdate(OrderItems orderItems);
	public boolean delete(OrderItems orderItems);
	public OrderItems getOrderItems(String orderItemsId);
	public List<OrderItems> orderItemslist();
	public List<OrderItems> getOrderItemsByProductId(String productId);


}
