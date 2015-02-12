package com.example.guava;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.example.db.Member;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author gimbyeongsu
 * com.google.common.cache 패키지 관련
 */
public class InMemoryCacheTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(InMemoryCacheTest.class);
	
	private final static int CACHE_ITEM_SIZE = 10;
	private final static int CACHE_DURATION_SEC = 3; // cache 업데이트 간격 시간의 초
	
	@Test
	public void test000() {
		LOGGER.debug("Cache");

		LOGGER.debug("키값 파라메타를 넘기는 형태의 cache");
		
		LoadingCache<String, String> cacheMember = CacheBuilder.newBuilder()
				.maximumSize(CACHE_ITEM_SIZE)
				.refreshAfterWrite(CACHE_DURATION_SEC, TimeUnit.SECONDS)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String id) {
						LOGGER.debug("id :" + id + " cache");
						return dao.get(id);
					}
				});

		try {
			String result0 = cacheMember.get("aaa");
			LOGGER.debug("result0:" + result0);
			String result1 = cacheMember.get("aaa");
			LOGGER.debug("result1:" + result1);
		} catch (ExecutionException e) {
			LOGGER.error("", e);
		}
	}
	
	
	@Test
	public void test001() {
		LOGGER.debug("Cache");
		
		LOGGER.debug("키가 없는 형태의 cache (NONE문자열을 공통적으로 넘긴다.)");
		
		LoadingCache<String, List<Member>> cacheMembers = CacheBuilder
				.newBuilder().maximumSize(CACHE_ITEM_SIZE)
				.refreshAfterWrite(CACHE_DURATION_SEC, TimeUnit.SECONDS)
				.build(new CacheLoader<String, List<Member>>() {
					@Override
					public List<Member> load(String none) {
						LOGGER.debug("members cache");
						return dao.listAll();
					}
				});
		
		try {
			List<Member> result0 = cacheMembers.get("NONE");
			LOGGER.debug("result0:" + result0.size());
			List<Member> result1 = cacheMembers.get("NONE");
			LOGGER.debug("result1:" + result1.size());
		} catch (ExecutionException e) {
			LOGGER.error("", e);
		}
	}
	
	@Test
	public void test002() {
		LOGGER.debug("Cache");
		
		LOGGER.debug("cache 업데이트 간격 확인");
		
		LoadingCache<String, List<Member>> cacheMembers = CacheBuilder
				.newBuilder().maximumSize(CACHE_ITEM_SIZE)
				.refreshAfterWrite(CACHE_DURATION_SEC, TimeUnit.SECONDS)
				.build(new CacheLoader<String, List<Member>>() {
					@Override
					public List<Member> load(String none) {
						LOGGER.debug("members cache");
						return dao.listAll();
					}
				});
		
		for (int i = 0; i < 10; ++i) {
			try {
				List<Member> result = cacheMembers.get("NONE");
				LOGGER.debug("result:" + result.size());
			} catch (ExecutionException e) {
				LOGGER.error("", e);
			}
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
			}
		}
	}
}
