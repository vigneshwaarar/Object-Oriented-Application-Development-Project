package dao;

import java.util.List;

public interface StudentService {
	public boolean addStudent(String firstName, String lastName, String username, String password, String studentAddress1,
			String studentAddress2, String city, String state, int postalCode, String mobileNumber);

	public boolean updateStudentProfile(String firstName, String lastName, String username, String password,
			String studentAddress1, String studentAddress2, String city, String state, int postalCode, String mobileNumber);

}
