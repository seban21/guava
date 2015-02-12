package com.example.db;

import com.google.common.collect.ComparisonChain;

/**
 * @author gimbyeongsu
 *
 */
public class Member implements Comparable<Member> {
	public String id;
	public String name;
	public String password;
	public int age;
	
	public Member(String id, String name, String password, int age) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
	}
	
	@Override
	public int compareTo(Member o) {
		return ComparisonChain.start().compare(age, o.age).compare(id, o.id).result();
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age
				+ "]";
	}
}
