package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.Users;

public interface UsersDao 
{
	public boolean saveorupdate(Users users);
	public boolean delete(Users users);
	public Users getUsers(String userId);
	public List<Users>userslist();
	public Users isvalidate(String emailId,String password);
	public Users getUsersbyemail(String currentUsername);
}
