/*Tyler Roberts
This program takes a date with format mm/dd/yy as standard inputand returns the largest date once 0/0/0 is inputted
*/
#include <stdio.h>

int main(){
	
	/*keeps track of the current date and the latest date*/
	int latestYear = 0, latestMonth = 0, latestDay = 0;
	int currentYear, currentMonth, currentDay;
	
	/*ask the user for input so we can enter the while loop*/
	printf("Input a date with format mm/dd/yy: \n");
	scanf("%d/%d/%d", &currentMonth, &currentDay, &currentYear);
	
	/* infinite loop */
	while(1){
		
		/*checks to see if the user wants to quit */
		if(currentYear == 0 && currentMonth == 0 && currentDay == 0){
			if(latestYear == 0 && latestMonth == 0 && latestDay == 0){
				printf("Error: At least one date must be input/n");
			}
			else{
				printf("The latest date given is: %.2d/%.2d/%.2d\n", latestMonth, latestDay, latestYear);
			}
			break;	
		}
	
		/*checks to make sure the input is valid */
		else if((currentYear < 00 || currentYear > 99) || (currentMonth < 1 || currentMonth > 12) || (currentDay < 1 || currentDay > 31)){
			if(currentYear < 00 || currentYear > 99){
				printf("INVALID YEAR NUMBER\n");
			}
			else if(currentMonth < 1 || currentMonth > 12){
				printf("INVALID MONTH NUMBER\n");
			}
			else{
				printf("INVALID DATE NUMBER\n");
			}

			while(getchar() != '\n' && getchar() != EOF);
		}
		
		else{
 			
			/* compares the current year with the largest year */
			if(latestYear < currentYear){
				latestYear = currentYear;
				latestDay = currentDay;
				latestMonth = currentMonth;
				 
			}
			else if(latestYear == currentYear){

				/*compares the current month with the largest month */
				if(latestMonth < currentMonth){
					latestYear = currentYear;
					latestDay = currentDay;
					latestMonth = currentMonth;
		
				}
				else if(latestMonth == currentMonth){

					/*compares the current day with the largest day*/
					if(latestDay < currentDay){
						latestYear = currentYear;
						latestMonth = currentMonth;
						latestDay = currentDay;
					}

				}			
			}
		}
		
		/* Have the user enter another date */
		printf("Input a date with format mm/dd/yy: \n");
		scanf("%d/%d/%d", &currentMonth, &currentDay, &currentYear);
	}
}
