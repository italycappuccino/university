package com.stone.university.imooc.binary;

public class Binary {

	public static void main(String[] args) {

		System.out.println(Integer.toBinaryString(12));
		System.out.println(Integer.toHexString(12));
		System.out.println(Integer.toOctalString(12));

		System.out.println(Integer.parseInt("10010", 2));
		System.out.println(Integer.parseInt("70", 8));
		System.out.println(Integer.parseInt("F0", 16));

		int a = 0xff;

		System.out.println(a);
		System.out.println(Integer.toBinaryString(a));

		try {
			Foo foo = Foo.class.newInstance();
			foo.print();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Foo {

	void print() {
		System.out.println("Foo");
	}

}
