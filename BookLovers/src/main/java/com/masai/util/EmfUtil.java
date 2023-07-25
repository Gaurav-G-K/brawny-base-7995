package com.masai.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmfUtil {

	static EntityManagerFactory emf=null;
	static {
		emf=Persistence.createEntityManagerFactory("booklover");
	}
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
//	public static void main(String[] args) {
//		EntityManager et=emf.createEntityManager();
//		System.out.println(et);
//		et.close();
//		System.out.println(et);
//	}
}
