package com.course.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents the User entity for the Course Demo. 
 * @author Bhushan Mahajan
 *
 */
@Entity
@Table(name="users")
public class User extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private String name;

	@Column(name="name",length=256)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
