package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * @author gimbyeongsu
 *
 */
public class SqlSession {
	private final static Logger LOGGER = Logger.getLogger(SqlSession.class);
	private final ConnectionPoolManager cpm = ConnectionPoolManager.getInstance();

	public SqlSession() {
	}

	public Connection getSession() {
		try {
			return cpm.getSession();
		} catch (SQLException e) {
			LOGGER.error("" , e);
		}
		return null;
	}

	public void returnObject(Connection conn) {
		cpm.returnObject(conn);
	}

	public void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				LOGGER.error("" , e);
			}
		}
	}

	protected void close(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {}
		}
	}

	protected void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
	}

	protected void close(PreparedStatement ps, ResultSet rs) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
	}

	protected void close(Statement s, ResultSet rs) {
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
	}

	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			LOGGER.error("" , e);
		}
	}
}
