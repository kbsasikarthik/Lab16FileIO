//@ Sasikaladevi Kumarasamy
package lab16;

import java.util.ArrayList;
import java.util.Scanner;

// Uses fileI/O system to read data from file and create objects with it and store the objects in an ArrayList
// write user entered data to file as well as corresponding object to the ArrayList
// user can List, Add, Remove or Exit
public class CountriesApp {
	
	// a static variable which can be used by all methods in this class
	public static Scanner sc = new Scanner(System.in); 
	
	public static void main(String[] args) {
		
		int menuOption=0;
		boolean isValid = false;
		
		// create a new ArrayList to store the objects
		ArrayList<Country> countries = new ArrayList<>();
		
		// display welcome message
		System.out.println("Welcome to the List of Countries!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		do {
			mainMenu();
			menuOption = Validator.getInteger(sc,"Please enter your option from the menu(1,2,3,4): ", 1, 4);
			sc.nextLine();
			if(menuOption == 1) {
				listCountries(countries);
				isValid=false;
			}else if(menuOption == 2) {
				addCountry(countries);
				isValid=false;
			}else if(menuOption == 3) {
				countries=removeCountry(countries);
				isValid=false;
			}else
				isValid = true;
			
		} while(!isValid);
		
		// display concluding message
		System.out.println("\nThanks for your interest! Bye!!");
	
	} //  end of main method
	
	// displays main menu with options for the user to select from
	private static void mainMenu() {
		System.out.println("\nMain Menu:\n---- -----");
		System.out.println("1. List Countries\n2. Add a Country\n3. Remove a Country\n4. Exit");
	}
	
	// reads data from the file, stores it as objects in an ArrayList and then and lists it
	private static void listCountries(ArrayList<Country> countries) {
		System.out.println("\nList of countries on File:\n**** ** ********* ** *****");
		countries = CountriesTextFile.readFile();
		for (Country country : countries) {
			System.out.println(country.getNameOfCountry());
		}
	}
	
	// adds the user entered data to the file and updates the ArrayList containing
	// corresponding objects as well
	private static void addCountry(ArrayList<Country> countries) {
		String addCountry="";
		addCountry = Validator.getString(sc, "Enter a Country to be added: ");
		Country newCountry = new Country(addCountry.trim());
		countries.add(newCountry);
		CountriesTextFile.appendLine(newCountry);
		System.out.println("\n"+addCountry.trim()+" has been added!");
	}
	
	// removes the data chosen by the user and updates the file and the ArrayList as well
	private static ArrayList<Country> removeCountry(ArrayList<Country> countries) {
		listCountries(countries);
		String removeCountry = Validator.getString(sc,"\nPlease enter a country from the list to remove: ");
		if(removeCountry.isEmpty()) {
			System.out.println("Invalid entry! Try again.");
			removeCountry(countries);
		}else {
			countries = CountriesTextFile.readFile();
			for(int i = 0; i<countries.size();i++) {
				if(countries.get(i).getNameOfCountry().equalsIgnoreCase(removeCountry.trim())) {
					countries.remove(i);
				}
			}
			System.out.println("\n"+removeCountry.trim().toUpperCase() +" has been removed!");
			CountriesTextFile.writeFile(countries);
			
		}
		return countries;
	}

}// end of class
