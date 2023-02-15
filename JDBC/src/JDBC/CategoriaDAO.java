package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Categoria> listarCategorias() throws SQLException {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		Statement stm = this.connection.createStatement();
		String sql = "SELECT ID, NOME FROM categorias";
		stm.execute(sql);

		ResultSet result = stm.getResultSet();

		while (result.next()) {
			Categoria categoria = new Categoria(result.getInt(1), result.getString(2));
			categorias.add(categoria);
		}

		return categorias;
	}
}
