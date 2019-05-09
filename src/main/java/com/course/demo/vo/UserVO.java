package com.course.demo.vo;

import java.util.List;
/**
 * This class is designed to get new user request
 *  
 * @author Bhushan Mahajan
 *
 */
public class UserVO {

	private String name;
	private String role;
	private List<Integer> courseIds;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Integer> getCourseIds() {
		return courseIds;
	}
	public void setCourseIds(List<Integer> courseIds) {
		this.courseIds = courseIds;
	}
	
}
