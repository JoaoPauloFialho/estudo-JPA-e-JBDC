package JDBC;

import java.sql.Connection;

public class TestaPoolConexoes {
	public static void main(String[] args) throws Exception{
		ConnectionFactory conexaoCriada = new ConnectionFactory();
		for(int i = 0 ; i < 20 ; i++) {
			conexaoCriada.conectar();
			System.out.println("Conexao criada -> " + ((int)i+1));
		}

	}
}
