package com.course.demo.dao;

import java.util.List;

import com.course.demo.entity.Course;
import com.course.demo.entity.Role;
import com.course.demo.entity.User;

public interface IUserCourseDAO {

	List<User> getCourseUsersByCourseName(String courseName);
	boolean removeUserCourses(User user);
	boolean mapUserToCourses(User user, Role role, List<Course> courses);
	
}
