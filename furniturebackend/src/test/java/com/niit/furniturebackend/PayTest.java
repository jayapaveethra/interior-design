package com.niit.furniturebackend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.PayDao;
import com.niit.furniturebackend.Model.Pay;

public class PayTest {
	
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		Pay c=(Pay)ctx.getBean("pay");
	    PayDao cDao=(PayDao)ctx.getBean("payDao");
		c.setPayId("P101");
		c.setPayMethod("Card");
		c.setPaystatus("paied");
		System.out.println("PayId: "+c.getPayId());
		System.out.println("PayMethod: "+c.getPayMethod());
		System.out.println("PayStatus: "+c.getPaystatus());
		
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("Pay saved");
		}
		else
		{
			System.out.println("Pay not saved");
		}
		
		c=cDao.getPay("P101");
		if(c==null)
		{
			System.out.println("Pay not found");
		}
		else
		{
			System.out.println("PayId: "+c.getPayId());
			System.out.println("PayMethod: "+c.getPayMethod());
			System.out.println("PayStatus: "+c.getPaystatus());
		}
		
		c=cDao.getPay("P102");
		if(c==null)
		{
			System.out.println("Pay not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("Pay deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
		List<Pay> paylist = cDao.paylist();
		for(Pay c1: paylist)
		{
			System.out.println("PayId: "+c1.getPayId());
			System.out.println("PayMethod: "+c1.getPayMethod());
			System.out.println("PayStatus: "+c1.getPaystatus());
		}
	}
}

