package br.com.joao.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal preco;
	private int quantidade;
	//o problema desse lazy é que caso você use o select normal caso você queira acessar o atributo que é manytoone lazy depois do entity
	//manager ser fechado você vai gerar um erro no código	
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;



	public ItemPedido() {
	}

	public ItemPedido(int quantidade, Produto produto, Pedido pedido) {
		this.preco = preco;
		this.quantidade = quantidade;
		this.preco = produto.getPreco();
		this.produto = produto;
		this.pedido = pedido;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public BigDecimal getPrecoTotal() {
		return this.preco.multiply(new BigDecimal(quantidade));
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
