package com.niit.furniturebackend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.OrderItemsDao;
import com.niit.furniturebackend.Dao.OrdersDao;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.OrderItems;
import com.niit.furniturebackend.Model.Orders;

public class OrderItemsTest {
	
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		OrderItems c=(OrderItems)ctx.getBean("orderItems");
		OrderItemsDao cDao=(OrderItemsDao)ctx.getBean("orderitemsDao");
		
		Orders o=(Orders)ctx.getBean("orders");
		OrdersDao oDao=(OrdersDao)ctx.getBean("ordersDao");
		o=oDao.getOrders("O101");
		c.setOrderItemId("o101");
		c.setProductId("P101");
//		c.setOrderss(o);
		System.out.println("OrderItemsId: "+c.getOrderItemId());
		System.out.println("ProductId: "+c.getProductId());
		
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("Order Items saved");
		}
		else
		{
			System.out.println("Order Items not saved");
		}
		
		c=cDao.getOrderItems("O101");
		if(c==null)
		{
			System.out.println("Order Items not found");
		}
		else
		{
			System.out.println("OrderItemsId: "+c.getOrderItemId());
			System.out.println("ProductId: "+c.getProductId());
		}
		
		c=cDao.getOrderItems("O102");
		if(c==null)
		{
			System.out.println("Order Items not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("Order Items deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
		List<OrderItems> orderitemslist = cDao.orderItemslist();
		for(OrderItems c1: orderitemslist)
		{
			System.out.println("OrderItemsId: "+c1.getOrderItemId());
			System.out.println("ProductId: "+c1.getProductId());
			
		}
	}

}


