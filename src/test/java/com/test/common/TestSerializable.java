package com.test.common;

import org.junit.Test;

import com.test.entity.Hello;

public class TestSerializable 
{
	@Test
	public void testSerializable()
	{
		Hello hello = new Hello();
		hello.setCount(1);
		hello.setMoney(12.253);
		hello.setMsg("hello world");
		
		System.out.println(hello);
	}
}
