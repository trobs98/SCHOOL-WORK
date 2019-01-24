<html>
<body>

<!-- Connect to the database -->
<?php
include 'connectToDB.php';
?>

<?php
$customerID = $_POST["customerID"];

//makes sure the textbox is not empty
if(empty($customerID)){
	die("EMPTY TEXTBOX: Please return to the previous screen and fill the text box");
}

//if its not empty, create a query to make sure the customer ID exists
$query = "SELECT cusID FROM customer WHERE customer.cusID='$customerID'";
$result = mysqli_query($connection,$query);

if(mysqli_num_rows($result) == 0){
	die("CUSTOMERID DOES NOT EXIST: Please return to the previous screen and enter a valid customer ID");
}
mysqli_free_result($result);

//if the customer ID exists then create a query so we can print the name of the customer
$query0 = "SELECT DISTINCT firstname,lastname FROM customer,purchases WHERE customer.cusID=purchases.cusID AND customer.cusID='$customerID'";
$result = mysqli_query($connection,$query0);
if (!$result) {
	die("databases query failed.");
}

echo "<h9>";
while($row = mysqli_fetch_assoc($result)){
	echo $row[firstname] . " " . $row[lastname] . " bought:" . "<br>";
}
mysqli_free_result($result);
echo "</h9>";

//create a query to get all the products the customer had bought
$query1 = "SELECT description FROM product,purchases WHERE purchases.prodID=product.prodID AND purchases.cusID='$customerID'";	
$result = mysqli_query($connection,$query1);

if (!$result) {
     	die("databases query failed.");
}

//create a list to print all the products the customer bought
echo "<ol>";
while ($row = mysqli_fetch_assoc($result)) {
    	echo "<li>";
    	echo $row[description] . "<br>" . "</li>";
}
mysqli_free_result($result);
echo "</ol>";

//disconnect from the database
mysqli_close($connection);
?>

<br><br>
<!-- create a link to return to the previous page -->
<a href="PHPfile1.php">RETURN TO PREVIOUS PAGE</a>

</body>
</html>
