package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TestaInsercaoComProduto {
	public static void main(String[] args){
		
		
		try(Connection con = new ConnectionFactory().conectar()){
			con.setAutoCommit(false);
			
			try {
				
				Produto produto = new Produto("Comoda", "Comoda eucalipto", 3);
				ProdutoDAO persistencia = new ProdutoDAO(con);
				persistencia.salvarProduto(produto);
				
				ArrayList<Produto> produtos =  persistencia.listaProdutos();
				
				produtos.forEach(p -> System.out.println(p));
				
			}catch(Exception error) {
				error.printStackTrace();
				con.rollback();
			}
			
			con.commit();
			
		}catch(Exception error) {
			error.printStackTrace();
		}
	}
}
