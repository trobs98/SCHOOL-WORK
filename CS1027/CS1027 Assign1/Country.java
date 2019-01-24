/* Name: Tyler Roberts
 * 
 * This class creates a Country which contains the name of the country, the country's continent, the country's population
 * and the country's area. There are also several methods created in order to access or manipulate the Country.
 */
public class Country {
	private String countryName;
	private String continentName;
	private int population;
	private float area;
	
	Country(String country, int pop, float areaTotal, String continent){ // Constructor which creates the object Country
		this.countryName = country;
		this.continentName = continent;
		this.population = pop;
		this.area = areaTotal;
	}
	
	public String getCountry(){ // Returns the name of the country
		return countryName;
	}
	public String getContinent() { // Returns the country's continent
		return continentName;
	}
	public int getPopulation() { // Returns the population of the country
		return population;
	}
	public float getArea() { // Returns the area of the country
		return area;
	}
	public float getPopDensity() { // Returns the population density of the country (population divided by area)
		float popdensity = (float)population / (float)area;
		return popdensity;
	}
	public void setPopulation(int newPopulation) { // Sets the population of the country to a new population
		this.population = newPopulation;
	}
	public void writeToFile(ThingToWriteFile countryDetails){ // Writes the information of the country to a file
		countryDetails.writeLine(countryName+","+continentName+","+population+","+getPopDensity()+"\n");
	}
	public void printCountryDetails() { // Prints the information of country 
		System.out.println(countryName+" is located in "+continentName+", has a population of "+population+", an area of "+area+" and has a population density of "+this.getPopDensity()+"\n");
	}
	public String toString() { // Returns a string that represents the country
		return (countryName+" in "+continentName+"\n"); 
	}

}




	
