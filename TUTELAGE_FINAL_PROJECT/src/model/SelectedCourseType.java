package model;

/**
 * When Administrator selects the COURSE type, then
 * those selected COURSE type informations are stored in this class.
 */
public class SelectedCourseType {

	// Instance fields.
	private static int typeID;
	private static String name;
	private static String description;
	
	// Getters and Setters for instance fields.
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		SelectedCourseType.typeID = typeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		SelectedCourseType.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		SelectedCourseType.description = description;
	}

	
}
