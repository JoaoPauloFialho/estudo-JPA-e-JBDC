package br.com.joao.loja.dao;

import javax.persistence.EntityManager;

import br.com.joao.loja.modelo.Cliente;

public class ClienteDAO {
	private EntityManager em;
	
	public ClienteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void adicionarCliente(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public void atualizarCliente(Cliente cliente) {
		this.em.merge(cliente);
	}
	
	public void removerCliente(Cliente cliente){
		this.em.merge(cliente);
		this.em.remove(cliente);
	}
	
}
