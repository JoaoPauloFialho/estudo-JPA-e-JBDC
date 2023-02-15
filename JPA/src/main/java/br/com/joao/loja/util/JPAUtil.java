package br.com.joao.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private String persistence_unit_name;
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistence_unit_name);
	
	public JPAUtil(String persistence_unit_name) {
		this.persistence_unit_name = persistence_unit_name;
	}
	
	
	public EntityManager getEntityManager() {
		return this.factory.createEntityManager();
	}
}
