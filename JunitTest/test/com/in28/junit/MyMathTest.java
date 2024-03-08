package com.in28.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
class MyMathTest {
	MyMath math = new MyMath();
	@Test
	void testForThreeElement() {
		assertEquals(6, math.calculateSum(new int[] {1,2,3}));
	}
	
	@Test
	void testForZeroElement() {
		assertEquals(0, math.calculateSum(new int[] {}));
	}
	
	List<String> todos = Arrays.asList("a","b","c");
	@Test
	void test() {
		boolean test = todos.contains("a");
	    
	    assertTrue(test); // 실패메시지 추가 : assertTrue("메시지",test);
	    
	    assertEquals(1,1); // 다양한 타입 가능
	    assertEquals("hello", "hello");
	    
	    assertArrayEquals(new int[]{1,2},new int[]{2,1}); // 실패 시 Junit의 실패 메시지창 확인해 자세한 정보 확인
	}
	
	
}
 