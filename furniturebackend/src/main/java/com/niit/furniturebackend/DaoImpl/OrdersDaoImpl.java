package com.niit.furniturebackend.DaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.OrdersDao;
import com.niit.furniturebackend.Model. Orders;
@Repository("ordersDao")
@Transactional
public class OrdersDaoImpl implements OrdersDao
{
    @Autowired
    SessionFactory sessionFactory;
    public  OrdersDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
    @Override
	public boolean saveorupdate(Orders  orders) {
		sessionFactory.getCurrentSession().saveOrUpdate(orders);
		return true;
	}
    @Override
	public boolean delete( Orders  orders) {
		sessionFactory.getCurrentSession().delete(orders);
		return true;
	}
    @Override
	public Orders getOrders(String  OrderId) {
		String q1="from  Orders where OrderId='"+OrderId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Orders> list= (List< Orders>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    }
    @Override
	public List<Orders> orderslist() {
		List<Orders> orders=(List< Orders>)sessionFactory.getCurrentSession().createCriteria(Orders.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return orders;
	}

}
