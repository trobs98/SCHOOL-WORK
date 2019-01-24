/*
Tyler Roberts
 
This program takes in a dollar amount and determines the smallest number of $20-$1 necessary to pay that amount
*/
#include <stdio.h>
#include <math.h>

/* prototype */
void pay_amount(int dollars, int *twenties, int *tens, int *fives, int *toonies, int *loonies);


void pay_amount(int dollars, int *twenties, int *tens, int *fives, int *toonies, int *loonies){
	
	*twenties = dollars / 20;
	dollars %= 20;

	*tens = dollars / 10;
	dollars %= 10;

	*fives = dollars / 5;
	dollars %= 5;
		
	*toonies = dollars / 2;
	dollars %= 2;

	*loonies = dollars;

}

int main(){
	
	/* check makes sure that the input is only a number and not any other charachters */
	int dollars, twenties, tens, fives, toonies, loonies, check;

	/* gets the user input */
	printf("Enter the amount in dollars: ");
	check = scanf("%d", &dollars);
	
	if(dollars >= 0 && check == 1){
		pay_amount(dollars, &twenties, &tens, &fives, &toonies, &loonies);
		
		printf("The smallest $1-$20 to pay the dollar amount %d is: \n", dollars);
		printf("twenties = %d\t tens = %d\t fives = %d\t toonies = %d\t loonies = %d\n", twenties, tens, fives, toonies, loonies);
		return 0;
	}

	/* prints an error message and quits when the user input is either a negative number or a charachter other than digits */
	else{
		printf("THIS INPUT IS INVALID\n");
		return 1;
	}
}
 
