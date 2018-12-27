package com.packtpub.jeepatterns.creational;

public class User {
	private final String name;
	private final String role;
	private final ACL aCL;

	public User(String name, String role, ACL aCL){
		this.name = name;
		this.role = role;
		this.aCL = aCL;
	}
	
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	public ACL getACL() {
		return aCL;
	}
	
	@Override
	public String toString(){
		return "Name: " + name + ", Role: "+ role + ", Access Group: " + aCL.getGroup() + ", Permission: " + aCL.getPermission();
	}
}
