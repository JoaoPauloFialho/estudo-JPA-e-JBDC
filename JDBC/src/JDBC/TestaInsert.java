package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestaInsert {
	public static void main(String[] args) throws Exception{

		ConnectionFactory conexaoCriada = new ConnectionFactory();

		try (Connection con = conexaoCriada.conectar()) {

			PreparedStatement stm = con.prepareStatement("INSERT INTO produtos (NOME, DESCRICAO) VALUES ('Carregador', 'Carregador Power')", Statement.RETURN_GENERATED_KEYS);
			stm.execute();
			
			ResultSet rst = stm.getGeneratedKeys();
			
			while(rst.next()) {
				System.out.println("Id gerado -> " + rst.getInt(1));
			}

			stm.close();
			
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}