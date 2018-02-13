package model;

/**
 * When Administrator or Student selects the Courses, then
 * those selected Course informations are stored in this class.
 */
public class SelectedCourse {

	// Instance fields.
	private static int id;
	private static String name;
	private static String type;
	private static double price;
	
	// Getters and Setters for instance fields.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		SelectedCourse.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		SelectedCourse.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		SelectedCourse.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		SelectedCourse.price = price;
	}
}
