<?php
//all the information to connect to the database
$dbhost = "localhost";
$dbuser= "root";
$dbpass = "*********";
$dbname = "assign2db";

//This is what actually connects to the database
$connection = mysqli_connect($dbhost, $dbuser,$dbpass,$dbname);
if (mysqli_connect_errno()) {
     die("database connection failed :" .
     mysqli_connect_error() .
     "(" . mysqli_connect_errno() . ")"
         );
    }
?>
