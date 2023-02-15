package JDBC;

import java.sql.Connection;

public class TestaListagemDeCategoria {

	public static void main(String[] args) {

		try (Connection con = new ConnectionFactory().conectar()) {

			try {
				
				CategoriaDAO categoriaDAO = new CategoriaDAO(con);
				categoriaDAO.listarCategorias().forEach(c -> System.out.println("Id -> " + c.getId() + " Nome -> " + c.getNome()));

			} catch (Exception error) {
				error.printStackTrace();
			}
		} catch (Exception error) {
			error.printStackTrace();
		}

	}

}
