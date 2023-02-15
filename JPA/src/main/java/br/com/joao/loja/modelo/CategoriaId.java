package br.com.joao.loja.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
//essa classe de chave composta é preciso ter o serializable
public class CategoriaId implements Serializable{
	private String nome;
	private String tipo;
	
	
	
	public CategoriaId(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
