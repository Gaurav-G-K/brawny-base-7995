package com.masai.services;

import java.math.BigDecimal;
import java.util.List;

import com.masai.entity.Book;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public interface BookService {
	public List<Book> allBook() throws SomethingWentWrongException;
	public String addBook(Book book) throws SomethingWentWrongException;
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
	public String updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException;
	public String increaseQuantity(int id,int n) throws SomethingWentWrongException,NoRecordFoundException;
	public String decreaseQuantity(int id,int n) throws SomethingWentWrongException,NoRecordFoundException;
	public List<Book> availableBooks() throws SomethingWentWrongException, NoRecordFoundException;
	public List<Book> rentedBooks() throws SomethingWentWrongException, NoRecordFoundException;
	public String deleteBookById(int id,int n) throws SomethingWentWrongException, NoRecordFoundException;
}
