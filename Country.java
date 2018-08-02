//@Sasikaladevi Kumarasamy
package lab16;

public class Country {
	
	// instance variable
	private String nameOfCountry;
		
	// default constructor
	public Country() {
	}
	
	// constructor with parameters
	public Country(String nameOfCountry) {
		super();
		this.nameOfCountry = nameOfCountry;
	}
	
	// getters and setters for the instance variable
	public String getNameOfCountry() {
		return nameOfCountry;
	}
	public void setNameOfCountry(String nameOfCountry) {
		this.nameOfCountry = nameOfCountry;
	}
	

}
