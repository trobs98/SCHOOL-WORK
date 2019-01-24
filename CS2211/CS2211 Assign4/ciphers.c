/* Tyler Roberts
*  This program contains all the functions that are stored in the cyphers.h library
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "ciphers.h"

char * caesar_encrypt(char *plaintext, int key){
	int i = 0,j;
	char letter;

	char *text = (char *) calloc((strlen(plaintext) + 1), sizeof(char));

	if(text == NULL){
		printf("Error allocating memory!");
		exit(EXIT_FAILURE);
	}
	
	while(*plaintext != 0){
		letter = *plaintext;
		
		if(isalpha(letter)){
			letter = toupper(letter);
			
			if(key > 0){
				for(j = (key%26); j > 0; j--){
					letter++;
						
					if(letter > 'Z'){
						letter = 'A';
					}
		
				} 
			}
			else{
				for(j = (key%26); j < 0; j++){
					letter--;
					
					if(letter < 'A'){
						letter = 'Z';
					}
				}
			}
		}
		
		text[i] = letter;
		i++;
		plaintext++;
	}
	
	text[i] = 0;
	return text;
}

char * caesar_decrypt(char *ciphertext, int key){
	return (caesar_encrypt(ciphertext, (0-key)));
}

char * vigen_encrypt(char *plaintext, char *key){
	int i, j;
	char charachter;
	int textLength = strlen(plaintext);
	int keyLength = strlen(key); 
	char paddedKey[textLength + 1];
	char upperKey[keyLength + 1];
	char *text = (char *) calloc((textLength + 1), sizeof(char)); 

	if(text == NULL){
		printf("Error allocating memory");
		exit(EXIT_FAILURE);
	}

	for(i = 0; i < keyLength; i++){
		upperKey[i] = toupper(*key);
		key++;	
	}

	upperKey[i] = 0;
	
	if(textLength > keyLength){
		int copyCount = ((textLength / keyLength) + 1);
		int pos = 0;

		for(i = 0; i < copyCount; i++){
			strcpy(paddedKey + pos, upperKey); 	
			pos = pos + keyLength;
		}
	}
	else{
		for(i = 0; i < textLength; i++){
			paddedKey[i] = upperKey[i];
		}
	}
	
	for(i = 0; i < textLength; i++){
		charachter = paddedKey[i];
		char letter = *plaintext;
		
		if(isalpha(letter)){
			letter = toupper(letter);
			charachter = charachter - 65;
			
			for(j = 0; j < charachter; j++){
				letter++;
				if(letter > 'Z'){
					letter = 'A';
				}
			}
		}
		
		text[i] = letter;
		plaintext++;
	}
	
	text[i] = 0;

	return text; 
}

char * vigen_decrypt(char *ciphertext, char *key){
	int i, j;
	int keyLength = strlen(key);	
	char *newKey = (char *) calloc((keyLength + 1), sizeof(char));
	char currentLetter;
	
	if(newKey == NULL){
		printf("Error allocating memory");
		exit(EXIT_FAILURE);
	}

	for(i = 0; i < keyLength; i++){
		newKey[i] = toupper(*key);
		key++;
	}

	newKey[i] = 0;
	printf("%s\n", newKey);

	key = key - keyLength;
	
	for(i = 0; i < keyLength; i++){
		currentLetter = newKey[i];
		currentLetter = ((26 - (currentLetter - 65) % 26) + 65);
		newKey[i] = currentLetter; 
	}

	return vigen_encrypt(ciphertext, newKey);
		
}

void freq_analysis(char *ciphertext, double letters[26]){
	
	char currentUpper = 'A';
	char currentLower = 'a';
	int textLength = strlen(ciphertext);
	int i, j;
	double *pointer = letters;


	for(i = 0; i < 26; i++){
		double total = 0.0;
		
		for(j = 0; j < textLength; j++){
			if(*ciphertext == currentUpper || *ciphertext == currentLower){
				total = total + 1.0;
			}

			ciphertext++;
		}
		
		total = ((total / (double) textLength)* 100);
	
		ciphertext = ciphertext - textLength;
		*pointer = total;
		pointer++;
		currentLower++;
		currentUpper++;
	}
	 
}
