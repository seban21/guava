package com.example.guava;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.example.db.Member;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

/**
 * @author gimbyeongsu
 *
 */
public class MapsTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(MapsTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Maps 만들기");
		
		Map<String, Object> hashMap = Maps.newHashMap();
		hashMap.put("aaa", "가가가");
		hashMap.put("bbb", "나나나");
		LOGGER.debug(hashMap.toString());
		
		Map<String, Object> concurrentMap = Maps.newConcurrentMap();
		concurrentMap.put("aaa", "가가가");
		concurrentMap.put("bbb", "나나나");
		LOGGER.debug(concurrentMap.toString());
		
		EnumMap<SomeEnum, Integer> enumMap = Maps.newEnumMap(SomeEnum.class);
		enumMap.put(SomeEnum.SOME_INSTANCE, 1);
		LOGGER.debug(enumMap.toString());
		
		int expectedSize = 10;
		Maps.newHashMapWithExpectedSize(expectedSize);
		
		Maps.newIdentityHashMap();
		Maps.newLinkedHashMap();
		Maps.newTreeMap();
	}
	
	@Test
	public void test001() {
		LOGGER.debug("List -> Map");
		
		List<Member> members = dao.listAll();
		Map<String, Member> map = Maps.uniqueIndex(members, new Function<Member, String>() {

			@Override
			public String apply(Member input) {
				return input.id;
			}
		});
		
		LOGGER.debug(map.get("aaa").toString());
	}
	
	@Test
	public void test002() {
		LOGGER.debug("Map filter");
		
		List<Member> members = dao.listAll();
		
		Map<String, Member> map = Maps.uniqueIndex(members, new Function<Member, String>() {
			@Override
			public String apply(Member input) {
				return input.id;
			}
		});
		
		Map<String, Member> filterMap = Maps.filterEntries(map,
				new Predicate<Map.Entry<String, Member>>() {
					@Override
					public boolean apply(Entry<String, Member> input) {
						Member member = input.getValue();
						if (member == null) {
							return false;
						}
						return member.age == 33;
					}
				});
		LOGGER.debug(filterMap.toString());
	}
	
	public enum SomeEnum { SOME_INSTANCE }
}
