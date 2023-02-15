package br.com.joao.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
//evitar importar do hibernate, só importar do javax.persistence
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//como estamos usando o hibernate n é nescessário, mas existe algumas libs que nescessitam que seja colocado lá no arquivo persistence.xml 
// dentro do pesristance-unit uma tag <class>endereço da entidade<class>

//esse entity é para deixar claro pro jpa que a classe produto é uma entidade
@Entity

//como em java a classe segue um padrão de nomenclatura para definir o nome da tabela no jpa é preciso usar uma anotação
@Table(name = "produtos")
@NamedQuery(name = "Produto.produtoPorNome", query = "SELECT produto FROM Produto produto WHERE produto.nome = :nome")

//esse inheritance controla basicamente o jeito que o JPA vai lidar com herança, esse single table é que ele vai agrupar todos os valores em 1 table
//o lado bom dele é que ele tem uma boa perfomance já que não são utilizados join's
//com esse single table quando a jpa for instanciar as tabelas ela vai criar uma coluna com o nome DTYPE que serve basicamente pra identificar
//qual classe está herdando
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

//com esse joined basicamente vão ser criadas tabelas para cada herança
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {

	// essa anotação é para especificar que o atributo id é um id
	@Id
	// essa anotação é para falar pra jpa que esse atributo é gerado pelo banco de
	// dados
	// essa estratégia muda de acordo com o banco de dados, o mysql gera id's em
	// sequência
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	//@Column(name = "desc")
	// essa anotação é para a jpa salvar a coluna da entidade no banco de dados como
	// desc em vez de descricao como é oq tá escrito
	// no atributo
	private String descricao;
	private BigDecimal preco;
	private LocalDate data = LocalDate.now();
	//@Enumerated(EnumType.STRING)
	//quando é usado um enum como atriburo numa entidade no mapeamento é preciso indicar isso configurando o cadastro como string
	//já que o padrão é pela ordem ou seja o índice da constante
	//private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//quando trata-se de chave estrangeira no JPA você passa a entidade em si como parâmetro e indica a cardinalidade da relação com uma
	//anotação, no caso do ManyToOne é que diversos produtos pertencem a uma categoria
	private Categoria categoria;
	
	public Produto() {
	}

	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria ) {
		
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}

//isso que eu fiz é chamado de mapeamento de entidade