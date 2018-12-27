package com.packtpub.jeepatterns.creational;

import static com.packtpub.jeepatterns.creational.ACL.Group.*;

import java.util.HashMap;
import java.util.Map;

import com.packtpub.jeepatterns.creational.ACL.Group;

public class AccessManagement {
	private static Map<Group, ACL> access = new HashMap<>();
	
	static {
		System.out.println("Creating access control lists...");
		access.put(USER, new ACL(USER,"DO WORK"));
		access.put(ADMIN, new ACL(ADMIN, "MODIFY USERS"));
		access.put(MANAGER, new ACL(MANAGER, "READ REPORTS"));
		access.put(ACCOUNTANT, new ACL(ACCOUNTANT, "MODIFY REPORTS"));
	}
	
	public static ACL getAccessControlList(Group group) {
		ACL ac = null;
		ac = access.get(group);
		if(ac != null) {
			return ac.clone();
		}
		return null;
	}
}
