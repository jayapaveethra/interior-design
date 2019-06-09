package com.niit.furniturebackend;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.furniturebackend.Dao.CategoryDao;
import com.niit.furniturebackend.Model.Category;

public class CategoryTest {
	public static void main(String args[]) { 
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext();
		ctx.scan("com.niit.*");
		ctx.refresh();
		
		//Category c= new Category();
		Category c=(Category)ctx.getBean("category");
		CategoryDao cDao=(CategoryDao)ctx.getBean("categoryDao");
		
		c.setCatId("c101");
		c.setCatName("category1");
		System.out.println("catid"+c.getCatId());
		System.out.println("catname"+c.getCatName());
		if(cDao.saveorupdate(c)==true)
		{
			System.out.println("category saved");
			
		}
		else
		{
			System.out.println("category  not saved");
			
		}
		c=cDao.getCategory("C101");
		if(c==null)
		{
			System.out.println("Category not found");
		}
		else
		{
			System.out.println("CategoryId : "+c.getCatId());
			System.out.println("CategoryName: "+c.getCatName());
		}
		
		c=cDao.getCategory("C102");
		if(c==null)
		{
			System.out.println("Category not found");
		}
		else if(cDao.delete(c)==true)
		{
			System.out.println("Category deleted");
		}
		else
		{
			System.out.println("Not deleted");
		}
		List<Category> categorylist = cDao.categorylist();
		for(Category c1: categorylist)
		{
			System.out.println("CategoryId : "+c1.getCatId());
			System.out.println("CategoryName: "+c1.getCatName());
			
		}
		
	}

}
