package com.phalaenopsis.model;

public enum RoleType {
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN"),
	ACTOR("ACTOR"),
	DIRECTOR("DIRECTOR");
	
	String roleType;
	
	private RoleType(String roleType){
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}
	
}
