package com.niit.furniturefrontend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.furniturebackend.Dao.BillingDao;
import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.Model.Authentication;
import com.niit.furniturebackend.Model.Billing;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.Users;


@Controller
public class UserController 
{
	@Autowired
	Users users;
	@Autowired
	UsersDao usersDao;
	@Autowired
	Billing billing;
	@Autowired
	BillingDao billingDao;
	@Autowired
	Cart cart;
	@Autowired
	CartDao cartDao;
	@Autowired
	Authentication authentication;
	
	
	@RequestMapping(value="/register")
	public String users(Model model)
	{
		Users u=new Users();
		Billing b=new Billing();
		
		u.setBilling(b);
		
		model.addAttribute("users",u);
		return "registerform";
	}
	
	@RequestMapping(value="/addUsers",method=RequestMethod.POST)
	public String addUsers(@ModelAttribute("users") Users users,Model model)
	{
		if(users.getCart()==null)
		{
			
			users.setCart(new Cart());
			
			Authentication a=new Authentication();
			a.setUserName(users.getEmailid());
			users.setAuthentication(a);
			if(usersDao.saveorupdate(users)==true)
			{
			return "index";
		    }
		
		else
		{
			Authentication a1=new Authentication();
			a1.setUserName(users.getEmailid());
			users.setAuthentication(a1);
		 if(usersDao.saveorupdate(users)==true)
		 {
			 
			   
				return "index";
		}
	}
		}
		else
		{
			model.addAttribute("mess", "sorry");
			 Users u=new Users();
			 model.addAttribute("users",u);
			 return "register";
		}
		return "register";
	
		
	
	}	
}
