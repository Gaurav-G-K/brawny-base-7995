package com.masai.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.masai.entity.Book;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;
import com.masai.util.EmfUtil;
import jakarta.persistence.Query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class BookDAOImpl implements BookDAO {
	
	@Override
	public List<Book> allBook() throws SomethingWentWrongException {
	    List<Book> Books = null;
	    try (EntityManager em = EmfUtil.getEntityManager()) {
	        String s = "SELECT b FROM Book b";
	        Query query = em.createQuery(s);
	        Books = query.getResultList();
	    } catch (PersistenceException ex) {
	        throw new SomethingWentWrongException("Unable to add book, try again later");
	    }
	    return Books;
	}
//	public List<Book> allBook() throws SomethingWentWrongException {
//		EntityManager em = null;
//		List<Book> Books=null;
//		try {
//			em = EmfUtil.getEntityManager();
//			String s = "SELECT b FROM Book b";
//			Query query = em.createQuery(s);
//			Books=query.getResultList();
//		}catch(PersistenceException ex) {
//			throw new SomethingWentWrongException("Unable to add book, try again later");
//		}finally {
//			em.close();
//		}
//		return Books;
//	}
	
	@Override
	public String addBook(Book book) throws SomethingWentWrongException {
		EntityManager em = EmfUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
			et.begin();
			em.merge(book);
			et.commit();
			return "Book Added Successfully";
//			et.rollback();
//			throw new SomethingWentWrongException("Unable to add book, try again later");
	}

	@Override
	public Book getBookById(int id) throws SomethingWentWrongException,NoRecordFoundException {
		Book book = null;
		try(EntityManager em = EmfUtil.getEntityManager()){
			book = em.find(Book.class, id);
			if(book == null) {
				throw new NoRecordFoundException("No book available for given id");
			}
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to fetch book details, please try again later");
		}
		return book;
	}

	@Override
	public String updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		if(book==null)
			return "Book not resisterd";
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			book.setPrice(price);
			et.commit();
			return "Book Price Updated";
		}catch(PersistenceException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to Update price, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public String deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		if(book==null)
			return "Book not resistered";
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			em.remove(book);
			et.commit();
			return "Book deleted successfully";
		}catch(PersistenceException ex) {
			et.rollback();
			ex.printStackTrace();
			throw new SomethingWentWrongException("Unable to delete book, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public String increaseQuantity(int id, int n) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		if(book==null)
			return "Book not resistered";
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			book.setTotal(book.getTotal()+n);
			et.commit();
			return "Book quantity increases";
		}catch(PersistenceException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public String decreaseQuantity(int id, int n) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		if(book==null)
			return "Book not resistered";
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			if(book.getTotal()>=n){
			book.setTotal(book.getTotal()-n);
			et.commit();
			return "Book quantity decreases";
			}
			else
				throw new SomethingWentWrongException("Book Quantity is less then "+n);
		}catch(PersistenceException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public List<Book> availableBooks() throws SomethingWentWrongException, NoRecordFoundException {
		List<Book> Books=null;
		try(EntityManager em=EmfUtil.getEntityManager())
		{
			String s="SELECT b from Book WHERE rented<total";
			Query query =em.createQuery(s);
			Books=query.getResultList();
			if(Books.size()==0)
				throw new NoRecordFoundException("No Books Available");
		}
		return Books;
	}

	@Override
	public List<Book> rentedBooks() throws SomethingWentWrongException, NoRecordFoundException {
		List<Book> Books =null;
		try(EntityManager em=EmfUtil.getEntityManager())
		{
			String s="SELECT b FROM Book where rented>0";
			Query query=em.createQuery(s);
			Books =query.getResultList();
			if(Books.size()==0)
				throw new NoRecordFoundException("No Books Are rented");
		}
		return null;
	}

}
