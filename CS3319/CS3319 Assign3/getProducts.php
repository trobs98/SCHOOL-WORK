<html>
<body>

<!-- Connect to the database -->
<?php
include'connectToDB.php';
?>

<h1><u> All Products: </u></h1>

<?php
$productOrder = $_POST["productOrder"];

//makes sure the user selects a button
if(empty($productOrder)){
	die("ERROR: You did not select a box");
}

//creates a query of the description and cost of all products ordered by the order the user chose
$query = "SELECT description,cost FROM product ORDER BY $productOrder";
$result = mysqli_query($connection,$query);
if (!$result) {
     die("databases query failed.");
}

//creates a list of all the products descriptions + cost
echo "<ol>";
while ($row = mysqli_fetch_assoc($result)) {
    echo "<li>";
    echo "Description: " . $row["description"] . "<br>" . "Price: " . $row["cost"] . "</li>";
}
mysqli_free_result($result);
echo "</ol>";

//disconnect from the datbase
mysqli_close($connection);
?>

<br><br>
<!-- create a link to return to the previous page -->
<a href="PHPfile1.php">RETURN TO PREVIOUS PAGE</a>

</body>
</html>
