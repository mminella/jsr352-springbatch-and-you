package org.springframework.batch.jsr;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionManager {

	private final static DataSource dataSource = setupDataSource();

	public static DataSource getDataSource() {
		return dataSource;
	}

	private static DataSource setupDataSource() {
		Properties props = new Properties();
		InputStream inputStream = ConnectionManager.class.getResourceAsStream("batch-hsql.properties");

		try {
			props.load(inputStream);
			inputStream.close();
			Class.forName(props.getProperty("batch.jdbc.driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		ObjectPool connectionPool = new GenericObjectPool(null);
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(props.getProperty("batch.jdbc.url"), null);
		new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);
		PoolingDataSource dataSource = new PoolingDataSource(connectionPool);

		return dataSource;
	}

}
