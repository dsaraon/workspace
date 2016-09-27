<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yahoo!!!! From JSP</title>
</head>
<body>

<form action="/login" method="post">
<p>${errorMessage}</p>
Enter name <input type = "text" name = "name"/>
Enter password <input type = "password" name = "password"/>
<input type="submit"/>
</form>
</body>
</html>