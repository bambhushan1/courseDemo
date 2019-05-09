package com.course.demo.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.course.demo.entity.User;
import com.course.demo.entity.UserCourse;
import com.course.demo.exception.CourseDemoException;

/**
 * This class is the DAO class used to handle Users of the application
 *  
 * @author Bhushan Mahajan
 *
 */
@Repository
public class UserDao implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Session session= null;
		List<User> allUsers = null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.isNull("deleted"));
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("id"));
				projectionList.add(Projections.property("name"));
				criteria.setProjection(projectionList);
				criteria.addOrder(Order.asc("name"));
				allUsers = criteria.list();
			}finally {
					if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return allUsers;
	}

	@Override
	public User createUser(String userName) {
		Session session= null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				user = new User();
				user.setName(userName);
				Integer id = (Integer) session.save(user);
				System.out.println("User is created With Id::"+id);
				session.getTransaction().commit();
				
			}finally {
					if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return user;
	}

	@Override
	public User getUserById(Integer userId) {
		Session session= null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				user = session.get(User.class, userId);
			}finally {
					if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return user;
	}

	@Override
	public User deleteUser(User user) {
		Session session= null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				user.setDeleted(new Date());
				session.update(user);
				session.getTransaction().commit();
				
			}finally {
					if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getAllUnEnrollerdUsers(String courseName) {
		Session session= null;
		List<User> allUsers = null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.isNull("deleted"));
				criteria.add(Subqueries.ne("id", getSubqueryToFetchUserCoursesUsingCourseName(courseName)));
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("id"));
				projectionList.add(Projections.property("name"));
				criteria.setProjection(projectionList);
				criteria.addOrder(Order.asc("name"));
				allUsers = criteria.list();
			}finally {
					if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return allUsers;
	}
	
	private DetachedCriteria getSubqueryToFetchUserCoursesUsingCourseName(String courseName) {
		DetachedCriteria subquery = DetachedCriteria.forClass(UserCourse.class);
		subquery.add(Restrictions.eq("course.name", courseName));
		subquery.add(Restrictions.isNull("deleted"));
		subquery.setProjection(Projections.property("user.id"));
		return subquery;
	}
}
