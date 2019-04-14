package com.st.cart.domain;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		Cat c = new Cat("tom", 10);
		Cat c1 = new Cat("tom2", 30);
		Cat c2 = new Cat("tom2", 40);
		// {"name":"tom", "age":10}
		
		System.out.println(JSONObject.toJSONString(c));
		
		List<Cat> list = Arrays.asList(c,c1, c2);
		// [{"name":"tome", "age":10}, {"name":"tom1", "age":20}]
		System.out.println(JSONObject.toJSONString(list));
	}
}
