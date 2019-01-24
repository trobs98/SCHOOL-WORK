/*need to use -lm option in order for the sqrt function to work.
Tyler Roberts
This program estimates the number pi
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

/*prototype*/
double estimate_pi (long long n);

/*method to estimate pi*/
double estimate_pi (long long n){
	
	/* i represents the count for the loop and inside represents the number of times a coordinate is inside the shaded area the circle of radius 1*/
	
	int i = 0;
	int inside = 0;
	
	/*allows us to get new random numbers each iteration of the loop*/
	srand((unsigned)time(NULL));

	for(i; i<n; i++){
		
		long double check;
		long double randX = (long double)rand()/(long double)(RAND_MAX);
		long double randY = (long double)rand()/(long double)(RAND_MAX);

		check = (randX*randX) + (randY*randY);		

		if(check <= 1){
			inside = inside + 1;
		}	
	}
	
	/*ratio represents the ratio of coordinates found inside the circle v.s. the total number of coordinates found*/
	
	long double ratio =(long double)inside/(long double)n;
	double pi =(double)ratio*4.0;
	return pi;
}
	
int main(){
	
	/* i represents the loop counter, n will be the standard input for the estimate_pi method, meanTotal is all pi added up, and stdDevTotal
	is all the pi*pi added up*/

	int i = 0;
	int n;
	double currentPi;
	double mean;
	double stdDev;
	double meanTotal = 0.0;
	double stdDevTotal = 0.0;
	
	/*asks the user for a number to use for the estimate_pi method*/

	printf("Enter a number: \n");
	scanf("%d", &n);	
	
	/*if the number the user gives is less than or equal to zero it will throw an error and quit the program*/  	
	if(n <= 0){
		printf("ERROR: NUMBER IS LESS THAN ZERO\n");
		return 1;	
	}
	/* if the number is greater than zero it will branch here (even if the number is a fraction is will only take the decimal part)*/
	else{
		
		srand((unsigned)time(NULL));
		
		/*estimates pi ten times and adds the totals to meanTotal and stdDevTotal as described above*/
		for(i; i<10; i++){
			currentPi = estimate_pi(n);
			printf("%.10f\n", currentPi);
			meanTotal = meanTotal + currentPi;
			stdDevTotal = stdDevTotal + (currentPi*currentPi);
		}
		
		/*calculates the mean, the standard deviation before square rooting, the standard deviation after square rooting and then prints
		to the screen*/
		mean = meanTotal/10.0;
		double stdDevBefore = (stdDevTotal/10.0) - (mean*mean);
		stdDev = sqrt(stdDevBefore);
		printf("Mean: %.10f\n", mean);
		printf("Standard Deviation: %.10f\n", stdDev);
		
		/* if successfully run then it will return 0*/
		return 0;
	}
} 
