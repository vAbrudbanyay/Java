<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Spring Boot</title>
</head>
<body>
	  	<form action="processForm" method="POST">
		Fizetendo osszeg: <input type="text" name="fizetendoOsszeg"
			placeholder="860" /> <input type="submit" value="Mehet">
	</form>

	<br>
	<br>

	<div>${message}</div>	
</body>
</html>