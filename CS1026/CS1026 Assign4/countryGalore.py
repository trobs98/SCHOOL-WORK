###
#Made By: Tyler Roberts
#This program uses class 'Country' in another class called 'CountryCatalogue'
# where many methods can be used to manipulate a catalogue of Countries that was created
###

#This is the first class that was created
class Country:
    #This is the constructor where the name, population, area, and the continent of a Country is passed through as parameters
    def __init__(self, name, pop, area, continent):
        self._name = name
        self._population = int(pop)
        self._area = float(area)
        self._continent = continent
    #This returns the name of a Country
    def getName(self):
        return self._name
    #This returns the area of a Country
    def getArea(self):
        return self._area
    #This returns the population of a Country
    def getPopulation(self):
        return self._population
    #This returns the Continent of which a Country resides in
    def getContinent(self):
        return self._continent
    #This returns the population density of a Country to two decimal places
    def getPopDensity(self):
        PopDensity = round((float(self._population)/self._area),2)
        return PopDensity
    #This is used as a manipulator in order to change the population of a country
    def setPopulation(self,pop):
        self._population = pop
    #This function is used to generate a string about a country
    def __repr__(self):
        return (self._name + ' is in ' + self._continent + ' with a population density of ' + str(self.getPopDensity()))


#This is the second class that was created
class CountryCatalogue:
    _cDictionary = {}
    _catalogue = {}

    #This is the constructor where the instance variable is the file that contains the data of all the Countries
    #All the data is then formatted and placed into a dictionary (a.k.a. cDictionary) with the key as the Country name and the value as the Country Continent
    #The data is also placed into another dictionary (a.k.a. the catalogue) with the key as the Country name and the value as an object of the class Country
    def __init__(self, filename):
        continentfile = open("continent.txt", 'r')
        updatedfile = continentfile.readlines()[1:] #reads after the first line because that is used for formatting
        for line in updatedfile:
            newline = line.split(",")
            self._cDictionary[newline[0]] = newline[1].strip('\n')
        self.Filename = filename
        textfile = open(self.Filename, 'r')
        newtextfile = textfile.readlines()[1:] #reads after the first line because that is used for formatting
        for line in newtextfile:
            data = line.split("|")
            the_country = data[0]
            the_population = data[1].replace(',','')
            the_area = data[2].replace(',', '').strip('\n')
            self._catalogue[the_country] = Country(the_country, the_population, the_area, self._cDictionary[the_country])

    #Allows a country to be added to the cDictionary and the Catalogue
    def addCountry(self):
        usercountry = input("Enter the Country's name you would like to add: ")
        userpopulation = int(input("Enter the Country's population: "))
        userarea = float(input("Enter the Country's area: "))
        usercontinent = input("Enter the Country's Continent: ")
        if usercountry in self._catalogue:
            print("This Country already exists, please enter one that does not exist")
            self.addCountry()
        else:
            self._cDictionary[usercountry] = usercontinent
            self._catalogue[usercountry] = Country(usercountry, userpopulation, userarea, self._cDictionary[usercountry])
            print(usercountry, "has been added")

    #Allows a country to be deleted ONLY from the Catalogue
    def deleteCountry(self):
        countrytobedeleted = input("Which Country would you like to delete: ")
        if countrytobedeleted not in self._catalogue:
            print("That Country does not exist")
        else:
            self._catalogue.pop(countrytobedeleted)
            print(countrytobedeleted, "has been removed")

    #Allows user to inquire information about a country where they are given the country's name, population, area and Continent
    def findCountry(self):
        countrytobefound = input("Which Country would you like to inquire about?: ")
        if countrytobefound in self._catalogue:
            print(('Country: ', countrytobefound,
                   'Continent: ', self._catalogue[countrytobefound].getContinent(),
                   'Population: ', self._catalogue[countrytobefound].getPopulation(),
                   'Area: ', self._catalogue[countrytobefound].getArea()))
        else:
            print(countrytobefound, 'does not exist')

    #Allows user to view all of the Countries in a given Continent
    def filterCountriesByContinent(self):
        continentwanted = input("Which Continent would you like to see all of it's Countries: ")
        for countries in self._cDictionary:
            if self._cDictionary[countries] == continentwanted:
                print(countries)

    #This prints all of the data in the catalogue as a string to the screen using the __repr__ function
    def printCountryCatalogue(self):
        for countries in self._catalogue:
            print(self._catalogue[countries].__repr__())

    #Allows user to change the population of a country that is in the Catalogue
    def setPopulationOfASelectedCountry(self):
        poptochange = input("Which Country's population would you like to change?: ")
        newpop = int(input("What is it's new population?: "))
        if poptochange in self._catalogue:
            self._catalogue[poptochange].setPopulation(newpop)
            print(poptochange,"'s new population density is", self._catalogue[poptochange].getPopDensity())
        else:
            print("The Country you chose does not exist")

    #This prints the Country who's population is the largest within the Catalogue
    def findCountryWithLargestPop(self):
        largestpop = 0
        largestpopcountry = ''
        for countries in self._catalogue:
            if largestpop < int(self._catalogue[countries].getPopulation()):
                largestpop = int(self._catalogue[countries].getPopulation())
                largestpopcountry = countries
        print("The Country with the largest population is", largestpopcountry, "with a population of",largestpop)

    #This prints the Country who's area is the smallest within the Catalogue
    def findCountryWithSmallestArea(self):
        smallestarea = 999999999999999999999999999.9999
        smallestareacountry = ''
        for countries in self._catalogue:
            if smallestarea > float(self._catalogue[countries].getArea()):
                smallestarea = float(self._catalogue[countries].getArea())
                smallestareacountry = countries
        print("The Country with the smallest area is", smallestareacountry, 'with an area of', smallestarea)

    #This allows the user to see all of the Countries who's population densities fall between the range that the user gives
    def filterCountriesByPopDensity(self):
        smallestrange = float(input("Enter the minimum population density you are looking for: "))
        largestrange = float(input("Enter the maximum population density you are looking for: "))
        for countries in self._catalogue:
            if smallestrange <= self._catalogue[countries].getPopDensity() <= largestrange:
                print(countries)

    #This prints the Continent who has the largest total population within the catalogue
    def findMostPopulousContinent(self):
        Continents = {}
        for country in self._catalogue:
            Continents[self._catalogue[country].getContinent()] = 0
        for countries in self._catalogue:
            Continents[self._catalogue[countries].getContinent()] += int(self._catalogue[countries].getPopulation())
        largestcont = ''
        largestpop = 0
        for totalpops in Continents:
            if int(Continents[totalpops]) > largestpop:
                largestpop = int(Continents[totalpops])
                largestcont = totalpops
        print("The Continent with the largest population is", largestcont, "with a population of", largestpop)

    #This saves the catalogue to a seperate file using the format 'Name|Continent|Population|PopulationDensity'
    def saveCountryCatalogue(self, filename):
        file = open(filename, 'w')
        sorted_catalogue = sorted(self._catalogue)
        file.write("Name|Continent|Population|PopulationDensity"+'\n')#This writes the first line of the new file to show how the data is formatted
        for data in sorted_catalogue:
            file.write(data+"|"+self._catalogue[data].getContinent()+'|'+str(self._catalogue[data].getPopulation())+'|'+str(self._catalogue[data].getPopDensity())+'\n')
        file.close()
        print("The catalogue was successfully saved to", filename)
