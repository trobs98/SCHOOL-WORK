<html>
<body>

<!-- connect to the database -->
<?php
include "connectToDB.php";
?>

<?php
//allows us to use the variables in the previous screens
session_start();
$phoneNumber = $_POST["phonenumber"];
$customerID = $_SESSION["customerID"];

//make sure the textbox is filled
if(empty($phoneNumber)){
	echo "EMPTY TEXTBOX: Please return to the previous page and fill the textbox";
}
else{
	//query will update the customer's phone number
	$query = "UPDATE customer SET phonenumber='$phoneNumber' WHERE customer.cusID='$customerID'";	
	if(!mysqli_query($connection, $query)){
		die("Error: update failed" . mysqli_error($connection));
	}
	echo "The customer's phone number was updated!";
}
//disconnect from the database
mysqli_close($connection);

?>


<br><br>
<!-- create a link to return to the main page -->
<a href="PHPfile1.php">RETURN TO MAIN PAGE</a>


</body>
</html>


