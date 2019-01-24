<html>
<body>

<!-- connect to the database -->
<?php
include "connectToDB.php";
?>

<?php
$prodID = $_POST["prodID"];
//makes sure the user entered in something into the text box
if(empty($prodID)){
	echo "EMPTY TEXT BOX: Please return to the previous page and fill the text box";
}

else{
	//creates a query that will determine if the product id exists
	$query1 = "SELECT prodID FROM product WHERE product.prodID='$prodID'";
	$result1 = mysqli_query($connection,$query1);
	if (!$result1) {
		die("databases query failed.");
	}
	//this occurs if the product id DOES NOT exist
	if(mysqli_num_rows($result1) == 0){
		die("The product you have entered does not exist.");
	}
	mysqli_free_result($result1);

	//create a query from the view I created in mysql that returns all the info for the product
	$query = "SELECT description,SUM(Total_Quantity) AS TotalQuantity,SUM(Total_Spent) AS TotalSpent FROM PurchaseHistory WHERE prodID='$prodID'";
	$result = mysqli_query($connection,$query);
	if (!$result) {
     		die("databases query failed.");
	}

	echo "<h1> Details Of Product: </h1> <br><br>";	
		
	while ($row = mysqli_fetch_assoc($result)) {
		//If the product has never been purchased this will print
		if(empty($row[description])){
			die("This product has never been purchased");
		}
		//otherwise it will print all the info
    		echo "Product: " . $row[description] . "<br>Total Sold: " . $row[TotalQuantity] . "<br>Total Dollar Amount Sold: $" . $row["TotalSpent"];
	}
	mysqli_free_result($result);
}
//disconnect from the database
mysqli_close($connection);
?>

<!-- Create a link to get back to the main page -->
<br><br>
<a href="PHPfile1.php">RETURN TO MAIN PAGE</a>

</body>
</html>
