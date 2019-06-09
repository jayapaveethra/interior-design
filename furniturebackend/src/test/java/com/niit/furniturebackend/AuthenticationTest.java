package com.niit.furniturebackend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.AuthenticationDao;
import com.niit.furniturebackend.Model.Authentication;

public class AuthenticationTest {
	public static void main(String args[]) { 
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		//Category c= new Category();
		Authentication c=(Authentication)ctx.getBean("authentication");
		AuthenticationDao cDao=(AuthenticationDao)ctx.getBean("authenticationDao");
		
		c.setRoleId("c101");
		c.setRoleName("role1");
		c.setUserName("jaya");
		System.out.println("roleid"+c.getRoleId());
		System.out.println("rolename"+c.getRoleName());
		System.out.println("rolename"+c.getUserName());
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("authentication saved");
			
		}
		else
		{
			System.out.println("authentication  not saved");
			
		}
		c=cDao.getAuthentication("C101");
		if(c==null)
		{
			System.out.println("Authentication not found");
		}
		else
		{
			System.out.println("roleId : "+c.getRoleId());
			System.out.println("roleName: "+c.getRoleName());
			System.out.println("rolename"+c.getUserName());
		}
		
		c=cDao.getAuthentication("C102");
		if(c==null)
		{
			System.out.println("Authentication not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("Authentication deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
		List<Authentication> authenticationlist = cDao.authenticationlist();
		for(Authentication c1: authenticationlist)
		{
			System.out.println("RoleId : "+c1.getRoleId());
			System.out.println("RoleName: "+c1.getRoleName());
			System.out.println("username"+c.getUserName());
			
		}
		
	}

}

