
#!/bin/bash

#Tyler Roberts
#This bash script will read in integer inputs from the command line and then take the average

total=0 #This is the total of all integer arguments added together
argsTotal="$#" #This is the total number of arguments
if [ $argsTotal -le 0 ] #This checks to make that they number of arguments is not zero
then
        echo "NEED AT LEAST ONE ARGUMENT!"
        exit 1 #exits the program if there are zero arguments

else #Happens if there are more then zero arguments
        for i in `seq 1 $argsTotal` #Loops through '1-the total number of arguments' times
        do
                ((total=total+$1)) #Adds the first argument to the total
                shift #shifts the arguments so that arg 2 is now arg 1 and so on and does this the same amount of times the loop iterates
        done
fi

average=`echo "scale=2; $total/$argsTotal" | bc` #calculates the average to two decimal placecs
echo $average #outputs the average to the screen

