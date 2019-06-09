package com.niit.furniturefrontend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.furniturebackend.Dao.BillingDao;
import com.niit.furniturebackend.Dao.CardDao;
import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.CartItemsDao;
import com.niit.furniturebackend.Dao.OrderItemsDao;
import com.niit.furniturebackend.Dao.OrdersDao;
import com.niit.furniturebackend.Dao.PayDao;
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Dao.ShippingDao;
import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.Model.Billing;
import com.niit.furniturebackend.Model.Card;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;
import com.niit.furniturebackend.Model.OrderItems;
import com.niit.furniturebackend.Model.Orders;
import com.niit.furniturebackend.Model.Pay;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model.Shipping;
import com.niit.furniturebackend.Model.Users;
@Controller
public class CheckoutController
{
	@Autowired
	Shipping shipping;
	@Autowired
	ShippingDao shippingDao;
	@Autowired
	Pay pay;
	@Autowired
	PayDao payDao;
	@Autowired
	Users users;
	@Autowired
	UsersDao usersDao;
	@Autowired
	Product product;
	@Autowired
	ProductDao productDao;
	@Autowired
	Cart cart;
	@Autowired
	CartDao cartDao;
	@Autowired
	CartItems cartItems;
	@Autowired
	CartItemsDao cartItemsDao;
	@Autowired
	Card card;
	@Autowired
	CardDao cardDao;
	@Autowired
	Orders orders;
	@Autowired
	OrdersDao ordersDao;
	@Autowired
	OrderItems orderItems;
	@Autowired
	OrderItemsDao orderItemsDao;




	@RequestMapping(value="/checkout")
	public String checkOut(Model model)
	{
	Shipping s=new Shipping();
	model.addAttribute("shipping",s);


	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	if(!(authentication instanceof AnonymousAuthenticationToken))
	{
	String currentUserName=authentication.getName();
	users=usersDao.getUsersbyemail(currentUserName);
	if(users==null)
	return "redirect:/login";
	else
	{

	model.addAttribute("cart",users.getCart());
	}
	}
	return "checkout";

	}

	@RequestMapping(value="/placeorder")
	public String placeOrder(@RequestParam("payment-method")String payment,@ModelAttribute("shipping")Shipping shipping,Model model)
	{

	this.shipping=shipping;
	if(payment.equalsIgnoreCase("card"))
	{
	System.out.println(1111);
	Card c=new Card();
	Pay pay= new Pay();
	pay.setPayMethod("Card");
	pay.setPaystatus("paid");
	payDao.saveorupdate(pay);
	this.pay=pay;
	model.addAttribute("card",c);
	return "card";
	}
	else
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken))
		{
			String currentUserName=authentication.getName();
			users=usersDao.getUsersbyemail(currentUserName);
			if(users==null)
				return "redirect:/login";
			else
			{
				System.out.println(123456789);
				Pay pay= new Pay();
				pay.setPayMethod("cash");
				pay.setPaystatus("not paid");
				payDao.saveorupdate(pay);
				shipping.setUser(users);
				shippingDao.saveorupdate(shipping);
				Orders o=new Orders();
				o.setShipping(shipping);
				o.setPay(pay);
				o.setBilling(users.getBilling());
				o.setGrandTotal(users.getCart().getGrandTotal());
				o.setUser(users);
				ordersDao.saveorupdate(o);
				List<CartItems> CartItems =cartItemsDao.cartItemslist(users.getCart().getCartId()); 
				
                for(CartItems c1:CartItems)
                {
                	System.out.println(98765321);
                	OrderItems oi=new OrderItems();
                	oi.setOrders(o);
                	oi.setProductId(c1.getProduct().getProductId());
                	orderItemsDao.saveorupdate(oi);
                	this.cart=users.getCart();
                	this.cart.setTotalItems(this.cart.getTotalItems()-1);
                	this.cart.setGrandTotal(this.cart.getGrandTotal()-c1.getPrice());
                	cartItemsDao.delete(c1);
                	cartDao.saveorupdate(this.cart);
                }
			}
		}
			return "redirect:/confirm";
	}
	}

	@RequestMapping(value="/addCard")
	public String addCard(@ModelAttribute("pay")Pay pay,@ModelAttribute("card")Card card,Model model)
	{

	if(cardDao.saveorupdate(card)==true)
	{
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	if(!(authentication instanceof AnonymousAuthenticationToken))
	{
	String currentUserName=authentication.getName();
	users=usersDao.getUsersbyemail(currentUserName);
	if(users==null)
	return "redirect:/login";
	else
	{
	System.out.println(123456789);
	card.setUsers(users);
	cardDao.saveorupdate(card);
	Orders o=new Orders();
	this.shipping.setUser(users);
	shippingDao.saveorupdate(this.shipping);
	o.setShipping(this.shipping);
	o.setPay(this.pay);
	o.setBilling(users.getBilling());
	o.setGrandTotal(users.getCart().getGrandTotal());
	o.setUser(users);
	ordersDao.saveorupdate(o);
	List<CartItems> CartItems =cartItemsDao.cartItemslist(users.getCart().getCartId()); 
	               for(CartItems c1:CartItems)
	               {
	               	System.out.println(c1.getCartItemsId());
	               	OrderItems oi=new OrderItems();
	               	oi.setOrders(o);
	               	oi.setProductId(c1.getProduct().getProductId());
	               	System.out.println(c1.getProduct().getProductId());
	               	orderItemsDao.saveorupdate(oi);
	               	this.cart=users.getCart();
	               	this.cart.setTotalItems(this.cart.getTotalItems()-1);
	               	this.cart.setGrandTotal(this.cart.getGrandTotal()-c1.getPrice());
	               	cartItemsDao.delete(c1);
	               	cartDao.saveorupdate(this.cart);
	               }             
	}
	}
	return "redirect:/confirm";
	}
	else
	{
	return "redirect:/checkout";
	}
	  }

	@RequestMapping(value="/confirm")
	public String confirm(Model model)
	{
//		List<OrderItems> oi=orderItemsDao.orderitemslist();
//		model.addAttribute("orderItems",oi);
//		Orders o=ordersDao.getOrders(oi);
//		model.addAttribute("orders",o);
	return "confirm";
	}

}