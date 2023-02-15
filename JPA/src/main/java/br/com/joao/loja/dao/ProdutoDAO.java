package br.com.joao.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.joao.loja.modelo.Categoria;
import br.com.joao.loja.modelo.Produto;

public class ProdutoDAO {
	private EntityManager em;

	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}

	public void adicionarProduto(Produto produto) {
		this.em.persist(produto);
	}

	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}

	public void remover(Produto produto) {
		this.em.merge(produto);
		this.em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}

	public List<Produto> buscaTodos() {
		return this.em.createQuery("SELECT produto FROM Produto produto", Produto.class).getResultList();
	}

	public List<Produto> buscaPorNome(String nome) {
		return this.em.createNamedQuery("Produto.produtoPorNome", Produto.class).setParameter("nome", nome)
				.getResultList();
	}

	public List<Produto> buscaPorCategoria(String categoria) {
		String jpql = "SELECT produto FROM Produto produto WHERE produto.categoria.nome = ?1";
		return this.em.createQuery(jpql, Produto.class).setParameter(1, categoria).getResultList();
	}

	public BigDecimal buscaPrecoDoProdutoPorNome(String nome) {
		String jpql = "SELECT produto.preco FROM Produto produto WHERE produto.nome = :nome";
		return this.em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();
	}

	public List<Produto> buscaPorParametrosJPQL(String nome, BigDecimal preco, LocalDate dataCadastro) {
		//esse 1=1 é obrigattório para meio que ter um true ali pra sempre dar certo o where
		String jpql = "SELECT p FROM Pedido p WHERE 1=1";
		if (nome != null && !(nome.trim().isEmpty()))
			jpql += "AND p.nome = :nome";
		if (preco != null)
			jpql += "AND p.preco = :preco";
		if (dataCadastro != null)
			jpql += "AND p.data = :data";
		
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		if (nome != null && !(nome.trim().isEmpty()))
			query.setParameter("nome", nome);
		if (preco != null)
			query.setParameter("preco", preco);
		if (dataCadastro != null)
			query.setParameter("dataCadastro", dataCadastro);
		return query.getResultList();
	}
	
	public List<Produto> buscaPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if (nome != null && !(nome.trim().isEmpty()))
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		if (preco != null)
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		if (dataCadastro != null)
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}
}
