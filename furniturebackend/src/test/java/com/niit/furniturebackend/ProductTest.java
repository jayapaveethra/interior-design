package com.niit.furniturebackend;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.CategoryDao;
import com.niit.furniturebackend.Dao.OrdersDao;
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Dao.SupplierDao;
import com.niit.furniturebackend.Model.Category;
import com.niit.furniturebackend.Model.Orders;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model.Supplier;


public class ProductTest 
{
		
		public static void main(String args[])
		{
			AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext();
			ctx.scan("com.niit.*");
			ctx.refresh();
			
			Product c=(Product)ctx.getBean("product");
		    ProductDao cDao=(ProductDao)ctx.getBean("productDao");
		    
		    Category cat=(Category)ctx.getBean("category");
		    CategoryDao catDao=(CategoryDao)ctx.getBean("categoryDao");
		    cat=catDao.getCategory("C101");
		    
		    Supplier sup=(Supplier)ctx.getBean("supplier");
		    SupplierDao supDao=(SupplierDao)ctx.getBean("supplierDao");
		    sup=supDao.getSupplier("S101");
			c.setProductId("P101");
			c.setProductName("Furniture");
			c.setPrice("50000");
			c.setQuantity("5");
			c.setDescription("good");
			c.setCategory(cat);
			c.setSupplier(sup);
			System.out.println("ProductsId: "+c.getProductId());
			System.out.println("ProductsName: "+c.getProductName());
			System.out.println("ProductsPrice: "+c.getPrice());
			System.out.println("ProductsQuantity: "+c.getQuantity());
			System.out.println("ProductsDescription: "+c.getDescription());
			
			
			if(cDao.saveorupdate(c)==true)
			{
				System.out.println("Products saved");
			}
			else
			{
				System.out.println("Products not saved");
			}
			
			c=cDao.getProduct("P101");
			if(c==null)
			{
				System.out.println("Products not found");
			}
			else
			{
				System.out.println("ProductsId: "+c.getProductId());
				System.out.println("ProductsName: "+c.getProductName());
				System.out.println("ProductsPrice: "+c.getPrice());
				System.out.println("ProductsQuantity: "+c.getQuantity());
				System.out.println("ProductsDescription: "+c.getDescription());
			}
			
			c=cDao.getProduct("P102");
			if(c==null)
			{
				System.out.println("Product not found");
			}
			else if(cDao.delete(c)==true)
			{
				System.out.println("Product deleted");
			}
			else
			{
				System.out.println("Not deleted");
			}
			List<Product> productlist = cDao.productlist();
			for(Product c1: productlist)
			{
				System.out.println("ProductsId: "+c1.getProductId());
				System.out.println("ProductsName: "+c1.getProductName());
				System.out.println("ProductsPrice: "+c1.getPrice());
				System.out.println("ProductsQuantity: "+c1.getQuantity());
				System.out.println("ProductsDescription: "+c1.getDescription());
			}
		}

}
