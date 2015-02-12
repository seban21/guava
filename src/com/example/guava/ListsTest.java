package com.example.guava;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.example.db.Member;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 *
 */
public class ListsTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(ListsTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Lists 만들기");
		
		List<Integer> arrayList0 = Lists.newArrayList();
		arrayList0.add(1);
		arrayList0.add(2);
		LOGGER.debug(arrayList0.toString());
		
		List<Integer> arrayList1 = Lists.newArrayList(3, 4);
		LOGGER.debug(arrayList1.toString());
		
		Lists.newCopyOnWriteArrayList();
		
		Lists.newLinkedList();
	}
	
	@Test
	public void test001() {
		LOGGER.debug("Lists Filter");
		
		List<Member> members = dao.listAll();
		
		List<Member> filterList = Lists.newArrayList(Iterables.filter(members,
				new Predicate<Member>() {

					@Override
					public boolean apply(Member input) {
						if (input == null) {
							return false;
						}
						return input.age == 33;
					}
				}));
		
		LOGGER.debug(filterList.toString());
	}
	
	@Test
	public void test002() {
		LOGGER.debug("Lists Transform");
		
		List<Member> members = dao.listAll();
		
		List<String> transformList = Lists.transform(members, new Function<Member, String>() {

			@Override
			public String apply(Member input) {
				return input.name;
			}
		});
		
		LOGGER.debug(transformList.toString());
	}
	
	@Test
	public void test003() {
		LOGGER.debug("Lists reverse");
		
		List<String> ids = dao.listIdAll();
		for (String id : ids) {
			LOGGER.debug(id);
		}
		LOGGER.debug("");
		List<String> reverseMembers = Lists.reverse(ids);
		for (String id : reverseMembers) {
			LOGGER.debug(id);
		}
	}
	
	@Test
	public void test004() {
		LOGGER.debug("Lists partition");
		
		List<String> ids = dao.listIdAll();
		
		List<List<String>> partitionIds = Lists.partition(ids, 2);
		for (List<String> partition : partitionIds) {
			LOGGER.debug(partition);
		}
	}
}
