<html>
<body>

<!-- connect to the database -->
<?php
include "connectToDB.php";
?>

<?php
//all the variables that the user entered
$customerID = $_POST["cusID"];
$firstname = $_POST["firstname"];
$lastname = $_POST["lastname"];
$city = $_POST["city"];
$phonenumber = $_POST["phoneNumber"];

//makes sure none of the text boxes were empty
if(empty($customerID) OR empty($firstname) OR empty($lastname) OR empty($city) OR empty($phonenumber)){
	echo "EMPTY TEXTBOX: Please return to the previous page and fill ALL the text boxes"; 		
}

else{
	//create a query to make sure the customer ID the user gave is valid
	$query = "SELECT cusID FROM customer WHERE customer.cusID='$customerID'";
	$result = mysqli_query($connection, $query);
	if(!result) {
		die("database query failed.");
	}
	
	if(mysqli_num_rows ($result) != 0){
		die("CUSTOMERID ALREADY EXISTS: Please return to the previous screen and create a new customer"); 
	}
	//if the ID is valid then insert the customer into the database and leave a message
	else{
		$query1 = "INSERT INTO customer(cusID,firstname,lastname,city,phonenumber,agentID) VALUES ($customerID,'$firstname','$lastname','$city','$phonenumber',NULL)";  
		if(!mysqli_query($connection, $query1)){
			die("Error: insert failed" . mysqli_error($connection));
		}
		echo "The customer was added!";
	} 
	mysqli_free_result($result);
}

//disconnect from the database
mysqli_close($connection);
?>

<br><br>
<!-- create a link to return to the previous page -->
<a href="PHPfile1.php">RETURN TO PREVIOUS PAGE</a> 

</body>
</html>
