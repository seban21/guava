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
		
		LOGGER.debug("List 객채의 Comparable구현 내용 compareTo 조건으로 정렬");
		
		List<Member> members = dao.listAll();
		List<Member> membersSort = Ordering.natural().sortedCopy(members);
		
		for (Member member : membersSort) {
			LOGGER.debug(member.toString());
		}
	}
	
	@Test
	public void test001() {
		LOGGER.debug("Ordering from");
		
		LOGGER.debug("List 객채의 Comparable를 사용하지 않고 정렬");
		
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
		
		LOGGER.debug("해당 List에서 조건에 해당하는 걸로 정렬후 max");
		List<Member> members = dao.listAll();
		Member memberMax = Ordering.from(new Comparator<Member>() {

			@Override
			public int compare(Member o1, Member o2) {
				return ComparisonChain.start().compare(o1.age, o2.age).compare(o1.id, o2.id).result();
			}
		}).max(members);
		LOGGER.debug("max:" + memberMax);
		
		LOGGER.debug("해당 List에서 조건에 해당하는 걸로 정렬후 min");
		Member memberMin = Ordering.from(new Comparator<Member>() {

			@Override
			public int compare(Member o1, Member o2) {
				return ComparisonChain.start().compare(o1.age, o2.age).compare(o1.id, o2.id).result();
			}
		}).min(members);
		LOGGER.debug("min:" + memberMin);
	}
}
