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
}
