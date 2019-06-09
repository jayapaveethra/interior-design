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
import com.niit.furniturebackend.Dao.SupplierDao;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;
import com.niit.furniturebackend.Model.Category;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model.Supplier;
import com.niit.furniturefrontend.FileInput.FileInput;

@Controller
public class ProductsController {
	
	@Autowired
	Product product;
	@Autowired
	ProductDao productDao; 
	@Autowired
	Category category;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDao supplierDao;
	@Autowired
	CartItems cartItems;
	@Autowired
	CartItemsDao cartItemsDao;
	@Autowired
	Cart cart;
	@Autowired
	CartDao cartDao;
	
	String path="C:\\Users\\user\\eclipse-workspace\\furbiturefrontend\\src\\main\\webapp\\resources\\img\\";
	
	@RequestMapping("/admin/product")
	public String product(Model model)
	{
		Product p=new Product();
		model.addAttribute("product",p);
		Category c=new Category();
		model.addAttribute("category",c);
		Supplier s=new Supplier();
		model.addAttribute("supplier",s);
		List<Category> categories=categoryDao.categorylist();
		model.addAttribute("categories",categories);
		List<Supplier> suppliers=supplierDao.supplierlist();
		model.addAttribute("suppliers",suppliers);
		return "productform";
	}
	
	@RequestMapping(value="/admin2/addProducts")
	public String addProducts(@ModelAttribute("product") Product product,Model model)
	{
		System.out.println(12343454);
     	FileInput.upload(path, product.getPing(), product.getProductId()+".jpg");
		if(productDao.saveorupdate(product)==true)
		{
			Product p=new Product();
			model.addAttribute("product",p);
			List<Product> products=productDao.productlist();
		    model.addAttribute("products",products);
			model.addAttribute("mess", "saved successful");
		    return "redirect:/admin/viewadminproduct";
		}
		else
		{
			model.addAttribute("mess", "Sorry");	
			Product p=new Product();
			model.addAttribute("products",p);
			return "productform";
		}
		
	}
	@RequestMapping(value="/admin/productss",method=RequestMethod.GET)
	public String viewListProducts(@ModelAttribute("product") Product product,Model model)
	{
			List<Product> products=productDao.productlist();
		    model.addAttribute("products",products);
		    return "redirect:/admin/viewadminproduct";
		
	}
	@RequestMapping(value="/admin/viewadminproduct")
	public String viewadminproduct(Model model)
	{
		List<Product> products=productDao.productlist();
	    model.addAttribute("products",products);
	    return "viewadminproduct";
	}

	
	@RequestMapping(value="/viewproducts/{id}")
	public String getProductByCat(@PathVariable("id")String id,Model model)
	{
		List<Category> categories=categoryDao.categorylist();
		model.addAttribute("categories",categories);
		List<Product> products=productDao.getProductsById(id);
		model.addAttribute("products",products);
		System.out.println(products);
		return "viewproducts";
		
	}
	@RequestMapping(value="/admin/editproducts/{id}")
	public String editProducts(@PathVariable("id")String id,Model model)
	{
		List<Category> categories=categoryDao.categorylist();
		model.addAttribute("categories",categories);
		List<Supplier> suppliers=supplierDao.supplierlist();
		model.addAttribute("suppliers",suppliers);
		Product p=productDao.getProduct(id);
		model.addAttribute("product",p);
		return "productform";
	}
	
	@RequestMapping(value="/admin/deleteproducts/{id}")
	public String deleteProducts(@PathVariable("id")String id,Model model)
	{
		try {
			Product p=productDao.getProduct(id);
			List<CartItems> c=cartItemsDao.getCartItemsByProductId(p.getProductId());
			if(c==null || c.isEmpty())
			{
				System.out.println(123);
				productDao.delete(p);
			
			}
			else
			{
				for(CartItems c1:c)
				{
					System.out.println(12);
					Cart ca=cartDao.getCart(c1.getCart().getCartId());
					ca.setTotalItems(ca.getTotalItems()-1);
					ca.setGrandTotal(ca.getGrandTotal()-c1.getPrice());
					cartDao.saveorupdate(ca);
					cartItemsDao.delete(c1);
				}
				productDao.delete(p);
			}
			
			if(productDao.delete(p)==true)
			{
				model.addAttribute("mess","Product deleted successfully");
				return "redirect:/admin/viewadminproduct";
			}
			else
			{
				return "redirect:/admin/viewadminproduct";
			}
		} catch (Exception e) {
			Product p=productDao.getProduct(id);
			productDao.delete(p);
			System.out.println(e);
			return "redirect:/admin/viewadminproduct";
		}
	}
}