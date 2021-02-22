<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Book Creation</title>
</head>
<body>
	<center>
		<h1>Books Management</h1>
		<h2>
			<a href="new">Add New Book</a> &nbsp;&nbsp;&nbsp; <a href="list">List
				All Books</a>

		</h2>
	</center>
	<div align="center">
		<form action="insert" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>Add New Book</h2>
				</caption>
				<tr>
					<th>BookName</th>
					<td><input type="text" name="bookname" size="45"
						value='${book.bookname}' /></td>
				</tr>
				<tr>
					<th>Author:</th>
					<td><input type="text" name="author" size="45"
						value='${book.author}' /></td>
				</tr>
				<tr>
					<th>Price:</th>
					<td><input type="text" name="price" size="5"
						value='${book.price}' /></td>
				</tr>
				<tr>
					<th>PublishedDate:</th>
					<td><input type="date" name="date" size="5"
						value='${book.date}' /></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>