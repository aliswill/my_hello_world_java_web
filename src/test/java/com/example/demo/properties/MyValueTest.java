package com.example.demo.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyValueTest {

	
	@Test
	@DisplayName("測試加法")
	void test() {
		int result = add(1, 2);
		Assertions.assertEquals(4, result, "add 方法錯誤");
	}
	
	//假設有這個業務邏輯要測試
	int add(int i, int j) {
		return i+j;
	}
	
}
