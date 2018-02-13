package dao;

import java.util.List;

import model.CourseType;

public interface CourseTypeService {
	public List<String> fetchTypeName();

	public List<CourseType> fetchCourseType();

	public int fetchTypeID(String typeName);

	public boolean insertCourseType(String typeName, String typeDescription);

	public boolean updateCourseType(int typeID, String typeName, String typeDescription);

	public boolean deleteCategory(int typeID);
}
