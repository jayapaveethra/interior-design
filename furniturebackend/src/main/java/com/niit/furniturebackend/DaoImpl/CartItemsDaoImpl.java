package com.niit.furniturebackend.DaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.CartItemsDao;
import com.niit.furniturebackend.Model. CartItems;
@Repository("cartitemsDao")
@Transactional
public class CartItemsDaoImpl implements CartItemsDao
{
    @Autowired
    SessionFactory sessionFactory;
    public  CartItemsDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
    @Override
	public boolean saveorupdate( CartItems cartItems) {
		sessionFactory.getCurrentSession().saveOrUpdate(cartItems);
		return true;
	}
    @Override
	public boolean delete( CartItems  cartItems) {
		sessionFactory.getCurrentSession().delete( cartItems);
		return true;
	}
    @Override
	public CartItems getCartItems(String  CartItemsId) {
		String q1="from  CartItems where CartItemsId='"+CartItemsId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List< CartItems> list= (List< CartItems>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    }
    @Override
	public List<CartItems> cartItemslist(String cartId) {
    	String q1="from  CartItems where Cart_Id='"+cartId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List< CartItems> list= (List< CartItems>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list;
	}
    @Override
    public List<CartItems> getCartItemsByProductId(String productId)
    {
    	String q1="from CartItems where prodId='"+productId+"'";
    	Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List< CartItems> list= (List< CartItems>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list;
	}
   }



