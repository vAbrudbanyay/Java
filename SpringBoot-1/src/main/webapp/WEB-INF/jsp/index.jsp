<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>Kifizetés</title>
</head>
<body>

<body>
<div>
	<form action="processForm" method="POST">
		Fizetendő összeg: <input type="text" name="fizetendoOsszeg"
			placeholder="860" /> <input type="submit" value="Mehet">
	</form>

<div class="visszajelzes">	
${message}
</div>
</div>


</body>

</html>