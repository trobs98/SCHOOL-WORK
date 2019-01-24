#!/bin/bash
#Tyler Roberts
#This bash script will take in an option and a filename, and returns output depending on the option used

#This checks to see if there are zero arguments
if [ $# -eq 0 ]; then
        echo "Usage: nums option filename"
        exit 1
elif [ $# -eq 1 ]; then # This checks to see if there is one argument and will return a message depending on the argument
        if [ $1 -eq 0 ] || [ $1 -eq 1 ] || [ $1 -eq 2 ] || [ $1 -eq 3 ]; then This checks to make see if the first argument is 0,1,2 or 3
                echo "Error: no filename given"
                exit 2
        else
                echo "Invalid option!"
                exit 3
        fi
elif [ $# -eq 2 ]; then #This checks to see if there is two arguments
        if [ ! -e $2 ]; then #This checks to see that the file given as the second argument does not exist
                echo "$2 does not exist!"
        else #This occurs if the file does exists
                if [ $1 -eq 0 ]; then #Checks to see if the first argument is equal to 0
                        cat $2 | wc -l #If the option is 0 then it counts the number of lines in the file given as the second argument
                        exit 4
                elif [ $1 -eq 1 ]; then #Checks to see if the first argument is equal to 1
                        tr a-z A-z < $2 | tr -s 0-9 '*' #If it is then replace all lowercase letters with upper case letters and all numbers with asterixs f$
                        exit 4
                elif [ $1 -eq 2 ]; then #Checks to see if the first argument is equal to 2
                        grep -Eix '^([a-zA-Z])([a-zA-Z])([a-zA-Z])[a-zA-Z]*\3\2\1' $2 # If it is then return all 7 letter palindromes from file given as sec$
                        exit 4
                elif [ $1 -eq 3 ]; then #Checks to see if the first argument is equal to 3
                        cat $2 | tr -sc [:digit:] "\n" | sort -nr | head -4 #If it is then return the 4 largest numbers in the file given as the second argu$
                        exit 4
                else
                        echo "Invalid Option!" #if the first argument is anything other then 0,1,2 or 3 then return this message
                        exit 3
                fi
        fi
else
	echo "Invalid Option!" # If the number of arguments is anything other then 0,1 or 2 then return this message
        exit 3
fi

