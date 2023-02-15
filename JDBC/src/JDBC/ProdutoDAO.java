package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvarProduto(Produto produto){

		try (PreparedStatement stm = this.connection.prepareStatement(
				"INSERT INTO produtos(NOME, DESCRICAO) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);) {

			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			stm.execute();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public ArrayList<Produto> listaProdutos(){
		ArrayList<Produto> produtos = new ArrayList<Produto>();

		try (Statement stm = this.connection.createStatement();) {

			String sql = "SELECT ID, NOME, DESCRICAO FROM produtos";
			stm.execute(sql);

			ResultSet result = stm.getResultSet();

			while (result.next()) {
				Produto produto = new Produto(result.getInt(1), result.getString(2), result.getString(3),
						result.getInt(4));
				produtos.add(produto);
			}
		}catch(Exception error) {
			error.printStackTrace();
		}

		return produtos;
	}

}
