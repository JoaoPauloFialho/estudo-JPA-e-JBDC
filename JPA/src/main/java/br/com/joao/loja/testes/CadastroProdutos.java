package br.com.joao.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.joao.loja.dao.CategoriaDAO;
import br.com.joao.loja.dao.PedidoDAO;
import br.com.joao.loja.dao.ProdutoDAO;
import br.com.joao.loja.modelo.Categoria;
import br.com.joao.loja.modelo.Cliente;
import br.com.joao.loja.modelo.Pedido;
import br.com.joao.loja.modelo.Produto;
import br.com.joao.loja.util.JPAUtil;

public class CadastroProdutos {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		Pedido pedido = new PedidoDAO(em).buscaPedidoComCliente(1l);
		em.find(Pedido.class, 1l);
		
		
		System.out.println(pedido.getCliente().getNome());
		
		
	}
	
	public static void cadastrarProduto() {
		EntityManager em = new JPAUtil("loja").getEntityManager();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		Categoria categoria = new Categoria("televisao");
		
		em.getTransaction().begin();
		try {
			categoriaDAO.adicionarCategoria(categoria);			
		} catch (Exception error) {
			error.printStackTrace();
		}

		
		Produto celular = new Produto("SmartTV", "sansumg", new BigDecimal("5672.80"), categoria);


		ProdutoDAO prodDAO = new ProdutoDAO(em);

		try {
			prodDAO.adicionarProduto(celular);
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		em.getTransaction().commit();
		em.close();

	}

}
