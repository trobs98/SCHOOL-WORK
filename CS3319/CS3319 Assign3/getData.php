<?php
//returns a query with all the customer information ordered by their last name
$query = "SELECT * FROM customer ORDER BY lastname";
$result = mysqli_query($connection,$query);
if (!$result) {
    die("databases query failed.");
}

//prints a list of all the customer info from the query
echo "<ol>";
while ($row = mysqli_fetch_assoc($result)) {
    echo "<li>";
    echo $row[firstname] . " " . $row[lastname] . " (ID=" . $row[cusID] . ", City=" . $row[city] . ", Phone Number=" . $row[phonenumber] . ", Agent's ID=" . $row[agentID] . ")" .  "</li>";
}
mysqli_free_result($result);
echo "</ol>";
?>


