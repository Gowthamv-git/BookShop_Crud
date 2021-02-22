<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookShop Management</title>
</head>
<body>
	<center>
		<h1>Books Management</h1>
		<h2>
			<a href="new">Add New Book</a> &nbsp;&nbsp;&nbsp;
			 <a href="list">List All Books</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of Books</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>PublishedDate</th>
				<th>Actions</th>
			</tr>

			<c:forEach var="book" items="${listBook}">
				<tr>
					<td><c:out value="${book.bookid}" /></td>
					<td><c:out value="${book.bookname}" /></td>
					<td><c:out value="${book.author}" /></td>
					<td><c:out value="${book.price}" /></td>
					<td><c:out value="${book.date}" /></td>

					<td><a href="edit?bookid=<c:out value='${book.bookid}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?bookid=<c:out value='${book.bookid}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>