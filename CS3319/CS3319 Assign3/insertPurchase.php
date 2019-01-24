<html>
<body>

<!-- Connect to the database -->
<?php
include "connectToDB.php";
?>

<?php
//variables the user gave
$customerID = $_POST["customerID"];
$productID = $_POST["productID"];
$quantity = $_POST["quantity"];

//make sure all the text boxes were filled
if(empty($customerID) OR empty($productID) OR empty($quantity)){
	echo "EMPTY TEXTBOX: Please return to the previous page and fill ALL the text boxes";
}

else{
	//create a query to make sure the customer ID exists
	$query = "SELECT cusID FROM customer WHERE customer.cusID='$customerID'";
	$result = mysqli_query($connection,$query);
	if (!$result) {
		die("databases query failed.");
	}

	if(mysqli_num_rows($result) == 0){
		die("CUSTOMERID DOES NOT EXIST: Please return to the previous page and enter a valid customer ID");
	}
	mysqli_free_result($result);

	//create a query to make sure the product ID exists
	$query = "SELECT prodID FROM product WHERE product.prodID='$productID'";
	$result = mysqli_query($connection,$query);
	if (!$result) {
    		die("databases query failed.");
	}
	if(mysqli_num_rows($result) == 0){
		die("PRODUCTID DOES NOT EXIST: Please return to the previous page and enter a valid product ID"); 
	}
	mysqli_free_result($result);
	
	//make a query to see if the customer had already purchases the product given
	$query = "SELECT * FROM purchases WHERE purchases.cusID='$customerID' AND purchases.prodID='$productID'";
	$result = mysqli_query($connection,$query);
	if (!$result) {
    		die("databases query failed.");
	}
	//if the customer HAS already purchased the product then just add to the quantity and leave a message
	if(mysqli_num_rows($result) > 0){
		$query1 = "UPDATE purchases SET Quantity=Quantity+$quantity WHERE purchases.cusID='$customerID' AND purchases.prodID='$productID'";
		if(!mysqli_query($connection, $query1)){
			die("Error: insert failed" . mysqli_error($connection));
		}
		echo "The purchases was added!";
	}
	//if the user HAS NOT then create a new purchase in the purchases table and leave a message
	else{
		$query2 = "INSERT INTO purchases(cusID,prodID,Quantity) VALUES ($customerID,$productID,$quantity)";
		if(!mysqli_query($connection, $query2)){
			die("Error: insert failed" . mysqli_error($connection));
		}
		echo "The purchase was added!";
	}

	mysqli_free_result($result);
}

//disconnect from the database
mysqli_close($connection);
?>

<!-- create a link to return to the previous page -->
<br><br>
<a href="PHPfile1.php">RETURN TO PREVIOUS PAGE</a>

</body>
</html>
