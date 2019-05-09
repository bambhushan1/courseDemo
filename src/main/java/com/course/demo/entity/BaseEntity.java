package com.course.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class represents the base entity for all the modules of the Course Demo. It
 * Contains all the common filed that will be part of all the Entities of the 
 * Course Demo.
 *  
 * @author Bhushan Mahajan
 *
 */
@MappedSuperclass
public class BaseEntity {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date created;
	private Date deleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created", nullable=false)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="deleted", nullable=true)
	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

}
