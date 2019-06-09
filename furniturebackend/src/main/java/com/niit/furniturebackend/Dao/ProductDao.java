package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.Product;

public interface ProductDao 
{
	public boolean saveorupdate(Product product);
	public boolean delete(Product product);
	public Product getProduct(String productId);
	public List<Product> productlist();
	public List<Product> getProductsById(String catId);
	public List<Product> getProductsBySupplierId(String supplierId);


}
