package com.course.demo.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.course.demo.entity.Role;
import com.course.demo.exception.CourseDemoException;

/**
 * This class is the DAO class used to handle Roles of the application
 *  
 * @author Bhushan Mahajan
 *
 */
@Repository
public class RoleDAO implements IRoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role getRoleByName(String roleName) {
		Session session= null;
		Role role= null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Role.class);
				criteria.add(Restrictions.isNull("deleted"));
				criteria.add(Restrictions.eq("name", roleName));
				role = (Role) criteria.uniqueResult();
			}finally {
					if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return role;
	}

}
