package com.bookshop1;

import java.util.Date;

public class Bookshoprecords {
	protected int bookid;
	protected String bookname;
	protected String author;
	protected float price;
	protected Date date;

	public Date getdate() {
		return date;
	}

	public void setdate(Date date) {
		this.date = date;
	}

	public Bookshoprecords() {
	}

	public Bookshoprecords(int bookid) {
		this.bookid = bookid;
	}

	public Bookshoprecords(int bookid, String bookname, String author, float price, Date date) {
		this(bookname, author, price, date);
		this.bookid = bookid;
	}

	public Bookshoprecords(String bookname, String author, float price, Date date) {
		this.author = author;
		this.bookname = bookname;
		this.price = price;
		this.date = date;
	}

	public int getbookid() {
		return bookid;
	}

	public void setbookid(int bookid) {
		this.bookid = bookid;
	}

	public String getbookname() {
		return bookname;
	}

	public void setbookname(String bookname) {
		this.bookname = bookname;
	}

	public String getauthor() {
		return author;
	}

	public void setauthor(String author) {
		this.author = author;
	}

	public float getprice() {
		return price;
	}

	public void setprice(float price) {
		this.price = price;
	}

}
