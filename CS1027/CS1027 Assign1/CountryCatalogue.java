/* Name: Tyler Roberts
 * 
 * This program creates a catalogue containing several objects of the class Country, a map that has a country name as the key and 
 * the continent as the value, and a set containing all continent names. There are also several methods that have been
 * created in order to manipulate or access the catalogue.
*/
import java.util.*;

public class CountryCatalogue {
	private static final int DEFAULT_SIZE = 5;
	private final int NOT_FOUND = -1;
	
	String CountryFilename;
	String ContinentFilename;
	
	Country[] catalogue = new Country[DEFAULT_SIZE]; // Catalogue in the form of an array containing objects of class Country
	int numberOfCountries = 0;
	
	static Set<String> continents = new HashSet<String>() ; //Set containing all continent names 
	static Map<String,String> cDict = new HashMap<String,String>(); // Map with country names as the key and continent names as values
	
	
	CountryCatalogue(String countryFile, String continentFile){ // Constructor for CountryCatalogue which takes in the country data file and the continent data file
		this.CountryFilename = countryFile;
		this.ContinentFilename = continentFile; 
		
		ThingToReadFile ContinentInformation = new ThingToReadFile(continentFile); // This reads the continent file which then splits the data and stores it into the Set and Map 
		ContinentInformation.readLine();
		while (ContinentInformation.endOfFile() != true) {
			String y = ContinentInformation.readLine();
			String splity[] = y.split(",");
			String CountryName = splity[0];
			String ContinentName = splity[1];
			continents.add(ContinentName);
			cDict.put(CountryName, ContinentName);		
			}
		ContinentInformation.close();
		
		ThingToReadFile CountryInformation = new ThingToReadFile(countryFile); // This reads the country file which splits the data to create a Country using the class Country and stores it into the catalogue
		CountryInformation.readLine();
		while (CountryInformation.endOfFile()!= true) {
			String x = CountryInformation.readLine();
			String splitx[] = x.split(",");
			String Countryname = splitx[0];
			int Population = Integer.parseInt(splitx[1]);
			float Area = Float.parseFloat(splitx[2]);
			String ContinentName = cDict.get(Countryname);
			Country newcountry = new Country(Countryname,Population,Area, ContinentName);
			addCatalogue(newcountry);
			}
		CountryInformation.close();	
	}

	
	private void addCatalogue(Country countryname) { // This method puts the countries of class Country into the catalogue and expands the catalogue if too small
		
		if (numberOfCountries == catalogue.length)
				expandCapacity();
		catalogue[numberOfCountries] = countryname;
		numberOfCountries++;
		}
	
	private void expandCapacity() { // This method creates a larger array for the catalogue if there is not enough space to store all the data
		
		Country[] largercatalogue = new Country[catalogue.length * 2];
		for (int i = 0; i< catalogue.length; i++) 
			largercatalogue[i] = catalogue[i];
		catalogue = largercatalogue;
	}
	
	public void addCountry(Country CountryName) { // This method takes in a country of class Country and adds it's continent and countryname to the Map and Set and also to the catalogue
		addCatalogue(CountryName);
		String stringCountryName = CountryName.getCountry();
		String stringContinentName = CountryName.getContinent();
		continents.add(stringContinentName);
		cDict.put(stringCountryName, stringContinentName);
	}
	
	public Country getCountry(int indexNumber) { // This method takes in an index number from the array and returns the Country at that location
		if(indexNumber == -1) {
			System.out.println("Target country not in catalogue."+"\n");
			return null;
		}
		else {
		Country CountryFromIndex = catalogue[indexNumber];
		return CountryFromIndex;
		}
	}
	
	public void printCountryCatalogue() { // This method prints the entire catalogue to the screen
		System.out.println("Country Catalogue:");
		for (int i=0; i<(catalogue.length); i++) {
			if (catalogue[i]==null){
				break;
			}
			System.out.println(catalogue[i].toString());
		}
	}
	
	public void filterCountriesByContinent(String ContinentName) { // This method prints all countries that are in the specified continent 
		System.out.println("Countries in "+ ContinentName+":");
		for(int i=0; i<(catalogue.length);i++) {
			if (catalogue[i] == null) {
				break;
			}
			String country = catalogue[i].getCountry();
			if (cDict.get(country).equals(ContinentName)){
				System.out.println(catalogue[i].getCountry());
			}
		}
	}
	
