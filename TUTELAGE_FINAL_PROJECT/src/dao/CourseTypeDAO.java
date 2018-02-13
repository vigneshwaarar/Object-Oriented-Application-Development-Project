package dao;

//Package for SQL class object
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//Package for List interface and ArrayList class.
import java.util.ArrayList;
import java.util.List;

//Imported the required classes
import controller.AdminLoginController;
import controller.ProfLoginController;
import controller.StudentLoginController;
import model.Administrator;
import model.AllCourseTypeDetails;
import model.Course;
import model.CourseType;
import model.Prof;
import model.Student;

/**
 * This is a DAO class which contains the queries related to COURSE TYPE table.
 */
public class CourseTypeDAO implements CourseTypeService{

	DbConnection dbConn = new DbConnection();
	Connection con;
	Statement stmt = null;
	Prof proff = ProfLoginController.getprof();
	Administrator admin = AdminLoginController.getAdmin();
	Student std = StudentLoginController.getStudent();

	/**
	 * This method is used to fetch all the COURSE type names.
	 * @return
	 */
	public List<String> fetchTypeName() {
		// Array List has been created to store the type names.
		List<String> courseTypes = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();

			// Query to fetch all the type name from COURSE table.
			String sql = "SELECT type_name FROM tutelage_course ORDER BY type_id";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String typeName = rs.getString(1);
				courseTypes.add(typeName);
			}
			// Connection closed.
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return courseTypes;
	}

	/**
	 * This method fetches the all the COURSE type and its information.
	 * This method is called by when all the STUDENT or Administrator or PROFESSOR who wants to view the COURSE types.
	 * @return
	 */
	public List<CourseType> fetchCourseType() {
		List<CourseType> items = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();
			// Query to fetch the COURSE type details from COURSE table.
			String sql = "SELECT type_name, type_description FROM tutelage_course ORDER BY type_id";
			rs = stmt.executeQuery(sql);

			// Collects the all the COURSE type details, if it is present.
			while (rs.next()) {
				CourseType pt = new CourseType();
				pt.setTypeName(rs.getString(1));
				pt.setTypeDescription(rs.getString(2));
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				items.add(pt);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Returns the COURSE type object.
		return items;
	}

	/**
	 * This method returns the COURSETypeID for a given COURSE type name.
	 * @param typeName
	 * @return
	 */
	public int fetchTypeID(String typeName) {
		ResultSet rs = null;
		// typeID (i.e. COURSEID) variable has been created and set to zero.
		int typeID = 0;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();

			//// Query to fetch the COURSEID for a given COURSE type name.
			String sql = "SELECT type_id FROM tutelage_course WHERE type_name='" + typeName + "'";
			rs = stmt.executeQuery(sql);
			
			// If the COURSEID is present, then that ID is set to typeID variable.
			while (rs.next()) {
				typeID = rs.getInt(1);
			}
			// Connection closed.
			con.close();
		} 
		// Returns zero if SQL exception is caught.
		catch (SQLException e) {
			e.printStackTrace();
			return typeID;
		}
		return typeID;
	}

	/**
	 * When Administrator tries to create a new COURSE type, then this method is called.
	 * This method is used to insert the COURSE type information like name and description.
	 * @param typeName
	 * @param typeDescription
	 * @return
	 */
	public boolean insertCourseType(String typeName, String typeDescription) 
	{
		try {
			int adminID = admin.getAdminID();
			// Database connection is established.
			con = dbConn.getConnections();
			stmt = con.createStatement();
			// Query to insert the new COURSE type in  table.
			String query = "INSERT INTO tutelage_course (type_name, type_description,createdby) " + "VALUES ('"
					+ typeName + "','" + typeDescription + "'," + adminID + ")";

			stmt.executeUpdate(query);
			System.out.println("New Course Type has been inserted.");
			// Database connection is closed.
			con.close();

		} catch (SQLException e) {
			e.getMessage();
			return false;
		}
		return true;
	}

	
	public boolean insertCourseType_Prof(String typeName, String typeDescription) 
	{
		try {
			int profID = proff.getProfID();
			// Database connection is established.
			con = dbConn.getConnections();
			stmt = con.createStatement();
			// Query to insert the new COURSE type in  table by the professor
			String query = "INSERT INTO tutelage_course (type_name, type_description,createdby) " + "VALUES ('"
					+ typeName + "','" + typeDescription + "'," + profID+ ")";

			stmt.executeUpdate(query);
			System.out.println("New Course Type has been inserted.");
			// Database connection is closed.
			con.close();

		} catch (SQLException e) {
			e.getMessage();
			return false;
		}
		return true;
	}
	/**
	 * This method is used to update the COURSE type details.
	 * @param typeID
	 * @param typeName
	 * @param typeDescription
	 * @return
	 */
	public boolean updateCourseType(int typeID, String typeName, String typeDescription) {
		try {
			int adminID = admin.getAdminID();
			// Database Connection is established.
			con = dbConn.getConnections();
			stmt = con.createStatement();
			
			// Query to update the COURSE type details.
			String sql = "UPDATE tutelage_course SET type_name = '" + typeName + "', type_description ='"
					+ typeDescription + "', " + "updatedby = " + adminID + " WHERE type_id = " + typeID;

			System.out.println("Update Query: " + sql);
			// Update query is executed.
			stmt.executeUpdate(sql);
			System.out.println("COURSE  has been updated");
			con.close();
			
		} catch (SQLException e) {
			e.getMessage();
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used to delete the particular COURSE type.
	 * @param typeID
	 * @return
	 */
	public boolean deleteCategory(int typeID){
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();
			
			// Query to delete the particular COURSE.
			String query = "DELETE FROM tutelage_course WHERE type_id = " + typeID;
			
			System.out.println("Delete Query: "+query);
			stmt.executeUpdate(query);
			System.out.println("Selected COURSE Type has been deleted");
			//Connection is closed.
			con.close();
		}
		//Returns false if SQL exception is caught.
		catch(SQLException e){
			e.getMessage();
			return false;
		}
		return true;
	}
	
	/**
	 * This method fetches the all the COURSE and its information.
	 * This method is called by when all the student or Administrator or professor who wants to view the COURSE.
	 * @return
	 */
	public List<Course> fetchCourse() {
		List<Course> Course = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();
			// Query to fetch the COURSE details from COURSE table.
			String sql = "SELECT TYPE_NAME,TYPE_DESCRIPTION"
					+ " FROM tutelage_course;";
			rs = stmt.executeQuery(sql);
			System.out.println("select Query: " + sql);

			// Collects the all the COURSE details
			while (rs.next()) {
				Course crs = new Course();
				crs.setCourseName(rs.getString(1));
				crs.setCourseType(rs.getString(2));
				Course.add(crs);
			}
			// Connection closed.
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Returns the course object.
		return Course;
	}
	
	/**
	 * This method fetches the all the COURSE registration history and its information.
	 * This method is called by when both the student or Administrator who wants to view the COURSE registration history.
	 * @return
	 */
	public List<Course> fetchCourseHistory() {
		List<Course> Course = new ArrayList<>();
		int StudentID = std.getStudentID();
		ResultSet rs = null;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();
			// Query to fetch the COURSE registration history details from COURSE table.
			String sql = "SELECT b.TYPE_NAME,b.TYPE_DESCRIPTION"
					+ " FROM tutelage_course_register a, tutelage_course b where a.courseid = b.type_id and StudentID = " + StudentID + ";";
			rs = stmt.executeQuery(sql);
			System.out.println("history Query: " + sql);

			// Collects the all the COURSE registration history details
			while (rs.next()) {
				Course crs = new Course();
				crs.setCourseName(rs.getString(1));
				crs.setCourseType(rs.getString(2));
				Course.add(crs);
			}
			// Connection closed.
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Returns the course object.
		return Course;
	}
	

	/**
	 * This method fetches the all the COURSE ENROLLEMENT and its information.
	 * This method is called by when BY the Administrator who wants to view the courses.
	 * @return
	 */
	public List<AllCourseTypeDetails> fetchCourseDetails() {
		List<AllCourseTypeDetails> CourseDetails = new ArrayList<>();
		//int StudentID = std.getStudentID();
		ResultSet rs = null;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();
			// Query to fetch the overall course details from coursetype table.
			String sql = "SELECT D.TYPE_NAME, C.USERNAME AS PROF_NAME, COUNT(A.STUDENTID) AS COUNTING FROM tutelage_course_register A, "
					+ "tutelage_student B, tutelage_prof C, tutelage_course D WHERE A.STUDENTID=B.STUDENTID AND "
					+ "A.COURSEID=D.TYPE_ID AND C.PROFID=D.CREATEDBY GROUP BY TYPE_NAME;";
			rs = stmt.executeQuery(sql);
			System.out.println("All Course Query: " + sql);
			
			// Collects the all the COURSE ENROLLEMENT details, if it is present.
			while (rs.next()) {
				AllCourseTypeDetails crs = new AllCourseTypeDetails();
				crs.setTypeName(rs.getString(1));
				System.out.println(rs.getString(1));
				crs.setProfName(rs.getString(2));
				System.out.println(rs.getString(2));
				crs.setStudentCount(rs.getString(3));
				System.out.println(rs.getString(3));
				CourseDetails.add(crs);
			}
			// Connection closed.
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Returns the course object.
		return CourseDetails;
	}
	
	/**
	 * This method returns the CourseID for a given Course name.
	 * @param CourseName
	 * @return
	 */
	public int fetchCourseID(String CourseName) {
		ResultSet rs = null;
		// itemID (i.e. COURSEID) variable has been created and set to zero. 
		int CourseID = 0;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();

			// Query to fetch the COURSEID for a given COURSEname.
			String sql = "SELECT type_id FROM tutelage_course WHERE type_name='" + CourseName + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			
			// If the COURSEID is present, then that ID is set to itemID variable.
			while (rs.next()) {
				CourseID = rs.getInt(1);
			}
			con.close();
		} 
		// Returns zero if SQL exception is caught.
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return CourseID;
	}
	

}
