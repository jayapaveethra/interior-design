package com.niit.furniturebackend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.OrdersDao;
import com.niit.furniturebackend.Model.Orders;

public class OrdersTest {
	
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		Orders c=(Orders)ctx.getBean("orders");
		OrdersDao cDao=(OrdersDao)ctx.getBean("ordersDao");
		c.setOrderId("O101");
		c.setGrandTotal((double)100.00000);
		System.out.println("OrderId: "+c.getOrderId());
		System.out.println("GrandTotal: "+c.getGrandTotal());
		
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("Order  saved");
		}
		else
		{
			System.out.println("Order not saved");
		}
		
		c=cDao.getOrders("O101");
		if(c==null)
		{
			System.out.println("Order not found");
		}
		else
		{
			System.out.println("OrderId: "+c.getOrderId());
			System.out.println("GrandTotal: "+c.getGrandTotal());
		}
		
		c=cDao.getOrders("O102");
		if(c==null)
		{
			System.out.println("Order not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("Order deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
		List<Orders> orderslist = cDao.orderslist();
		for(Orders c1: orderslist)
		{
			System.out.println("OrderId: "+c1.getOrderId());
			System.out.println("GrandTotal: "+c1.getGrandTotal());
			
		}
	}


}

