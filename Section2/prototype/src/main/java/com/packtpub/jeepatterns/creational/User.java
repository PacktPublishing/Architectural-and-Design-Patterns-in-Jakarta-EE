package com.packtpub.jeepatterns.creational;

public class User {
	private final String name;
	private final String role;
	private final ACL acl;

	public User(String name, String role, ACL acl){
		this.name = name;
		this.role = role;
		this.acl = acl;
	}
	
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	public ACL getACL() {
		return acl;
	}
	
	@Override
	public String toString(){
		return "Name: " + name + ", Role: "+ role + ", Access Group: " + acl.getGroup() + ", Permission: " + acl.getPermission();
	}
}
