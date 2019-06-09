package com.niit.furniturebackend.DaoImpl;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.CategoryDao;
import com.niit.furniturebackend.Model.Category;

@Repository("categoryDao")
@Transactional

public class CategoryDaoImpl implements CategoryDao
{
    @Autowired
    SessionFactory sessionFactory;
    public CategoryDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
    @Override
	public boolean saveorupdate(Category category) {
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;
	}
    @Override
	public boolean delete(Category category) {
		sessionFactory.getCurrentSession().delete(category);
		return true;
	}
    @Override
	public Category getCategory(String catId) {
		String q1="from Category where CatId='"+catId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Category> list= (List<Category>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    }
    @Override
	public List<Category> categorylist() {
		List<Category> categories=(List<Category>)sessionFactory.getCurrentSession().createCriteria(Category.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return categories;
	}

}