	public int searchCatalogue(String Countryname) { //This method searches the catalogue for the specified country and returns the location of the object by it's index number
		int index = NOT_FOUND;
			for(int i=0;i<catalogue.length;i++) {
				
				if (catalogue[i] == null){
					break;
				}
				else if(catalogue[i].getCountry().equals(Countryname)){
					index = i;
				}
			}
			if (index!= NOT_FOUND) {
				return index;
			}
			else {
				return NOT_FOUND;
			}
			
	}
	
	public void removeCountry(String Countryname) { // This method removes a country from the catalogue and once removed, shifts each object in the array to avoid null locations between objects
		int indexNumber = searchCatalogue(Countryname);
		if (indexNumber != -1) {
			catalogue[indexNumber] = null;
			for (int j=indexNumber;j<catalogue.length-1;j++) {
				if (catalogue[j+1] == null){
					break;
				}
				else{
					catalogue[j] = catalogue[j+1];
				}
			}
			System.out.println("Country " +Countryname+ " removed successfully"+"\n");
		}
		else {
			System.out.println(Countryname+" was not removed");	
		}
	}

	public void setPopulationOfCountry(String Countryname, int newPopulation) { // This method changes the population of a given country
		if (searchCatalogue(Countryname) != -1) {
			getCountry(searchCatalogue(Countryname)).setPopulation(newPopulation);
			System.out.println(Countryname+" now has a population of "+newPopulation);
		}
		else {
			System.out.println(Countryname+"'s population was not changed");
		}
	}
	
	public void saveCountryCatalogue(String CountryFileName) { // This method saves the catalogue to a given filename using the format (country, continent, population, area)
		ThingToWriteFile countryDetails = new ThingToWriteFile(CountryFileName);
		for(int i=0; i<catalogue.length;i++) {
			if(catalogue[i]==null) {
				countryDetails.close();
				break;
			}
			else {
				catalogue[i].writeToFile(countryDetails);
			}
		}
	}
	
	public int findCountryWithLargestPop() { // This method returns the index location of the country with largest population that is in the catalogue
		int LargestPop = 0;
		String CountryName = "";
		for (int i=0;i<catalogue.length-1;i++) {
			if (catalogue[i] == null) {
				break;
			}
			if (catalogue[i].getPopulation() > LargestPop) {
				LargestPop = catalogue[i].getPopulation();
				CountryName = catalogue[i].getCountry();
				
			}
		}
		return searchCatalogue(CountryName);
	}
	
	public int findCountryWithSmallestArea() { // This method returns the index location of the country with the smallest area that is in the catalogue
		float SmallestArea = 999999999;
		String CountryName = "";
		for(int i=0; i<catalogue.length;i++) {
			if (catalogue[i] == null) {
				break;
			}
			if(catalogue[i].getArea()<SmallestArea) {
				SmallestArea = catalogue[i].getArea();
				CountryName = catalogue[i].getCountry();
			}
		}
		return searchCatalogue(CountryName);
	}
	
	public void printCountriesFilterDensity(int low, int high) { //This methods takes two numbers for a population density range, and prints out the countries that have a population density within that range 
		List <Country> CountriesWithinRange = new ArrayList<Country>();
		System.out.println("Countries with a population density between "+low+" and "+high+":");
		for(int i=0;i<catalogue.length;i++) {
			if(catalogue[i] == null) {
				break;
			}
			if(catalogue[i].getPopDensity()<=high && catalogue[i].getPopDensity()>=low) {
				CountriesWithinRange.add(catalogue[i]);
			}
		}
		if(CountriesWithinRange.size() == 0) {
			System.out.println("There are no countries that have a population density within this range");
		}
		else {
			for(int i=0;i<CountriesWithinRange.size();i++) {
				System.out.println(CountriesWithinRange.get(i).toString()+"has a population density of "+CountriesWithinRange.get(i).getPopDensity()+"\n");
			}
		}
	}
	
	public void findMostPopulousContinent() { //This method prints which continent has the greatest overall population within the catalogue 
		String largestContName = "";
		int largestCont = 0;
		for(int i=0;i<catalogue.length;i++) {
			if(catalogue[i] == null) {
				break;
			}
			
			String CurrentCont = catalogue[i].getContinent();
			int CurrentContPop = 0;
			

			for(i=0;i<catalogue.length;i++) {
				if(catalogue[i] == null) {
					break;
				}
				if(catalogue[i].getContinent().equals(CurrentCont))
					CurrentContPop = CurrentContPop + catalogue[i].getPopulation();
			}
			if(CurrentContPop >= largestCont) {
				largestCont = CurrentContPop;
				largestContName = CurrentCont;
			}
		}
		System.out.println("Continent with the largest population: "+largestContName+" with a population of "+largestCont);
	}	
}

