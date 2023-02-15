package br.com.joao.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private DadosPessoais dadosPessosias;
	
	public Cliente() {
	}
	
	public Cliente(String nome, String cpf) {
		this.dadosPessosias = new DadosPessoais(nome, cpf);
	}

	public String getNome() {
		return dadosPessosias.getNome();
	}


	public String getCpf() {
		return dadosPessosias.getCpf();
	}

}
