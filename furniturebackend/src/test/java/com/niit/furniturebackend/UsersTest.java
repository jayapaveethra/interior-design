package com.niit.furniturebackend;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.BillingDao;
import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.Model.Billing;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.Users;


public class UsersTest {
	
	public static void main(String args[])
	{
		AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		Users c=(Users)ctx.getBean("users");
		UsersDao cDao=(UsersDao)ctx.getBean("usersDao");
		
		Billing b=(Billing)ctx.getBean("billing");
		BillingDao bDao=(BillingDao)ctx.getBean("billingDao");
		b=bDao.getBilling("B101");
		
		Cart cat=(Cart)ctx.getBean("cart");
		CartDao catDao=(CartDao)ctx.getBean("cartDao");
		cat=catDao.getCart("C101");
		
		c.setUserId("U101");
		c.setUserName("Jaya");
		c.setEmailid("jayamonesh@gmail.com");
		c.setPhoneno("9740356831");
		c.setUserAddress("mangalore");
		c.setPassword("jayamonesh");
		c.setBilling(b);
		c.setCart(cat);
		
		
		System.out.println("UsersId: "+c.getUserId());
		System.out.println("UsersName: "+c.getUserName());
		System.out.println("UsersPhoneNo: "+c.getPhoneno());
		System.out.println("Usersemailid: "+c.getEmailid());
		System.out.println("UsersAddress: "+c.getUserAddress());
		System.out.println("UsersPasword: "+c.getPassword());
		
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("Users saved");
		}
		else
		{
			System.out.println("Users not saved");
		}
		
		c=cDao.getUsers("U101");
		if(c==null)
		{
			System.out.println("Users not found");
		}
		else
		{
			System.out.println("UsersId: "+c.getUserId());
			System.out.println("UsersName: "+c.getUserName());
			System.out.println("UsersPhoneNo: "+c.getPhoneno());
			System.out.println("Usersemailid: "+c.getEmailid());
			System.out.println("UsersAddress: "+c.getUserAddress());
			System.out.println("UsersPasword: "+c.getPassword());
			
		}
		
		c=cDao.getUsers("U102");
		if(c==null)
		{
			System.out.println("Users not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("Users deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
		List<Users> userslist = cDao.userslist();
		for(Users c1: userslist)
		{
			System.out.println("UsersId: "+c1.getUserId());
			System.out.println("UsersName: "+c1.getUserName());
			System.out.println("UsersPhoneNo: "+c1.getPhoneno());
			System.out.println("Usersemailid: "+c1.getEmailid());
			System.out.println("UsersAddress: "+c1.getUserAddress());
			System.out.println("UsersPasword: "+c1.getPassword());
			
			
		}
	}


}