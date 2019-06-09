package com.niit.furniturefrontend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.furniturebackend.Dao.CategoryDao;
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Model.Category;
import com.niit.furniturebackend.Model.Product;

@Controller
public class HomeController {
	
	@Autowired
	Category category;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ProductDao productDao; 
	
	@RequestMapping("/")
	public String index(Model model)
	{
		System.out.println(123);
		
		return "index";
	}
	@RequestMapping(value="/shop",method=RequestMethod.GET)
	public String shop(Model model)
	{
		List<Category> categories=categoryDao.categorylist();
		model.addAttribute("categories",categories);
		List<Product> products=productDao.productlist();
		model.addAttribute("products",products);
		System.out.println(products);
		return "shop";
	}
	@RequestMapping("/product")
	public String product()
	{
		return "product-details";
	}
//	@RequestMapping("/checkout")
//	public String checkout()
//	{
//		return "checkout";
//	}

}