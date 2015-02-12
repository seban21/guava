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
public class MemberDao extends SqlSession {
	private final static Logger LOGGER = Logger.getLogger(MemberDao.class);
	
	public void delete() {
		Connection conn = getSession();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM member");
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("" , e);
		} finally {
			close(ps);
			commit(conn);
			returnObject(conn);
		}
	}
	
	public int insert(Connection conn, String id, String name, String password, int age) {
		String sql = "INSERT INTO member(id, name, password, age, r_dt) VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setInt(4, age);
			return ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("" , e);
		} finally {
			close(ps);
		}
		return 0;
	}
	
	public List<Member> listAll() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		List<Member> list = Lists.newArrayList();
		try {
			conn = getSession();
			s = conn.createStatement();
			rs = s.executeQuery("SELECT id, name, password, age FROM member");
			while (rs.next()) {
				list.add(new Member(rs.getString("id"), rs.getString("name"), rs
						.getString("password"), rs.getInt("age")));
			}
		} catch (SQLException e) {
			LOGGER.error("" , e);
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
			rs = s.executeQuery("SELECT id FROM member");
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
	
	public String get(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = getSession();
			ps = conn.prepareStatement("SELECT id FROM member WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString("id");
			}
		} catch (SQLException e) {
			LOGGER.error("" , e);
		} finally {
			close(ps, rs);
			returnObject(conn);
		}
		return result;
	}
}
