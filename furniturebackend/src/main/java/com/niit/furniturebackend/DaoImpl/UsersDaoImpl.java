package com.niit.furniturebackend.DaoImpl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.Model.Pay;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model. Users;
@Repository("usersDao")
@Transactional
public class UsersDaoImpl implements UsersDao
{
    @Autowired
    SessionFactory sessionFactory;
    public UsersDaoImpl(SessionFactory sessionFactory) 
    {
    	this.sessionFactory=sessionFactory;
    }
	@Override
	public boolean saveorupdate(Users users) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		return true;
	}
	@Override
	public boolean delete(Users users) {
		sessionFactory.getCurrentSession().saveOrUpdate(users);
		return true;
	}
	@Override
	public Users getUsers(String usersId) {
		String q1="from Users where UserId='"+usersId+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Users> list= (List<Users>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    
	}
	@Override
	public Users  getUsersbyemail(String currentUsername) {
		String q1="from Users where emailid='"+currentUsername+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Users> list= (List<Users>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
    
	}
	@Override
	public List<Users> userslist() {
		List<Users> users =(List< Users>)sessionFactory.getCurrentSession().createCriteria(Users.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return users;
	}
	@Override
	public Users isvalidate(String emailId, String password)
	{
		String q1="from Users where emailid='"+emailId+"' and password= '"+password+"'";
		Query w= sessionFactory.getCurrentSession().createQuery(q1);
		List<Users> list= (List<Users>)w.list();
		if(list==null || list.isEmpty())
		{
		return null;
	}
		return list.get(0);
	}
    

}

