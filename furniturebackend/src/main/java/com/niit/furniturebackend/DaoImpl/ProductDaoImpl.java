package com.niit.furniturebackend.DaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model. Shipping;
@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao
{
    @Autowired
    SessionFactory sessionFactory;
    public  ProductDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
	@Override
	public boolean saveorupdate(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		return true;
	}
	@Override
	public boolean delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
		return true;
	}
	@Override
	public Product getProduct(String productId) {
		String q1="from  Product where ProductId='"+productId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Product> list= (List< Product>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    }
	
	@Override
	public List<Product> productlist() {
		List<Product> product =(List< Product>)sessionFactory.getCurrentSession().createCriteria(Product.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return product;
	}
	@Override
	public List<Product> getProductsById(String catId) {
		String c1="from Product where catId='"+catId+"'";
		Query w=sessionFactory.getCurrentSession().createQuery(c1);
		List<Product> list= (List<Product>)w.list();
		if(list==null || list.isEmpty())
		{
			return null;
		}
		
		return list;
	}
	
	@Override
	public List<Product> getProductsBySupplierId(String supplierId)
	{
		String c="from Product where supplierId='"+supplierId+"'";
		Query w=sessionFactory.getCurrentSession().createQuery(c);
		List<Product> list= (List<Product>)w.list();
		if(list==null || list.isEmpty())
		{
			return null;
		}
		
		return list;
	}
    
}
