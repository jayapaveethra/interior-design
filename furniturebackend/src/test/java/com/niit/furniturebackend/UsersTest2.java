package com.niit.furniturebackend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.Model.Users;

public class UsersTest2
{ 
	public static void main(String args[])
	{
	AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
	ctx.scan("com.niit.*");
	ctx.refresh();
	
	Users c=(Users)ctx.getBean("users");
	UsersDao cDao=(UsersDao)ctx.getBean("usersDao");
	c.setEmailid("jaya@gmail.com");
	c.setPassword("jaya");
	
	System.out.println("UsersEmailid: "+c.getEmailid());
	System.out.println("UsersPassword: "+c.getPassword());
	
	c=cDao.isvalidate("jaya@gmail.com","jaya");
	if(c==null)
	{
		System.out.println("Users not found");
	}
	else
	{
		System.out.println("UsersEmailid: "+c.getEmailid());
		System.out.println("UsersPassword: "+c.getPassword());
	}
	
	
	c=cDao.isvalidate("jay@gmail.com","jay");
	if(c==null)
	{
		System.out.println("Users not found");
	}
	else
	{
		System.out.println("UsersEmailid: "+c.getEmailid());
		System.out.println("UsersPassword: "+c.getPassword());
	}
	}

}
