package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 *
 */
public class LoginLogDao extends SqlSession {
	private final static Logger LOGGER = Logger.getLogger(LoginLogDao.class);
	
	public void delete() {
		Connection conn = getSession();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM login_log");
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("", e);
		} finally {
			close(ps);
			commit(conn);
			returnObject(conn);
		}
	}
	
	public int insert(Connection conn, String id, String name, int age) {
		String sql = "INSERT INTO login_log(id, name, age, r_dt) VALUES(?, ?, ?, CURRENT_TIMESTAMP)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			return ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("", e);
		} finally {
			close(ps);
		}
		return 0;
	}
	
	public List<LoginLog> listAll() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		List<LoginLog> list = Lists.newArrayList();
		try {
			conn = getSession();
			s = conn.createStatement();
			rs = s.executeQuery("SELECT no, id, name, age FROM login_log");
			while (rs.next()) {
				list.add(new LoginLog(rs.getInt("no"), rs.getString("id"), rs.getString("name"), rs
						.getInt("age")));
			}
		} catch (SQLException e) {
			LOGGER.error("", e);
		} finally {
			close(s, rs);
			returnObject(conn);
		}
		return list;
	}
	
	public List<String> listIdAll() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		List<String> list = Lists.newArrayList();
		try {
			conn = getSession();
			s = conn.createStatement();
			rs = s.executeQuery("SELECT id FROM login_log");
			while (rs.next()) {
				list.add(rs.getString("id"));
			}
		} catch (SQLException e) {
			LOGGER.error("" , e);
		} finally {
			close(s, rs);
			returnObject(conn);
		}
		return list;
	}
}
