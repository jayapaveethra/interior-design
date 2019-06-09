package com.niit.furniturefrontend.Utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.CartItemsDao;
import com.niit.furniturebackend.Dao.OrdersDao;
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;
import com.niit.furniturebackend.Model.Orders;
import com.niit.furniturebackend.Model.Product;

public class Delete 
{
	@Autowired
	CartDao cartDao;
	@Autowired
	Product product;
	@Autowired
	CartItems cartItems;
	@Autowired
	CartItemsDao cartItemsDao;
	@Autowired
	Cart cart;
	@Autowired
	Orders orders;
    @Autowired
    OrdersDao ordersDao;
    @Autowired
    ProductDao productDao;
    
    public boolean DeleteProduct(String catId)
	{
		System.out.println(111111);
		List<Product> p=productDao.getProductsById(catId);
		System.out.println(111111);
		for(Product p1:p)
		{
			List<CartItems> c=cartItemsDao.getCartItemsByProductId(p1.getProductId());
			if(c==null || c.isEmpty())
			{
				System.out.println(123);
				productDao.delete(p1);
				return true;
			}
			else
			{
				for(CartItems c1:c)
				{
					System.out.println(12);
					Cart ca=cartDao.getCart(c1.getCart().getCartId());
					ca.setTotalItems(ca.getTotalItems()-1);
					ca.setGrandTotal(ca.getGrandTotal()-c1.getPrice());
					cartDao.saveorupdate(ca);
					cartItemsDao.delete(c1);
				}
				productDao.delete(p1);
				return true;
			}
//			List<OrderItems> o=orderItemsDao.getOrderItemsByProductId(p1.getProductsId());
		}
		
		return false;
	}
	
    
	
	
	
	
	

}
