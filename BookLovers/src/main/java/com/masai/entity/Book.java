package com.masai.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_table")
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 75, nullable = false, unique = true)
	private String title;
	@Column(length = 40, nullable = false)
	private String author;
	@Column(name="total_books")
	private int total;
	@Column(name="available_books")
	private int available;
	@Column(name="rented_books")
	private int rented;
//	@Column(nullable = false, precision = 7, scale = 2)	
	@Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
	private BigDecimal price;
	
	@Column(name = "publish_date")
	private LocalDate publishDate;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Book(int id, String title, String author, int total, int available, int rented, BigDecimal price,
			LocalDate publishDate) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.total = total;
		this.available = available;
		this.rented = rented;
		this.price = price;
		this.publishDate = publishDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		total = total;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getRented() {
		return rented;
	}

	public void setRented(int rented) {
		this.rented = rented;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", total=" + total + ", available="
				+ available + ", rented=" + rented + ", price=" + price + ", publishDate=" + publishDate + "]";
	}
}

