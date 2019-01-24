<html>
<body>

<!-- connect to the database -->
<?php
include "connectToDB.php";
?>

<?php
//allows us to use the variables in previous screens
session_start();
$customerID = $_POST["customerID"];
$_SESSION["customerID"] = $customerID;

//makes sure the text boxes aren't empty
if(empty($customerID)){
	echo("EMPTY TEXTBOX: Please return to the previous page and fill the textbox");	
}
else{
	//create a query to make sure the customer ID exists
	$query = "SELECT cusID,phonenumber FROM customer WHERE customer.cusID='$customerID'";
	$result = mysqli_query($connection,$query);
	if(!$result){
		die("database query failed.");
	}
	if(mysqli_num_rows($result) == 0){
		die("CUSTOMERID DOES NOT EXIST: Please return to the previous page and enter a valid customer ID");
	}
	//if it does exist
	else{
		//create another text button that the user will enter the new phone number
		while($row = mysqli_fetch_assoc($result)){
			echo "Customer's Current Phone Number: " . $row["phonenumber"];
		}
		echo "<br><br>";
		echo "<form action='updatePhoneNow.php' method='post'> New Phone Number (use format ###-####): <br> <input type='text' name='phonenumber' maxlength='8'> <br><br> <input type='submit' value='Update Phone Number'> </form>";
	}

	mysqli_free_result($result);
}
//disconnect from the database
mysqli_close($connection);
?>

<br><br>
<!-- Create a link to return to the previous page -->
<a href="PHPfile1.php">RETURN TO PREVIOUS PAGE</a>

</body>
</html>
