package com.course.demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.course.demo.entity.Course;
import com.course.demo.exception.CourseDemoException;

@Repository
public class CourseDAO implements ICourseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Course> getAllCourses() {
		Session session= null;
		List<Course> allCourses = null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Course.class);
				criteria.add(Restrictions.isNull("deleted"));
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("id"));
				projectionList.add(Projections.property("name"));
				criteria.setProjection(projectionList);
				criteria.addOrder(Order.asc("name"));
				allCourses = criteria.list();
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return allCourses;
	}
	
	@Override
	public List<Course> getCoursesByIds(List<Integer> courseIds) {
		Session session= null;
		List<Course> allCourses = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Course.class);
				criteria.add(Restrictions.in("id", courseIds));
				criteria.add(Restrictions.isNull("deleted"));
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("id"));
				projectionList.add(Projections.property("name"));
				criteria.setProjection(projectionList);
				criteria.addOrder(Order.asc("name"));
				allCourses = criteria.list();
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return allCourses;
	}

	@Override
	public Integer createCourse(Course course) throws Exception {
		Session session= null;
		Integer id = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				id =(Integer) session.save(course);
				System.out.println("Course is created With Id::"+id);
				session.getTransaction().commit();
				
			}finally {
					if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CourseDemoException(e.getMessage());
		}
		return id;
	}

}
