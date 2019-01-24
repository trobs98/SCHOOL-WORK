<html>
<body>

<!-- Connect to the database -->
<?php
include "connectToDB.php";
?>

<?php
//variable the user gave
$quantity = $_POST["quantity"];

//make sure the text box is not empty
if(empty($quantity)){
	echo "EMPTY TEXTBOX: Please return to the previous page and fill text box";
}

else{
	//create a query that returns the customers who have purchases a product more than the given quantity that the user gave
	$query = "SELECT firstname,lastname,description,quantity FROM customer,purchases,product WHERE customer.cusID=purchases.cusID AND purchases.prodID=product.prodID AND quantity>$quantity";
	$result = mysqli_query($connection,$query);
	if (!$result) {
     		die("databases query failed.");
	}

	echo "<h1>" . "These Customers Bought A Product More Than " . $quantity . " Times: " . "</h1>" . "<br><br>";
	
	//create a list and display all of the customers + the prodcut they purchased + the amount of times they purchased it
	echo "<ol>";
	while ($row = mysqli_fetch_assoc($result)) {
    		echo "<li>";
    		echo "Customer: " . $row["firstname"] . " " . $row[lastname] . "<br>Product Description: " . $row[description] . "<br>Quantity Bought: " . $row["quantity"] . "</li>" . "<br>";
	}
	mysqli_free_result($result);
	echo "</ol>";
}

//disconnect from the database
mysqli_close($connection);
?>

<!-- create a link to return to the main page -->
<br><br>
<a href="PHPfile1.php">RETURN TO MAIN PAGE</a>

</body>
</html>
