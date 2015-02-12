package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import com.example.Config;

/**
 * @author gimbyeongsu
 *
 */
public class ConnectionPoolManager {
	private final static Logger LOGGER = Logger.getLogger(ConnectionPoolManager.class);
	private static final ConnectionPoolManager INSTANCE = new ConnectionPoolManager();
	private final static String POOL_NAME = "GUAVA";
	private final static String POOL_DRIVER_URL = "jdbc:apache:commons:dbcp:";
	private final static String POOL_URL = POOL_DRIVER_URL + POOL_NAME;
	private final String driverClassName;
	private final String url;
	private final String userName;
	private final String password;
	private final boolean defaultAutoCommit;
	private final boolean defaultReadOnly;
	private final GenericObjectPool<Connection> connectionPool = new GenericObjectPool<Connection>(
			null);
	private ConnectionFactory connectionFactory;
	private PoolableConnectionFactory poolableConnectionFactory;
	/**
	 * 커넥션 풀이 제공할 최대 커넥션 개수
	 */
	private final int maxActive;
	/**
	 * 커넥션 풀에서 가져올 수 있는 커넥션이 없을 때 어떻게 동작할지를 지정한다.
	 * 1일 경우 maxWait 속성에서 지정한 시간만큼 커넥션을 구할 때 까지 기다리며, 0일 경우 에러를 발생시킨다.
	 * 2일 경우에는 일시적으로 커넥션을 생성해서 사용한다.
	 */
	private final byte whenExhaustedAction;
	/**
	 * whenExhaustedAction 속성의 값이 1일 때 사용되는 대기 시간.
	 * 단위는 1/1000초이며, 0 보다 작을 경우 무한히 대기한다.
	 */
	private final long maxWait;
	/**
	 * 사용되지 않고 풀에 저장될 수 있는 최대 커넥션 개수. 음수일 경우 제한이 없다.
	 */
	private final int maxIdle;
	/**
	 * 사용되지 않고 풀에 저장될 수 있는 최소 커넥션 개수.
	 */
	private final int minIdle;
	/**
	 * true일 경우 커넥션 풀에서 커넥션을 가져올 때 커넥션이 유효한지의 여부를 검사한다.
	 */
	private final boolean testOnBorrow;
	/**
	 * true일 경우 커넥션 풀에 커넥션을 반환할 때 커넥션이 유효한지의 여부를 검사한다.
	 */
	private final boolean testOnReturn;
	/**
	 * 사용되지 않은 커넥션을 추출하는 쓰레드의 실행 주기를 지정한다.
	 * 양수가 아닐 경우 실행되지 않는다. 단위는 1/1000 초이다.
	 */
	private final long timeBetweenEvictionRunsMillis;
	/**
	 * 사용되지 않는 커넥션을 몇 개 검사할지 지정한다.
	 */
	private final int numTestsPerEvictionRun;
	/**
	 * 사용되지 않는 커넥션을 추출할 때 이 속성에서 지정한 시간 이상 비활성화 상태인 커넥션만 추출한다.
	 * 양수가 아닌 경우 비활성화된 시간으로는 풀에서 제거되지 않는다.
	 * 시간 단위는 1/1000초이다.
	 */
	private final long minEvictableIdleTimeMillis;
	/**
	 * true일 경우 비활성화 커넥션을 추출할 때 커넥션이 유효한지의 여부를 검사해서 유효하지 않은 커넥션은 풀에서 제거한다.
	 */
	private final boolean testWhileIdle;

	private ConnectionPoolManager() {
		driverClassName = Config.DB_DRIVER_CLASS_NAME;
		url = Config.DB_URL;
		userName = Config.DB_USER_NAME;
		password = Config.DB_PASSWORD;
		defaultReadOnly = false;
		defaultAutoCommit = false;
		maxActive = 10;
		maxWait = 10000L;
		minIdle = 10;
		maxIdle = 50;
		whenExhaustedAction = 1;
		testOnBorrow = false;
		testOnReturn = false;
		timeBetweenEvictionRunsMillis = 600000L;
		numTestsPerEvictionRun = 50;
		minEvictableIdleTimeMillis = 3600000L;
		testWhileIdle = false;

		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException cnfe) {
			LOGGER.error("", cnfe);
			System.exit(0);
		}

		connectionPool.setMaxActive(maxActive);
		connectionPool.setMinIdle(minIdle);
		connectionPool.setMaxIdle(maxIdle);
		connectionPool.setWhenExhaustedAction(whenExhaustedAction);
		connectionPool.setMaxWait(maxWait);
		connectionPool.setTestOnBorrow(testOnBorrow);
		connectionPool.setTestOnReturn(testOnReturn);
		connectionPool.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		connectionPool.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		connectionPool.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		connectionPool.setTestWhileIdle(testWhileIdle);
		//
		connectionFactory = new DriverManagerConnectionFactory(url, userName, password);
		poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
				connectionPool, null, null, defaultReadOnly, defaultAutoCommit);
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver(POOL_DRIVER_URL);
			driver.registerPool(POOL_NAME, poolableConnectionFactory.getPool());
		} catch (ClassNotFoundException e) {
			LOGGER.error("", e);
			System.exit(0);
		} catch (SQLException e) {
			LOGGER.error("", e);
			System.exit(0);
		}
	}

	public static ConnectionPoolManager getInstance() {
		return INSTANCE;
	}

	public Connection getSession() throws SQLException {
		return DriverManager.getConnection(POOL_URL);
	}

	public void returnObject(Connection conn) {
		try {
			connectionPool.returnObject(conn);
		} catch (Exception e) {
			LOGGER.error("", e);
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					LOGGER.error("", ex);
				}
			}
		}
	}

	public void close() {
		if (connectionPool != null) {
			try {
				connectionPool.close();
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
	}
}
