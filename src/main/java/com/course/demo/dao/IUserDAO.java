package com.course.demo.dao;

import java.util.List;

import com.course.demo.entity.User;
import com.course.demo.exception.CourseDemoException;

public interface IUserDAO {

	List<User> getAllUsers();
	User createUser(String userName) throws CourseDemoException;
	User getUserById(Integer user);
	User deleteUser(User user);
	List<User> getAllUnEnrollerdUsers(String courseName);
}
