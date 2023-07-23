package com.masai.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.masai.entity.Book;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;
import com.masai.util.EmfUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class BookDAOImpl implements BookDAO {
	
	@Override
	public List<com.masai.entity.Book> allBook() {
		
		return null;
	}
	
	@Override
	public void addBook(Book book) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(book);
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();
		}
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
	public void updateBookPrice(int id, BigDecimal price) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			book.setPrice(price);
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public void deleteBookById(int id) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			em.remove(book);
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			ex.printStackTrace();
			throw new SomethingWentWrongException("Unable to delete book, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public void increaseQuantity(int id, int n) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			book.setTotal(book.getTotal()+n);
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public void decreaseQuantity(int id, int n) throws SomethingWentWrongException, NoRecordFoundException {
		Book book = getBookById(id);
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EmfUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			book = em.merge(book);
			if(book.getTotal()>=n)
			book.setTotal(book.getTotal()-n);
			else
				throw new SomethingWentWrongException("Book Quantity is less then "+n);
			et.commit();
		}catch(PersistenceException ex) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to add book, try again later");
		}finally {
			em.close();
		}
	}

	@Override
	public List<Book> availableBooks()
			throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rentedBooks()
			throws com.masai.exceptions.SomethingWentWrongException, com.masai.exceptions.NoRecordFoundException {
		// TODO Auto-generated method stub
		
	}
	
}
