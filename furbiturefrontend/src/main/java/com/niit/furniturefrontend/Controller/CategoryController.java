package com.niit.furniturefrontend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.CartItemsDao;
import com.niit.furniturebackend.Dao.CategoryDao;
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;
import com.niit.furniturebackend.Model.Category;
import com.niit.furniturebackend.Model.Product;
@Controller
public class CategoryController 
{
	@Autowired
	Category category;
	@Autowired
	CategoryDao categoryDao; 
	@Autowired
	CartItems cartItems;
	@Autowired
	CartItemsDao cartItemsDao;
	@Autowired
	Cart cart;
	@Autowired
	CartDao cartDao;
	@Autowired
	Product product;
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(value="/admin/category",method=RequestMethod.GET)
	public String category(Model model)
	{
		Category c=new Category();
		List<Category> categories=categoryDao.categorylist();
		model.addAttribute("category",c);
		model.addAttribute("categories",categories);
		return "categoryform";
	}
	@RequestMapping(value="/admin/addCategory",method=RequestMethod.POST)
	public String addCategory(@ModelAttribute("category") Category category,Model model)
	{
		if(categoryDao.saveorupdate(category)==true)
		{
			Category c=new Category();
			model.addAttribute("category",c);
			model.addAttribute("mess", "saved successful");
		    return "redirect:/admin/viewcategory";
		}
		else
		{
			model.addAttribute("mess", "Sorry");	
			Category c=new Category();
			model.addAttribute("category",c);
			return "categoryform";
		}
	}
		@RequestMapping(value="/admin/categories",method=RequestMethod.GET)
		public String viewListCategory(@ModelAttribute("category") Category category,Model model)
		{
				List<Category> categories=categoryDao.categorylist();
			    model.addAttribute("categories",categories);
			    return "redirect:/admin/viewcategory";
			
		}
		
		@RequestMapping(value="/admin/viewcategory")
		public String viewcategory(Model model)
		{
			List<Category> categories=categoryDao.categorylist();
			model.addAttribute("categories",categories);
			return "viewcategory";
			
		}
		
	
	@RequestMapping(value="/admin/deleteCategory/{id}")
	public String deleteCategory(@PathVariable("id")String id,Model model)
	{
		try {
			List<Product> p=productDao.getProductsById(id);
			System.out.println(1);
			for(Product p1:p)
			{
				System.out.println(p1.getDescription());
				if(cartItemsDao.getCartItemsByProductId(p1.getProductId())==null || cartItemsDao.getCartItemsByProductId(p1.getProductId()).isEmpty())
				{
					System.out.println(123);
					productDao.delete(p1);
				
				}
				else
				{
					List<CartItems> c=cartItemsDao.getCartItemsByProductId(p1.getProductId());
					for(CartItems c1:c)
					{
						System.out.println(2);
						Cart ca=cartDao.getCart(c1.getCart().getCartId());
						ca.setTotalItems(ca.getTotalItems()-1);
						ca.setGrandTotal(ca.getGrandTotal()-c1.getPrice());
						cartDao.saveorupdate(ca);
						cartItemsDao.delete(c1);
					}
					productDao.delete(p1);
				}
			}
			   
				Category c=categoryDao.getCategory(id);
				if(categoryDao.delete(c)==true)
				{
					model.addAttribute("mess","Category has been successfully deleted");
					return "redirect:/admin/viewcategory";
				}
				return "redirect:/admin/viewcategory";
			
		} catch (Exception e) {
			Category c=categoryDao.getCategory(id);
			categoryDao.delete(c);
			System.out.println(e);
			return "redirect:/admin/viewcategory";
		}
	}
	@RequestMapping(value="/admin/editCategory/{id}")
	public String editCategory(@PathVariable("id")String id,Model model)
	{
		Category c=categoryDao.getCategory(id);
		List<Category> categories=categoryDao.categorylist();
		model.addAttribute("category",c);
		model.addAttribute("categories",categories);
		return "categoryform";
		
	}
}
