package com.course.demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.course.demo.entity.Course;
import com.course.demo.entity.Role;
import com.course.demo.entity.User;
import com.course.demo.entity.UserCourse;
import com.course.demo.exception.CourseDemoException;
/**
 * This class is the DAO class used to handle Users courses of the application
 *  
 * @author Bhushan Mahajan
 *
 */
@Repository
public class UserCourseDAO implements IUserCourseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	//Get all students, sorted by their name, for a given course with course name as input.
	@Override
	public List<User> getCourseUsersByCourseName(String courseName) {
		Session session= null;
		List<User> users = null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(UserCourse.class);
				criteria.createAlias("user", "userAlias");
				criteria.createAlias("course", "courseAlias");
				criteria.createAlias("role", "roleAlias");
				criteria.add(Restrictions.eq("courseAlias.name", courseName));
				criteria.add(Restrictions.eq("roleAlias.name", "student"));
				criteria.add(Restrictions.isNull("deleted"));
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("userAlias.id"));
				projectionList.add(Projections.property("userAlias.name"));
				criteria.setProjection(projectionList);
				criteria.addOrder(Order.asc("userAlias.name"));
				criteria.setResultTransformer(new AliasToBeanResultTransformer(User.class));
				users = criteria.list();
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return users;
	}

	@Override
	public boolean removeUserCourses(User user) {
		Session session= null;
		boolean result = false;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			try {
				session = sessionFactory.openSession();
				SQLQuery createSQLQuery = session.createSQLQuery("UPDATE users_courses SET deleted=now() WHERE user_id = :userId AND deleted IS NULL");
				createSQLQuery.setParameter("userId", user.getId());
				createSQLQuery.executeUpdate();
				transaction.commit();
				result = true;
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean mapUserToCourses(User user, Role role, List<Course> courses) {
		Session session= null;
		boolean result = false;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			try {
				session = sessionFactory.openSession();
				if(courses != null && courses.size() > 0) {
					for(Course course : courses) {
						UserCourse userCourse = getUserCourseEntity(user, role, course);
						session.save(userCourse);
					}
				}
				transaction.commit();
				result = true;
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return result;
	
	}

	private UserCourse getUserCourseEntity(User user, Role role, Course course) {
		UserCourse userCourse = new UserCourse();
		userCourse.setUser(user);
		userCourse.setCourse(course);
		userCourse.setRole(role);
		return userCourse;
	}
}
