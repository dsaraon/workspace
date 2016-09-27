<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>

<table>
	<caption>Your todos are</caption>
	<thead>
		<th>Description</th>
		<th>Target Date</th>
		<th>Is Completed?</th>
	</thead>
	<tbody>
		<c:forEach items="${todos}" var="todo">
		<br/>
		<tr>
			<td>${todo.desc}</td>
			<td>${todo.targetDate}</td>
			<td>${todo.done}</td>
		</tr>
		</c:forEach>
		
		<tr></tr>
		<tr></tr>	
		<tr></tr>
	</tbody>
</table>



<br/>
<a class="button" href = "/add-todo">Add</a>
</body>
</html>