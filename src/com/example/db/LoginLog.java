package com.example.db;

/**
 * @author gimbyeongsu
 *
 */
public class LoginLog {
	public int no;
	public String id;
	public String name;
	public int age;

	public LoginLog(int no, String id, String name, int age) {
		this.no = no;
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "LoginLog [no=" + no + ", id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
