package com.niit.furniturefrontend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.CartItemsDao;
import com.niit.furniturebackend.Dao.CategoryDao;
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;
import com.niit.furniturebackend.Model.Category;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model.Users;

@Controller
public class CartController {

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
	Category category;
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value="/cart")
	public String getCartItems(Model model)
	{
		System.out.println(123);
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken))
		{
			 String currentUsername=authentication.getName();
		      System.out.println(currentUsername);
		      users=usersDao.getUsersbyemail(currentUsername);
		      if(users==null)
		    	  return "redirect:/login";
		      else
		      {
		    	  List<CartItems> ci=cartItemsDao.cartItemslist(users.getCart().getCartId());
		    	  if(ci==null || ci.isEmpty())
		    	  {
		    		  model.addAttribute("mess","No items addded to cart");
		    		  return "cart";
		    	  }
		    	  model.addAttribute("CartItems", ci);
		    	  model.addAttribute("cart",users.getCart());
		      }
		}
		return "cart";
	}

	@RequestMapping(value="/viewproducts")
	public String getProductByCat(@PathVariable("id")String id,Model model)
	{
		List<Category> categories=categoryDao.categorylist();
		model.addAttribute("categories",categories);
		List<Product> products=productDao.productlist();
		model.addAttribute("products",products);
		System.out.println(products);
		return "viewproducts";
		
	}
	
	@RequestMapping(value="/addTocart/{ProductId}")
	public String addToCart(@PathVariable("ProductId")String id,Model model)
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken))
		{
		      String currentUsername=authentication.getName();
		      System.out.println(currentUsername);
		      users=usersDao.getUsersbyemail(currentUsername);
		      if(users==null)
		    	  return "redirect:/login";
		      else
		      {
		    	  Cart c=users.getCart();
		    	  System.out.println(c.getCartId());
		    	  product=productDao.getProduct(id);
		    	  CartItems cartItems=new CartItems();
		    	  cartItems.setProduct(product);
		    	  cartItems.setCart(c);
		    	  System.out.println(675543);
		    	  cartItems.setPrice(Float.parseFloat(product.getPrice()));
		    	  if(cartItemsDao.saveorupdate(cartItems))
		    	  {
		    	 	  int t=c.getTotalItems()+1;
		    		  double p=c.getGrandTotal()+Double.parseDouble(product.getPrice());
		    		  c.setGrandTotal(p);
		    		  c.setTotalItems(t);
		    		  cartDao.saveorupdate(c);
		    	  }  
		    	  System.out.println(86875);

		    	  
		    	  return "redirect:/cart";
		      }
		}
		return "redirect:/cart";
	}
	
	@RequestMapping(value="/remove/cartitems/{cartitemsid}")
	public String removeCartItems(@PathVariable("cartitemsid")String cartitemsid,Model model)
	{
		CartItems c=cartItemsDao.getCartItems(cartitemsid);
		Cart c1=c.getCart();
		c1.setGrandTotal(c1.getGrandTotal()-c.getPrice());
		c1.setTotalItems(c1.getTotalItems()-1);
		cartDao.saveorupdate(c1);
		cartItemsDao.delete(c);
		return "redirect:/cart";
	}
	
}
