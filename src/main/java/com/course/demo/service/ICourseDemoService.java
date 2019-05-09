package com.course.demo.service;

import java.util.List;

import com.course.demo.entity.User;
import com.course.demo.vo.UserVO;

public interface ICourseDemoService {
	User addNewUser(UserVO user);
	boolean deleteUser(Integer userId);
	List<User> getAllStudents(String courseName);
	List<User> getAllUnEnrolledStudents(String courseName);
}
