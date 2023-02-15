package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teste {

	public static void main(String[] args) throws Exception{
		ConnectionFactory conexaoCriada = new ConnectionFactory();
		try(Connection con = conexaoCriada.conectar()){
			con.setAutoCommit(false);
			
			try(PreparedStatement stm = con.prepareStatement("INSERT INTO produtos(NOME, DESCRICAO) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);){
			
			stm.setString(1, "Ventilador");
			stm.setString(2, "Ventilador Arno");
			
			stm.execute();
			
			ResultSet result = stm.getGeneratedKeys();
			
			while(result.next()) {
				int id = result.getInt(1);
				System.out.println("Id gerado -> " + id);
			}
			
			}catch(Exception error) {
				con.rollback();
				error.printStackTrace();
			}
			
			con.commit();
			
		}catch(Exception error) {
			error.printStackTrace();
		}
	}
		
}
