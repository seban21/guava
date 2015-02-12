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
 * com.google.common.collect.Lists 클래스 관련
 */
public class ListsTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(ListsTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Lists 만들기");
		
		LOGGER.debug("guava 형태의 List 생성");
		
		List<Integer> arrayList0 = Lists.newArrayList();
		arrayList0.add(1);
		arrayList0.add(2);
		LOGGER.debug(arrayList0.toString());
		
		// 리스트 생성시 value 추가하기
		List<Integer> arrayList1 = Lists.newArrayList(3, 4);
		LOGGER.debug(arrayList1.toString());
		
		// 멀티스레드에 안전한 리스트 생성
		Lists.newCopyOnWriteArrayList();
		
		Lists.newLinkedList();
	}
	
	@Test
	public void test001() {
		LOGGER.debug("Lists filter");
		
		List<Member> members = dao.listAll();
		
		LOGGER.debug("List에서 해당 조건에 해당하는 List 만들기");
		
		List<Member> filterList = Lists.newArrayList(Iterables.filter(members,
				new Predicate<Member>() {

					@Override
					public boolean apply(Member input) {
						if (input == null) {
							return false;
						}
						return input.age == 33; // 나이가 33인 유저만
					}
				}));
		
		LOGGER.debug(filterList.toString());
	}
	
	@Test
	public void test002() {
		LOGGER.debug("Lists transform");
		
		List<Member> members = dao.listAll();
		
		LOGGER.debug("List의 구조 변경");
		
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
		
		LOGGER.debug("List를 역순으로 변경");
		
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
		
		LOGGER.debug("List를 해당 크기만큼 분할");
		
		int partitionSize = 2;
		
		List<String> ids = dao.listIdAll();
		
		List<List<String>> partitionIds = Lists.partition(ids, partitionSize);
		for (List<String> partition : partitionIds) {
			LOGGER.debug(partition);
		}
	}
}
