package com.course.demo.dao;

import java.util.List;

import com.course.demo.entity.Course;

public interface ICourseDAO {

	List<Course> getAllCourses();
	List<Course> getCoursesByIds(List<Integer> courseIds);
	Integer createCourse(Course course) throws Exception;
}
