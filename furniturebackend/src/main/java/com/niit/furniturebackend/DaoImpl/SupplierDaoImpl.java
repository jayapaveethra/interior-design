package com.niit.furniturebackend.DaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.SupplierDao;
import com.niit.furniturebackend.Model. Supplier;
@Repository("supplierDao")
@Transactional
public class SupplierDaoImpl implements SupplierDao
{
    @Autowired
    SessionFactory sessionFactory;
    public  SupplierDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
    @Override
	public boolean saveorupdate(Supplier  supplier) {
		sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		return true;
	}
    @Override
	public boolean delete(Supplier  supplier) {
		sessionFactory.getCurrentSession().delete(supplier);
		return true;
	}
    @Override
	public Supplier getSupplier(String  supplierId) {
		String q1="from  Supplier where SupplierId='"+supplierId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Supplier> list= (List< Supplier>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    }
    @Override
	public List<Supplier> supplierlist() {
		List<Supplier> supplier=(List< Supplier>)sessionFactory.getCurrentSession().createCriteria(Supplier.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return supplier;
	}

}
