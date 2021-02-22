package com.bookshop1;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();

	}

	private Bookshopdao bookdao;

	public void init() {
		System.out.println("init");
		String URL = getServletContext().getInitParameter("URL");
		String UserName = getServletContext().getInitParameter("UserName");
		String Password = getServletContext().getInitParameter("Password");

		bookdao = new Bookshopdao(URL, UserName, Password);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("0");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doget");
		String action = request.getServletPath();
		System.out.println("1");
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			default:
				System.out.println("2");
				listBook(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("going to list");
		List<Bookshoprecords> list = bookdao.listallbooks();
		request.setAttribute("listBook", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Booklist.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("shownew form called");
		RequestDispatcher dispatcher = request.getRequestDispatcher("Bookform.jsp");
		dispatcher.forward(request, response);
		System.out.println("dispatched the responsed");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("showedit form called");
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		Bookshoprecords existingBook = bookdao.getBook(bookid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Editform.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException {
		System.out.println("insert in servlet");
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));
		String date1 = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("here error");
		java.util.Date date = sdf.parse(date1);

		System.out.println("date parsed");
		Bookshoprecords newBook = new Bookshoprecords(bookname, author, price, date);
		bookdao.insertBook(newBook);
		response.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ParseException {
		System.out.println("update book called");
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));
		String date1 = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(date1);

		Bookshoprecords book = new Bookshoprecords(bookid, bookname, author, price, date);
		bookdao.updateBook(book);
		response.sendRedirect("list");

	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int bookid = Integer.parseInt(request.getParameter("bookid"));

		Bookshoprecords book = new Bookshoprecords(bookid);
		bookdao.deleteBook(book);
		response.sendRedirect("list");

	}

}
