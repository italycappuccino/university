package com.stone.university.imooc.annotation;

import com.stone.university.imooc.annotation.Description;

public class Student {

	@Description(desc = "i am peter", author = "peter", age = 19)
	public void Abc() {
		System.out.println("===");
	}

	public static void main(String[] args) {

		new Student().Abc();
	}

}
