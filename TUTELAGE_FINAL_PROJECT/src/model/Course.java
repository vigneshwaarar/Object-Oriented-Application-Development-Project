package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Course information like name, type and ID
 * are stored in this class.
 */
public class Course {

private StringProperty Course_Type;
private StringProperty Course_Name;
public Course()
{
	
}

public Course(String Course_Name, String Course_Type)
{
	 this.Course_Name =new SimpleStringProperty(Course_Name);
	 this.Course_Type = new SimpleStringProperty(Course_Type);
}

	// Instance fields.
	private String CourseName;
	private String CourseType;
	private int CourseID;
	
	// Getters and Setters for instance fields.
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String CourseName) {
		this.CourseName = CourseName;
	}
	public String getCourseType() {
		return CourseType;
	}
	public void setCourseType(String CourseType) {
		this.CourseType = CourseType;
	}

	public int getCourseID() {
		return CourseID;
	}
	public void setCourseID(int CourseID) {
		this.CourseID = CourseID;
	}
	
}
