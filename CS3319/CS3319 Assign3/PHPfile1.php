<!DOCTYPE html>
<html>
<body>

<!-- connects to the database -->
<?php
include 'connectToDB.php';
?>


<h2><u> Welcome to the Sports Store's directory!</u></h2>
<br>
<h3> Current Customers: </h3>
<!-- Allows us to see the information of all the customers -->
<?php
include 'getData.php';
?> 

<hr>

<!-- Allows user to see all the purchases of a customer -->
<form action="getPurchases.php" method="post">
	Get The Customer's Purchase History:<br><br>
	CustomerID:<br>	
	<input type="text" name="customerID"  maxlength="2">
	<br><br>
	<input type="submit" value="Submit Customer ID">
</form>

<br>
<hr>
<br>

<!-- Allows user to see all of the products ordered in a specific way -->
<form action="getProducts.php" method="post">
	Get List of All Products in Which Order? <br><br>
	<input type="radio" name="productOrder" value="description ASC"> Product Description Ascending <br>
	<input type="radio" name="productOrder" value="description DESC"> Product Description Descending <br>
	<input type="radio" name="productOrder" value="cost ASC"> Product Price Ascending <br>
	<input type="radio" name="productOrder" value="cost DESC"> Product Price Descending <br>
	<br>
	<input type="submit" value="Submit Product Order">
</form>

<br>
<hr>
<br>

<!-- Allows user to insert a purchase for a customer -->
<form action="insertPurchase.php" method="post">
	Add a Purchase For a Customer:<br><br>
	Customer ID: 
	<input type="text" name="customerID" maxLength="2">
	<br>
	Product ID:   
	<input type="text" name="productID" maxLength="2">
	<br>
	Quantity: 
	<input type"text" name="quantity" maxLength="2">
	<br><br>
	<input type="submit" value="Insert Purchase">
</form>

<br>
<hr>
<br>

<!-- Allows user to create a new customer -->
<form action="insertCustomer.php" method="post">
	Insert A New Customer:
	<br><br>
	Customer ID:
	<input type="text" name="cusID" maxLength="2">
	<br>
	First Name: 
	<input type="text" name="firstname" maxLength="10">
	<br>
	Last Name:
	<input type="text" name="lastname" maxLength="10">
	<br>
	City: 
	<input type="text" name="city" maxLength="15">
	<br>
	Phone Number (use format ###-####):
	<input type="text" name="phoneNumber" maxLength="8">
	<br><br>
	<input type="submit" value="Insert Customer">
</form>

<br>
<hr>
<br>

<!-- Allows user to update the phone number of a customer -->
<form action="updatePhone.php" method="post">
	Choose Customer To Update Their Phone Number:
	<br><br>
	Customer ID: 
	<input type="text" name="customerID" maxLength="2">
	<br><br>
	<input type="submit" value="Submit Customer">
</form>

<br>
<hr>
<br>

<!-- Allows user to delete a customer in the database -->
<form action="deleteCustomer.php" method="post">
	Choose A Customer To Delete:
	<br><br>
	Customer ID:
	<input type="text" name="customerID" maxLength="2">
	<br><br>
	<input type="submit" value="Delete Customer">
</form>

<br>
<hr>
<br>

<!-- Allows user to see which customers bought more than a given quantity of any item -->
<form action="boughtQuantity.php" method="post">
	See Who Bought More Than A Given Quantity:
	<br><br>
	Quantity:
	<input type="text" name="quantity" maxLength="11">
	<br><br>
	<input type="submit" value="Check Customers">
</form>

<br>
<hr>
<br>

<!-- Allows user to see which products have not been bought yet -->
<form action="notBought.php" method="post">
	Show All Products That Have Never Been Bought: 
	<br><br>
	<input type="submit" value="Check Products">
</form>

<br>
<hr>
<br>

<!-- Allows user to see the details of a product -->
<form action="productPurchases.php" method="post">
	Show The Details Of A Particular Product:
	<br><br>
	Product ID: 
	<input type="text" name="prodID" maxLength="2">
	<br><br>
	<input type="submit" value="Check Details">
</form>

</body>
</html>

