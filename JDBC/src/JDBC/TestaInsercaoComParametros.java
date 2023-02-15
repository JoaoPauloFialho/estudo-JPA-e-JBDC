package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestaInsercaoComParametros {

	public static void main(String[] args) throws Exception{
		ConnectionFactory conexaoCriada = new ConnectionFactory();
		String nome = "Cadeira";
		String descricao = "Cadeira Gamer";

		try (Connection con = conexaoCriada.conectar()) {
			con.setAutoCommit(false);
			try (PreparedStatement stm = con.prepareStatement("INSERT INTO produtos (NOME, DESCRICAO) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);) {
				adicionaProduto("SmartTV", "SmartTV Samsung", stm);
				adicionaProduto("Notebook", "Notebook Lenovo", stm);
				con.commit();
			} catch (Exception error) {
				error.printStackTrace();
				con.rollback();
			}

		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public static void adicionaProduto(String nome, String descricao, PreparedStatement stm) throws Exception {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		stm.execute();

		ResultSet rst = stm.getGeneratedKeys();

		while (rst.next()) {
			System.out.println("Id gerado -> " + rst.getInt(1));
		}

	}
}
