package br.com.joao.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;


import br.com.joao.loja.modelo.Pedido;
import br.com.joao.loja.modelo.Produto;
import br.com.joao.loja.modelo.RelatorioDeVendasVo;

public class PedidoDAO {
	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		this.em = em;
	}

	public void adicionarPedido(Pedido pedido) {
		this.em.persist(pedido);
	}

	public void atualizarPedido(Pedido pedido) {
		this.em.merge(pedido);
	}

	public void excluirPedido(Pedido pedido) {
		this.em.merge(pedido);
		this.em.remove(pedido);
	}

	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	//aqui podia ser usado o tipo Object[] porém é meio feio é melhor usar um classe padrão vo para armazenas os dados coletados
	//essa consulta de relatórios com select new basicamente eu tô instanciando uma classe nova passando os dados da querry como parâmetros
	public List<RelatorioDeVendasVo> relatorioDeVendas() {
		String jpql = "SELECT new br.com.joao.loja.modelo.RelatorioDeVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data))"
				+ "FROM Pedido pedido"
				+ "JOIN pedido.itens item"
				+ "JOIN item.produto produto"
				+ "GROUP BY produto.nome" 
				+ "ORDER BY item.quantidade DESC";
		return this.em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}
	
	public Pedido buscaPedidoComCliente(Long id) {
		return this.em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
		
}
