#!/bin/bash

echo
hour=`date +%H`

if [ "$hour" -lt 12 ]
then
        echo "GOOD MORNING"
else
        if [ "$hour" -lt 18 ]
        then
                echo "GOOD AFTERNOON"
        else
                echo "GOOD EVENING"
        fi
fi

echo


