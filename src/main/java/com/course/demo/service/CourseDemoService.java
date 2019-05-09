package com.course.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.demo.dao.ICourseDAO;
import com.course.demo.dao.IRoleDAO;
import com.course.demo.dao.IUserCourseDAO;
import com.course.demo.dao.IUserDAO;
import com.course.demo.entity.Course;
import com.course.demo.entity.Role;
import com.course.demo.entity.User;
import com.course.demo.vo.UserVO;

/**
 * This class is the main Service class of Course Demo application
 *  
 * @author Bhushan Mahajan
 *
 */
@Service
public class CourseDemoService implements ICourseDemoService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private ICourseDAO courseDAO;
	
	@Autowired
	private IUserCourseDAO userCourseDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	// 2.1). Add a new student along with their course registrations.
	@Override
	public User addNewUser(UserVO userVO) {
		//TODO: Validate Create user request 
		User newUser = userDAO.createUser(userVO.getName());
		if(newUser != null && userVO.getCourseIds() != null && userVO.getCourseIds().size() > 0) {
			List<Course> courses = courseDAO.getCoursesByIds(userVO.getCourseIds());
			if(courses != null && courses.size() > 0) {
				Role role = roleDAO.getRoleByName(userVO.getRole() != null && userVO.getRole().equalsIgnoreCase("teacher") ? "teacher" : "student");
				userCourseDAO.mapUserToCourses(newUser, role, courses);
			}
		}
		return newUser;
	}

	//2.2). Delete a student.
	@Override
	public boolean deleteUser(Integer userId) {
		//TODO: Validate the request
		User user = userDAO.getUserById(userId);
		if(user!= null) {
			userCourseDAO.removeUserCourses(user);
			userDAO.deleteUser(user);
			return true;
		}
		return false;
	}

	//2.3).Get all students, sorted by their name, for a given course with course name as input.
	@Override
	public List<User> getAllStudents(String courseName) {
		//TODO: Validate the course
		List<User>  users = null;
		if(courseName != null)		
			users =  userCourseDAO.getCourseUsersByCourseName(courseName);
		return users;
	}

	//2.5). Get all students who don’t register for a given course  
	@Override
	public List<User> getAllUnEnrolledStudents(String courseName) {
		//TODO: Validate the course
		List<User>  users = null;
		if(courseName != null)		
			users =  userDAO.getAllUnEnrollerdUsers(courseName);
		return users;
	}

}
