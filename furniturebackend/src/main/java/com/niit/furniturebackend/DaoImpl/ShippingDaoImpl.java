package com.niit.furniturebackend.DaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.ShippingDao;
import com.niit.furniturebackend.Model. Shipping;
@Repository("shippingDao")
@Transactional
public class ShippingDaoImpl implements ShippingDao
{
    @Autowired
    SessionFactory sessionFactory;
    public  ShippingDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
    @Override
	public boolean saveorupdate(Shipping  shipping) {
		sessionFactory.getCurrentSession().saveOrUpdate(shipping);
		return true;
	}
    @Override
	public boolean delete(Shipping  shipping) {
		sessionFactory.getCurrentSession().delete(shipping);
		return true;
	}
    @Override
	public Shipping getShipping(String  ShippingId) {
		String q1="from  OrderItems where OrderItemId='"+ShippingId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Shipping> list= (List< Shipping>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    }
    @Override
	public List<Shipping> shippinglist() {
		List<Shipping> shipping=(List< Shipping>)sessionFactory.getCurrentSession().createCriteria(Shipping.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return shipping;
	}

}
