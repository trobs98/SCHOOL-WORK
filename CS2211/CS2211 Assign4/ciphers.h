/* Tyler Roberts
*  This is the library that contains all of the functions made in cyphers.c
*/
#include <stdio.h>

char * caesar_encrypt(char *plaintext, int key);

char * caesar_decrypt(char *ciphertext, int key);

char * vigen_encyrpt(char *plaintext, char *key);

char * vigen_decrypt(char *plaintext, char *key);

void freq_analysis(char *ciphertext, double letters[26]);
