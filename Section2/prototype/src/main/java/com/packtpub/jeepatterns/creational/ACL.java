package com.packtpub.jeepatterns.creational;

/**
 * Class representing an Access Control List (ACL). 
 * An Access Control List is a data structure used to guard access to
 * resources.
 */
public class ACL implements Prototype {
	
	public static enum Group {
		USER, ADMIN, MANAGER, ACCOUNTANT
	}
	
	private final Group group;
	private String permissions;
	
	public ACL(Group group, String access) {
		this.group = group;
		this.permissions = access;
	}
	
	@Override
	public ACL clone() {
		try {
			return (ACL) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Group getGroup() {
		return group;
	}

	public String getPermission() {
		return permissions;
	}

	public void setPermission(String access) {
		this.permissions = access;
	}
}
