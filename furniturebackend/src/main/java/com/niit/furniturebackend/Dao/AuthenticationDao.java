package com.niit.furniturebackend.Dao;

import java.util.List;

import com.niit.furniturebackend.Model.Authentication;

public interface AuthenticationDao 
{
	
		public boolean saveorupdate(Authentication authentication);
		public boolean delete(Authentication authentication);
		public Authentication getAuthentication(String roleId);
		public List<Authentication> authenticationlist();

	}


