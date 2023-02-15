package JDBC;

public class Produto {
	private Integer id;
	private String nome;
	private String descricao;
	private Integer categoriaId;

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

	public Produto(String nome, String descricao, Integer categoriaId) {
		this.nome = nome;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
	}
	
	public Produto(Integer id, String nome, String descricao, Integer categoriaId) {
		this(nome, descricao, categoriaId);
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ID -> " + this.id + " NOME -> " + this.nome + " DESCRICAO -> " + this.descricao + " CATEGORIA -> " + this.categoriaId; 
	}
}
