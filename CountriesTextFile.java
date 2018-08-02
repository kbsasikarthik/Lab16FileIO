//@Sasikaladevi Kumarasamy

package lab16;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class CountriesTextFile {
	
	// a constant that stores the name of the file
	public static final String FILE_NAME = "/users/sasi/eclipse-workspace/lab16/src/lab16/countries.txt";
	
	// reads a line from the file and converts it to an object
	private static Country convertLineToObject(String lineFromFile) {
		String[] parts = lineFromFile.split("\n");
		Country country = new Country();
		country.setNameOfCountry(parts[0]);
		return country;
	}
	
	private static String convertObjectToLine(Country country) {
		return country.getNameOfCountry();
	}
	
	public static ArrayList<Country> readFile(){
		ArrayList<Country> countries = new ArrayList<>();
		try (
				// Open/prepare the resources in the try resources block
				FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
				Scanner fileScanner = new Scanner(fileInputStream)
			) {
				// loop until the end of the file
				while (fileScanner.hasNextLine()) {
					// read each line as a string
					String lineFromFile = fileScanner.nextLine();
					// then convert it to an object
					countries.add( convertLineToObject(lineFromFile) );
				}
				
			} catch (FileNotFoundException ex) {
				// If the file doesn't exist, there just aren't any items.
				createBlankFile(FILE_NAME);
				return countries;
			} catch (IOException e) {
				// If something else crazy goes wrong, print out the error.
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}
			
		return countries;
		
	}
	
	public static void appendLine (Country country) {
		
		String lineToFile = convertObjectToLine(country);
		
		try (
				// The `true` here tells the FileOutputStream to append to the file rather than overwriting it
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, true);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);
			) {
				// write to the file
				fileWriter.println(lineToFile);
				
			} catch (IOException e) {
				// If something else crazy goes wrong, print out the error.
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}
	}
	
	public static void writeFile(ArrayList<Country> countries) {
		try (
			// The `false` here tells the FileOutputStream to overwrite the file, rather than append to it
			FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, false);
			PrintWriter fileWriter = new PrintWriter(fileOutputStream);
		) {
			// write to the file
			for (Country country : countries) {
				// each item must be converted to a string of text to write to the file
				String lineToFile = convertObjectToLine(country);
				fileWriter.println(lineToFile);
			}
			
		} catch (IOException e) {
			// If something else crazy goes wrong, print out the error.
			System.err.println("Something unexpected happended.");
			e.printStackTrace();
		}
	}
	public static void createDirectory(String pathName) {
		Path path = Paths.get(pathName);
		if (Files.notExists(path)) {
			try {
				Files.createDirectories(path);
				System.out.println("Directory created at " + path.toAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createBlankFile(String pathName) {
		Path path = Paths.get(pathName);
		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
				System.out.println("File created at " + path.toAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
