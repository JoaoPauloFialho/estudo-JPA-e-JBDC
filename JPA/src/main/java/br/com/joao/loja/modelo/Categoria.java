package br.com.joao.loja.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	//quando usamos chave composta é preciso passar a anotaçao EmbeddedId e a classe CategoriaId precisa ter o Embeddable
	@EmbeddedId
	private CategoriaId id;
	
	//para fazer consulta nessa classe é preciso passar a classe que representa a chave primaria lá no find
	//em.find(Categoria.class, new CategoriaId(nome, tipo);
	
	public Categoria() {
	}
	
	public Categoria(String nome, String tipo) {
		this.id = new CategoriaId(nome, tipo);
	}
	
	public String getNome() {
		return this.id.getNome();
	}
}
