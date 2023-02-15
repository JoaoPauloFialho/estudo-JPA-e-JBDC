package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestaSelect {

	public static void main(String[] args) throws Exception{
		
		ConnectionFactory conexaoCriada = new ConnectionFactory();
		
		try (Connection con = conexaoCriada.conectar()){
			
			PreparedStatement stm = con.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTOS");
			stm.execute();
			
			ResultSet res = stm.getResultSet();
			
			while(res.next()) {//esses métodos get tambem podem ter como parametro o id da coluna
				Integer id = res.getInt("ID");
				String nome = res.getString("NOME");
				String descricao = res.getString("DESCRICAO");
				
				System.out.println("ID: " + id + " NOME: " + nome + " DESCRICAO: " + descricao);
				System.out.println("-------------------------------------------------------------------------------");
			}
			stm.close();
		
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}

}
