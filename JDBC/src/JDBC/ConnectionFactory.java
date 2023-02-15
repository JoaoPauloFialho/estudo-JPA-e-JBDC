package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.*;


public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() throws Exception{
		ComboPooledDataSource cpds = new ComboPooledDataSource();   
		cpds.setDriverClass( "com.mysql.jdbc.Driver" );
		cpds.setJdbcUrl("url do banco de dados");
		cpds.setUser("user");                                  
		cpds.setPassword("senha");                                  
		cpds.setMaxPoolSize(15);
		this.dataSource = cpds;
		
	}
	
	public Connection conectar() throws Exception{
		return this.dataSource.getConnection();
		
	}
}
