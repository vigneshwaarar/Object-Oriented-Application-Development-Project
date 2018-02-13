package model;

/**
 * Informations about COURSE types like type name and type description
 * are stored in this class. 
 */
public class CourseType {

	// Instance fields.
	private String typeName;
	private String typeDescription;
	
	// Getters and Setters for instance fields.
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}


}
