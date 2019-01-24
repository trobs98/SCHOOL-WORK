/*
I am using std c89. 
Tyler Roberts
This program taks a phone number and an option from standard input and displays the phone number in a specific format given the option
*/ 
#include <stdio.h>

int main(){
	
	/* all the variables that are used to store the information*/
	
	int firstThree;
	int secondThree;
	int lastFour;
	char option;	
	int check;
	
	/*This takes in the standard input phone number and saves it to the variables as the 1st three digits, the 2nd three digits 
	and the last four digits
	*/

	printf("Input Phone Number: ");
	check = scanf("%3d%3d%d", &firstThree, &secondThree, &lastFour);
	
	/*This makes sure that phone number is all digits, isnt larger than 10 digits and isnt negative*/ 
	
	if(check == 3 && firstThree >= 0 && firstThree <= 999 && secondThree >= 0 && secondThree <= 999 && lastFour >= 0 && lastFour <= 9999){

		/*prints the format so the user will know*/
		printf("\nFormat Options:\n");
		printf("A) Local\n");
		printf("B) Domestsic\n");
		printf("C) International\n");
		printf("D) Odd\n");

		/*This takes in the standard input option and saves it in it's designated variable*/
		printf("\nInput Option: ");
		check = scanf(" %c", &option);
		getchar();
		
		/*This makes sure that only input is given as the option*/
		if(check == 1){
			
			/*This checks to make sure the option isn't anything other then a,b,c,d,A,B,C or D.
			If it is then it will throw an error and exit the program	
			*/
 
			switch(option){
				case 'A':
				case 'a': printf("\nPhone Number: %d-%d\n", secondThree, lastFour);
					break;
				case 'B':
				case 'b': printf("\nPhone Number: (%d) %d-%d\n", firstThree, secondThree, lastFour);
					break;
				case 'C':
				case 'c': printf("\nPhone Number: +1-%d-%d-%d\n", firstThree, secondThree, lastFour);
					break;
				case 'D':
				case 'd': printf("\nPhone Number: %6.4d%6.4d%6.4d\n", firstThree, secondThree, lastFour);
					break;
				default: {
					printf("\nERROR: BAD OPTION\n");
					return 1; 
				}
			}
		}
	}
	/*This occurs if the phone number is not inputted correctly and will throw an error and exit the program*/ 
	else{
		printf("ERROR: BAD PHONE NUMBER\n");
		return 1;
	}
	/*This is returned if the program ran successfully*/
	return 0;
}
