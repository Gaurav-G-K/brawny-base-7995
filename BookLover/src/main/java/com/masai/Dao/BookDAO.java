package com.masai.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.masai.entity.Book;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public interface BookDAO {
	public List<Book> allBook();
	public void addBook(Book book) throws SomethingWentWrongException;
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException;
	public void increaseQuantity(int id,int n) throws SomethingWentWrongException,NoRecordFoundException;
	public void decreaseQuantity(int id,int n) throws SomethingWentWrongException,NoRecordFoundException;
	public List<Book> availableBooks() throws SomethingWentWrongException, NoRecordFoundException;
	public void rentedBooks() throws SomethingWentWrongException, NoRecordFoundException;
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException;
}
