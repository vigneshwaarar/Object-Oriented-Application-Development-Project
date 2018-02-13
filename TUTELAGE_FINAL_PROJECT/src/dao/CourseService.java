package dao;

import java.util.List;

import model.Course;

public interface CourseService {
	
	public boolean registerCourse(String CourseName, int CourseTypeID);
	
	public int fetchCourse(String CourseName);

	public boolean updateCourse(int CourseID, String CourseName, int CourseType, double CoursePrice);

	public boolean deleteCourse(int CourseID);
}
