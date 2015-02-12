package com.example.guava;

import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.example.db.Member;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * @author gimbyeongsu
 *
 */
public class OrderingTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(OrderingTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Ordering implements Comparable");
		
		List<Member> members = dao.listAll();
		List<Member> membersSort = Ordering.natural().sortedCopy(members);
		
		for (Member member : membersSort) {
			LOGGER.debug(member.toString());
		}
	}
	
	@Test
	public void test001() {
		LOGGER.debug("Ordering from");
		
		List<Member> members = dao.listAll();
		List<Member> membersSort = Ordering.from(new Comparator<Member>() {

			@Override
			public int compare(Member o1, Member o2) {
				return ComparisonChain.start().compare(o1.age, o2.age).result();
			}
		}).sortedCopy(members);

		for (Member member : membersSort) {
			LOGGER.debug(member.toString());
		}
	}
	
	@Test
	public void test002() {
		LOGGER.debug("Ordering max min");
		
		List<Member> members = dao.listAll();
		Member memberMax = Ordering.from(new Comparator<Member>() {

			@Override
			public int compare(Member o1, Member o2) {
				return ComparisonChain.start().compare(o1.age, o2.age).compare(o1.id, o2.id).result();
			}
		}).max(members);
		LOGGER.debug("max:" + memberMax);
		
		Member memberMin = Ordering.from(new Comparator<Member>() {

			@Override
			public int compare(Member o1, Member o2) {
				return ComparisonChain.start().compare(o1.age, o2.age).compare(o1.id, o2.id).result();
			}
		}).min(members);
		LOGGER.debug("min:" + memberMin);
	}
}
