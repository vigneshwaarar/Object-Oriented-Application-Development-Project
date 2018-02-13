package dao;

import java.util.List;

public interface ProfService {
	public boolean addProf(String firstName, String lastName, String username, String password, String profAddress1,
			String profAddress2, String city, String state, int postalCode, String mobileNumber);

	public boolean updateProfProfile(String firstName, String lastName, String username, String password,
			String profAddress1, String profAddress2, String city, String state, int postalCode, String mobileNumber);


}
