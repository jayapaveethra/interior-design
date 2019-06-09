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
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Dao.SupplierDao;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;
import com.niit.furniturebackend.Model.Category;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model.Supplier;

@Controller
public class SupplierController {
	
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
	@Autowired
	Product product;
	@Autowired
	ProductDao productDao;
	
	@RequestMapping("/admin/supplier")
	public String supplier(Model model)
	{
		Supplier s=new Supplier();
		model.addAttribute("supplier",s);
		return "supplierform";
	}
	
	@RequestMapping(value="/admin/addSupplier",method=RequestMethod.POST)
	public String addSupplier(@ModelAttribute("supplier") Supplier supplier,Model model)
	{
		if(supplierDao.saveorupdate(supplier)==true)
		{
			Supplier s=new Supplier();
			model.addAttribute("supplier",s);
			List<Supplier> suppliers=supplierDao.supplierlist();
		    model.addAttribute("suppliers",suppliers);
			model.addAttribute("mess","Saved Successfully");
			return "redirect:/admin/viewsupplier";
		}
		else
		{
			model.addAttribute("mess","Sorry");
			Supplier s=new Supplier();
			model.addAttribute("supplier",s);
			return "supplierform";
		}
	}
	
	@RequestMapping(value="/admin/suppliers",method=RequestMethod.GET)
	public String viewListSupplier(@ModelAttribute("supplier") Supplier supplier,Model model)
	{
			List<Supplier> suppliers=supplierDao.supplierlist();
		    model.addAttribute("suppliers",suppliers);
		    return "redirect:/admin/viewsupplier";
		
	}
	
	@RequestMapping(value="/admin/viewsupplier")
	public String viewsupplier(Model model)
	{
		List<Supplier> suppliers=supplierDao.supplierlist();
		model.addAttribute("suppliers",suppliers);
		return "viewsupplier";
		
	}
	@RequestMapping(value="/admin/deletesupplier/{id}")
	public String deleteSupplier(@PathVariable("id")String id,Model model)
	{
		try {
			List<Product> p=productDao.getProductsBySupplierId(id);
			System.out.println(111111);
			for(Product p1:p)
			{
				System.out.println(p1.getDescription());
				List<CartItems> c=cartItemsDao.getCartItemsByProductId(p1.getProductId());
				if(c==null || c.isEmpty())
				{
					System.out.println(123);
					productDao.delete(p1);
				
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
					productDao.delete(p1);
				}
			}
			   
		
		
		Supplier s=supplierDao.getSupplier(id);
		if(supplierDao.delete(s)==true)
		{
			model.addAttribute("mess","Supplier deleted successfully");
			return "redirect:/admin/viewsupplier";
		}
		else
		{
			return "redirect:/admin/viewsupplier";
		}
		} catch (Exception e) {
			Supplier c=supplierDao.getSupplier(id);
			supplierDao.delete(c);
			System.out.println(e);
			return "redirect:/admin/viewsupplier";
		}
	}
	@RequestMapping(value="/admin/editsupplier/{id}")
	public String editSupplier(@PathVariable("id")String id,Model model)
	{
		Supplier s=supplierDao.getSupplier(id);
		
		model.addAttribute("supplier",s);
		return "supplierform";

}
}
