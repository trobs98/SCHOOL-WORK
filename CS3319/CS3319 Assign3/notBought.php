<html>
<body>

<!-- Connects to the database -->
<?php
include "connectToDB.php";
?>

<?php
//creates a query that returns all of the products that have not been purchases before
$query = "SELECT DISTINCT description FROM product WHERE prodID NOT IN (SELECT prodID FROM purchases)";
$result = mysqli_query($connection,$query);
if (!$result) {
     die("databases query failed.");
}

echo "<h1> Products That Have Never Been Purchased: </h1>";
//This prints a list of the product descriptions that are in the query made above
echo "<ol>";
while ($row = mysqli_fetch_assoc($result)) {
    echo "<li>";
    echo $row["description"] . "</li>";
}
mysqli_free_result($result);
echo "</ol>";

//disconnect from the database
mysqli_close($connection);
?>

<!-- Create a link to return back to the main page -->
<br><br>
<a href="PHPfile1.php">RETURN TO MAIN PAGE</a>

</body>
</html>
