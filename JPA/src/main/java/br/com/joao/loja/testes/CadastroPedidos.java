package br.com.joao.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.joao.loja.dao.CategoriaDAO;
import br.com.joao.loja.dao.ClienteDAO;
import br.com.joao.loja.dao.PedidoDAO;
import br.com.joao.loja.dao.ProdutoDAO;
import br.com.joao.loja.modelo.Categoria;
import br.com.joao.loja.modelo.Cliente;
import br.com.joao.loja.modelo.ItemPedido;
import br.com.joao.loja.modelo.Pedido;
import br.com.joao.loja.modelo.Produto;
import br.com.joao.loja.util.JPAUtil;
import br.com.joao.loja.modelo.RelatorioDeVendasVo;

public class CadastroPedidos {
	
	public static void main(String[] args) {
		
		cadastrarProduto();
		
		EntityManager em = new JPAUtil("loja").getEntityManager();
		
		em.getTransaction().begin();
		
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		Cliente cliente = new Cliente("Joao", "123456");
		Pedido pedido = new Pedido(cliente);
		
		
		clienteDAO.adicionarCliente(cliente);
		pedido.adicionarItem(new ItemPedido(2 , produtoDAO.buscarPorId(1l), pedido));
		pedidoDAO.adicionarPedido(pedido);
		
		em.getTransaction().commit();
		
		List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendas();
		relatorio.forEach(p -> System.out.println(p));
		
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
