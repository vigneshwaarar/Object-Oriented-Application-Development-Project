package model;

/**
 * Informations about course types like type name and type description
 * are stored in this class. 
 */
public class AllCourseTypeDetails {

	// Instance fields.
	private String typeName;
	private String StudentCount;
	private String ProfName;
	
	// Getters and Setters for instance fields.
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getProfName() {
		return ProfName;
	}
	public void setProfName(String ProfName) {
		this.ProfName = ProfName;
	}
	public String getStudentCount() {
		return StudentCount;
	}
	public void setStudentCount(String StudentCount) {
		this.StudentCount = StudentCount;
	}

}
