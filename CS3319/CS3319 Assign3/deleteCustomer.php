<html>
<body>
<!-- Connect to the database -->
<?php
include "connectToDB.php";
?>

<?php
$customerID = $_POST["customerID"];

//makes sure the text box isnt empty
if(empty($customerID)){
	echo "EMPTY TEXTBOX: Please return to the previous page and fill the text box";
}
else{
	//create a query that makes sure the customer ID exists
	$query = "SELECT cusID FROM customer WHERE customer.cusID='$customerID'";
	$result = mysqli_query($connection,$query);
	if (!$result) {
     		die("databases query failed.");
	}
	if(mysqli_num_rows($result) == 0){
		die("CUSTOMERID DOES NOT EXIST: Please return to the previous page and enter a valid customer ID");
	}
	//if it doesnt exist it will delete the customer and return a message
	else{
		$query1 = "DELETE FROM customer WHERE customer.cusID='$customerID'";
		if(!mysqli_query($connection,$query1)){
			die("Error deleting record: " . mysqli_error($connection));
		}
		echo "Customer has been deleted!";
	}
	mysqli_free_result($result);
}

//disconnect from the database
mysqli_close($connection);
?>

<br><br>
<!-- create a link to return to the main page -->
<a href="PHPfile1.php">RETURN TO MAIN PAGE</a>


</body>
</html>
