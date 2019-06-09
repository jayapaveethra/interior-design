package com.niit.furniturebackend.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Authentication 
{
	@Id
	private String roleId;
	private String roleName="ROLE_USER";
	private String UserName;
   
   
public Authentication() {

	this.roleId ="role"+UUID.randomUUID().toString().substring(30).toUpperCase();
}
public String getRoleId() {
	return roleId;
}
public void setRoleId(String roleId) {
	this.roleId = roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
}
