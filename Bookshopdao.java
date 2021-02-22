package com.bookshop1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bookshopdao {
	private String URL;
	private String UserName;
	private String Password;
	private Connection con;

	public Bookshopdao(String URL, String UserName, String Password) {

		this.URL = URL;
		this.UserName = UserName;
		this.Password = Password;
	}

	protected void connect() throws SQLException {
		System.out.println("Connecting DB");

		if (con == null || con.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			con = DriverManager.getConnection(URL, UserName, Password);
		}
		System.out.println("DB Connected");
	}

	protected void disconnect() throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
		}
		System.out.println("DB disConnected");
	}

	public boolean insertBook(Bookshoprecords book) throws SQLException {
		System.out.println("Insert method called");
		String sql = "insert into BookDetails(bookname,author,price,pdate) values (?,?,?,?);";
		connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, book.getauthor());
		stmt.setString(2, book.getbookname());
		stmt.setFloat(3, book.getprice());
		java.util.Date utilDate = book.getdate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		stmt.setDate(4, sqlDate);

		boolean rowinsert = stmt.executeUpdate() > 0;
		System.out.println("data inserted into db");
		stmt.close();
		disconnect();

		return rowinsert;

	}

	public List<Bookshoprecords> listallbooks() throws SQLException {
		System.out.println("listmethod called");
		List<Bookshoprecords> list = new ArrayList<>();
		String sql = "select * from BookDetails";
		connect();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int bookid = rs.getInt("bookid");
			String bookname = rs.getString("bookname");
			String author = rs.getString("author");
			float price = rs.getFloat("price");
			Date date = rs.getDate("pdate");
			Bookshoprecords book = new Bookshoprecords(bookid, bookname, author, price, date);
			list.add(book);

		}
		System.out.println("list all data");
		rs.close();
		stmt.close();
		disconnect();

		return list;

	}

	public boolean deleteBook(Bookshoprecords book) throws SQLException {
		System.out.println("delete method called");
		String sql = "delete from bookdetails where bookid=?";
		connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, book.getbookid());

		boolean rowdeleted = stmt.executeUpdate() > 0;
		System.out.println("deleted the given data");
		stmt.close();
		disconnect();

		return rowdeleted;
	}

	public boolean updateBook(Bookshoprecords book) throws SQLException {
		System.out.println("update method called");
		String sql = "update BookDetails set bookname = ?, author = ?, price = ? , pdate = ?";
		sql += " WHERE bookid = ? ";
		connect();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, book.getbookname());
		stmt.setString(2, book.getauthor());
		stmt.setFloat(3, book.getprice());
		System.out.println("Update book in getting");
		java.util.Date utilDate = book.getdate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		stmt.setDate(4, sqlDate);
		stmt.setInt(5, book.getbookid());
		System.out.println("completed");

		boolean rowupdate = stmt.executeUpdate() > 0;
		System.out.println("data updated for given id");
		stmt.close();
		disconnect();

		return rowupdate;

	}

	public Bookshoprecords getBook(int id) throws SQLException {
		Bookshoprecords book = null;
		String sql = "SELECT * FROM bookdetails WHERE bookid = ?";

		connect();

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String title = resultSet.getString("bookname");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			Date date = resultSet.getDate("pdate");
			book = new Bookshoprecords(id, title, author, price, date);
		}

		resultSet.close();
		statement.close();

		return book;
	}

}
