package br.com.joao.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	
	//por padrão sempre que você chama um select na JPA ela vai fazer um join por conta desse to one aqui
	//o padrão de carregamento do toone é eager pois ele faz o carregamento antecipado
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	//to one é carregado automaticamente
	//to many não é
	//o padrão de carregamento do tomany é lazy(preguiçoso), ele só carrega se você acessar 
	
	//quando está acontecendo um relacionamento bidirecional é preciso indicar pro jpa que esse é o lado contrário dos relacionamento, se não for indicado
	//a jpa vai criar uma tabela a mais do que o nescessário, pra indicar isso é só colocar o parâmetro mappedBy
	// fica no one to many esse mappedBy
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // o cascade faz com que tudo que aconteça em pedido aconteça em item pedido
															   // é muito importante fazer isso por item pedido é dependente de pedido
	 														   // e também pra n precisar criar um DAO pro item pedido também
	private List<ItemPedido> itens = new ArrayList<>(); //é uma boa prática iniciar a lista aqui pra n precisar ficar fazendo checagem com if depois
	
	public Pedido() {
	}
	
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//como é um relacionamento de 2 lados para que eu consiga adicionar um item obviamente eu preciso setar que o pedido do item é o 
	//pedido que tô modificando por isso antes eu seto o pedido no item e depois adiciona o item na lista
	public void adicionarItem(ItemPedido item) {
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getPrecoTotal());
		
	}

	public LocalDate getData() {
		return data;
	}

	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	

}
