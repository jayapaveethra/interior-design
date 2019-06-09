package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.Cart;

public interface CartDao 
{
	public boolean saveorupdate(Cart cart);
	public boolean delete(Cart cart);
	public Cart getCart(String cartId);
	public List<Cart> cartlist();


}
