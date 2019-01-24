
#!/bin/bash

#Tyler Roberts
#This script reads in a directory, determines whether its real or not, and then saves it in the current working directory

#This reads in the directory name and then saves it as variable dirpath
echo -n "Input a directory name: "
read dirpath

#Conditional statement that determines if the inputted directory name is valid or not
if [ -d "$dirpath" ]
then
        echo "Valid directory name." #If it is valid it will return "Valid Directory Name"
else
        echo "Invalid directory name." #If it is invalid then it will return "Invalid Directory Name" and exit the script
        exit 1
fi

if [ -d "backup" ] #Checks to see if the directory "backup" exists in the current working directory
then
        rm -r backup #If it does, then it will remove it and all of it's contents
fi

cp -r $dirpath backup #This will copy the inputted directory and all of it's contents into the current working directory as "backup"

tar -cf backup.tar backup #This will create a archive file of the directory "backup" into the current working directory

cp -r backup.tar "backup-`date +%d`-`date +%m`-`date +%Y`.tar" #This will rename the just-created archive file with the format "backup-day-month-year.tar"

#This will send the archive "backup.tar" to the users "mybackups" directory in the user current working directory over the course server
scp -r  ~/backup.tar $USER@cs2211b.gaul.csd.uwo.ca:~/mybackups
