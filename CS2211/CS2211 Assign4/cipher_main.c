/* Tyler Roberts
*  	
*  This is the main function that is used to utilize the ciphers.h library. This program also contains a helper function that is used
*  to read in a string standard input from the user, where we do not know how long the string will be. The user is then able to encrypt
*  the string.
*/


#include <stdio.h>
#include <stdlib.h>
#include "ciphers.h"

char * input();  /* prototype */

int main(void){
	
        int check = 0;
        int choice;
	int k;

	char j;
        char *plainText;
        char *encryptText;
        char *decryptText;
        double letters[26];

        printf("Input plaintext: \n");
        plainText = input();
  

        printf("Available Ciphers:\n1) Caesar\n2) Vigenere\n\nSelect Cipher: ");
        check = scanf("%d", &choice);
        printf("\n");

        if(check != 1 || (choice != 1 && choice != 2)){
                printf("Error: Bad Selection\n");
                return EXIT_FAILURE;
        }

        if(choice == 1){
		
		int key;
	
                printf("Input key as number: ");
                check = scanf("%d", &key);
		printf("\n");

                if(check != 1){
                        printf("Error: bad key!");
                        return EXIT_FAILURE;
                }

                encryptText = caesar_encrypt(plainText, key);
                decryptText = caesar_decrypt(encryptText, key);
        }
	
        else{
		char *key;
                int i;

                printf("Input key as string: ");
                key = input();

                for(i = 0; *(key + i) != 0; i++){
                        if(!isalpha(*(key + i))){
                                printf("Error: bad key, invalid char!\n");
                                return EXIT_FAILURE;
                        }
                }

                encryptText = vigen_encrypt(plainText, key);
                decryptText = vigen_decrypt(encryptText, key);
                free(key);
        }

        printf("\nPlaintext: \n%s\n\n\nCiphertext: \n%s\n\n\nDecrypted plaintext:\n%s\n\n\n", plainText, encryptText, decryptText);
        
	freq_analysis(encryptText, letters);
	
	printf("Frequency analysis: \n");

	for(j = 'A'; j < ('Z' + 1); j++){
		printf("%c\t", j);
	}
	printf("\n");
	
	for(k = 0; k < 26; k++){
		printf("%.1lf\t", letters[k]);
	} 
	printf("\n");	

        free(plainText);
        free(encryptText);
        free(decryptText);

        return 0;
}

char * input(){
        int size = 1;
        char *input = (char *) malloc(size); /*creates space in memory for input starting at only one byte, as the input can be expanded */
        char letter; 
        int i;

        /*makes sure that the memory was allocated correctly, if not then an error is shown and the program exits */ 
        if(input == NULL){
                printf("Error allocating memory\n");
                exit(EXIT_FAILURE);
        }

        //We need to clear the buffer so that there are no excess charachters (such as linespaces etc.) *\
        scanf(" %c", &letter);

        for(i = 0; letter != '\n'; i++){
                size++;
                input = (char *) realloc(input, size); /* makes sure the memory contains 1 extra space for the null terminator */

                /* everytime we increase the memory size and allocate new memory, we make sure that it occured successfully */
                if(input == NULL){
                        printf("Error reallocating memory\n");
                        exit(EXIT_FAILURE);
                }

                *(input + i) = letter; /* using the counter variable i, we do not directly increment the string pointer thus we do not have to worry about decrementing it*/
                scanf("%c", &letter);
        }

        *(input + i) = 0; /* add a null terminator to the string */

        return input;
}
