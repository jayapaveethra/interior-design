package com.niit.furniturebackend.DaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.BillingDao;
import com.niit.furniturebackend.Model.Billing;

@Repository("billingDao")
@Transactional
public class BillingDaoImpl implements BillingDao
{
    @Autowired
    SessionFactory sessionFactory;
    public  BillingDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
    @Override
	public boolean saveorupdate( Billing  billing) {
		sessionFactory.getCurrentSession().saveOrUpdate(billing);
		return true;
	}
    @Override
	public boolean delete( Billing  billing) {
		sessionFactory.getCurrentSession().delete(billing);
		return true;
	}
    @Override
	public Billing getBilling(String  billId) {
		String q1="from  Billing where BillId='"+billId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List< Billing> list= (List< Billing>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    }
    @Override
	public List< Billing>  billinglist() {
		List<Billing> billing=(List<Billing>)sessionFactory.getCurrentSession().createCriteria(Billing.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return billing;
	}

}
