package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestaDelete {

	public static void main(String[] args) throws Exception{
		ConnectionFactory criaConexao = new ConnectionFactory();
		
		try(Connection con = criaConexao.conectar()){
			
			PreparedStatement stm = con.prepareStatement("DELETE FROM produtos WHERE ID > 2");
			stm.execute();
			Integer linhasModificadas = stm.getUpdateCount();
			System.out.println("Linhas modificadas -> " + linhasModificadas);
			stm.close();
			
		}catch(Exception error) {
			error.printStackTrace();
		}

	}

}
