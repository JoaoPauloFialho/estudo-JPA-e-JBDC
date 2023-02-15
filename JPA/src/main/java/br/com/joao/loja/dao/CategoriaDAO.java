package br.com.joao.loja.dao;

import javax.persistence.EntityManager;

import br.com.joao.loja.modelo.Categoria;

public class CategoriaDAO {
	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void adicionarCategoria(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		this.em.merge(categoria);
		this.em.remove(categoria);
	}
}
