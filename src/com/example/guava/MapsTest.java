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
 * com.google.common.collect.Maps 클래스 관련
 */
public class MapsTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(MapsTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Maps 만들기");
		
		LOGGER.debug("guava 형태의 Map 생성");
		
		Map<String, Object> hashMap = Maps.newHashMap();
		hashMap.put("aaa", "가가가");
		hashMap.put("bbb", "나나나");
		LOGGER.debug(hashMap.toString());
		
		// 멀티스레드에 안전한 리스트 생성
		Map<String, Object> concurrentMap = Maps.newConcurrentMap();
		concurrentMap.put("aaa", "가가가");
		concurrentMap.put("bbb", "나나나");
		LOGGER.debug(concurrentMap.toString());
		
		// enum을 키로 가지는 맵 생성
		EnumMap<SomeEnum, Integer> enumMap = Maps.newEnumMap(SomeEnum.class);
		enumMap.put(SomeEnum.SOME_INSTANCE, 1);
		LOGGER.debug(enumMap.toString());
		
		int expectedSize = 10;
		Maps.newHashMapWithExpectedSize(expectedSize);
		
		// 참조가 없을경우 버려지는 맵 생성
		Maps.newIdentityHashMap();
		
		// 순번이 있는 맵 생성
		Maps.newLinkedHashMap();
		
		// 추가시 정렬이 되는 맵 생성
		Maps.newTreeMap();
	}
	
	@Test
	public void test001() {
		LOGGER.debug("List -> Map");
		
		LOGGER.debug("리스트를 Map으로 변경");
		
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
		
		LOGGER.debug("Map에서 해당 조건에 해당하는 Map 만들기");
		
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
						return member.age == 33; // 나이가 33인 유저만
					}
				});
		LOGGER.debug(filterMap.toString());
	}
	
	public enum SomeEnum { SOME_INSTANCE }
}
