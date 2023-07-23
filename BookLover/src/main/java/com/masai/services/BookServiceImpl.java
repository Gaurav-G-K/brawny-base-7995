package com.masai.services;

import java.math.BigDecimal;
import java.util.List;
import com.masai.Dao.BookDAO;
import com.masai.Dao.BookDAOImpl;
import com.masai.entity.Book;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public class BookServiceImpl implements BookService{
	BookDAO books=new BookDAOImpl();

	@Override
	public List<Book> allBook() throws SomethingWentWrongException {
		return books.allBook();
		}

	@Override
	public String addBook(Book book) throws SomethingWentWrongException {
		return books.addBook(book);
		
	}

	@Override
	public Book getBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		return books.getBookById(id);
	}

	@Override
	public String updateBookPrice(int id, Double price) throws SomethingWentWrongException, NoRecordFoundException {
		return books.updateBookPrice(id, price);
		
	}

	@Override
	public String increaseQuantity(int id, int n) throws SomethingWentWrongException, NoRecordFoundException {
		return books.increaseQuantity(id, n);
		
	}

	@Override
	public String decreaseQuantity(int id, int n) throws SomethingWentWrongException, NoRecordFoundException {
		return books.decreaseQuantity(id, n);
		
	}

	@Override
	public List<Book> availableBooks() throws SomethingWentWrongException, NoRecordFoundException {
		return books.availableBooks();
	}

	@Override
	public List<Book> rentedBooks() throws SomethingWentWrongException, NoRecordFoundException {
		return books.rentedBooks();
	}

	@Override
	public String deleteBookById(int id,int n) throws SomethingWentWrongException, NoRecordFoundException {
		return books.decreaseQuantity(id,n);
		
	}

}
