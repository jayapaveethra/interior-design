package com.niit.furniturebackend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.CartItemsDao;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;

public class CartItemsTest {
	public static void main(String args[]) { 
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		//Category c= new Category();
		CartItems c=(CartItems)ctx.getBean("cartItems");
		CartItemsDao cDao=(CartItemsDao)ctx.getBean("cartitemsDao");
		Cart cat=(Cart)ctx.getBean("cart");
		CartDao catDao=(CartDao)ctx.getBean("cartDao");
		cat=catDao.getCart("C101");
		
		c.setCartItemsId("c101");
		c.setPrice((float) 10.0);
		c.setCart(cat);
		System.out.println("CartItemsid"+c.getCartItemsId());
		System.out.println("Price: "+c.getPrice());
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("CartItems saved");
			
		}
		else
		{
			System.out.println("CartItems  not saved");
			
		}
		c=cDao.getCartItems("C101");
		if(c==null)
		{
			System.out.println("CartItems not found");
		}
		else
		{
			System.out.println("CartItemsId : "+c.getCartItemsId());
			System.out.println("CartItemsPrice: "+c.getPrice());
		}
		
		c=cDao.getCartItems("C102");
		if(c==null)
		{
			System.out.println("CartItems not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("CartItems deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
//		List<CartItems> cartItemslist = cDao.cartItemslist();
//		for(CartItems c1: cartItemslist)
//		{
//			System.out.println("CartItemsId : "+c1.getCartItemsId());
//			System.out.println("CartItemsPrice: "+c1.getPrice());
//			
//		}
		
	}

}

