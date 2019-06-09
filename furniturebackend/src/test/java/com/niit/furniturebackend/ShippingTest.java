package com.niit.furniturebackend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.ShippingDao;
import com.niit.furniturebackend.Dao.SupplierDao;
import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.Model.Shipping;
import com.niit.furniturebackend.Model.Supplier;
import com.niit.furniturebackend.Model.Users;

public class ShippingTest { 
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		Shipping c=(Shipping)ctx.getBean("shipping");
	    ShippingDao cDao=(ShippingDao)ctx.getBean("shippingDao");
	    
	    Users u=( Users)ctx.getBean(" users");
	    UsersDao uDao=( UsersDao)ctx.getBean(" usersDao");
	    u=uDao.getUsers("U101");
		c.setShipId("S101");
		c.setShipName("mission");
		c.setShipCity("Bangalore");
		c.setPhoneNo("9845042883");
		c.setShipState("Karnataka");
		c.setUser(u);
		
		System.out.println("ShipId: "+c.getShipId());
		System.out.println("ShipName: "+c.getShipName());
		System.out.println("Shipcity: "+c.getShipCity());
		System.out.println("Shipphoneno: "+c.getPhoneNo());
		System.out.println("Shipstate: "+c.getShipState());
		
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("Shipping saved");
		}
		else
		{
			System.out.println("Shipping not saved");
		}
		
		c=cDao.getShipping("S101");
		if(c==null)
		{
			System.out.println("Shipping not found");
		}
		else
		{
			System.out.println("ShipId: "+c.getShipId());
			System.out.println("ShipName: "+c.getShipName());
			System.out.println("Shipcity: "+c.getShipCity());
			System.out.println("Shipphoneno: "+c.getPhoneNo());
			System.out.println("Shipstate: "+c.getShipState());
		}
		
		c=cDao.getShipping("S102");
		if(c==null)
		{
			System.out.println("Shipping not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("Shipping deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
		List<Shipping> shippinglist = cDao.shippinglist();
		for(Shipping c1: shippinglist)
		{
			System.out.println("ShipId: "+c1.getShipId());
			System.out.println("ShipName: "+c1.getShipName());
			System.out.println("Shipcity: "+c1.getShipCity());
			System.out.println("Shipphoneno: "+c1.getPhoneNo());
			System.out.println("Shipstate: "+c1.getShipState());
		}
	}

}


