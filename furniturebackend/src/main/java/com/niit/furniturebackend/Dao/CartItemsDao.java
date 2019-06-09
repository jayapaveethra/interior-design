package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.CartItems;

public interface CartItemsDao 
{
	public boolean saveorupdate(CartItems cartItems);
	public boolean delete(CartItems cartItems);
	public CartItems getCartItems(String cartItemsId);
	public List<CartItems> cartItemslist(String cartId);
	public List<CartItems> getCartItemsByProductId(String productId);


}

