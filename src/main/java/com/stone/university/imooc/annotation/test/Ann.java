package com.stone.university.imooc.annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.stone.university.imooc.annotation.Description;

public class Ann {

	public static void main(String[] args) {

		try {
			Class c = Class
					.forName("com.stone.university.imooc.annotation.Student");

			Method[] ms = c.getMethods();

			for (Method m : ms) {
				boolean isMExist = m.isAnnotationPresent(Description.class);
				if (isMExist) {
					Description d = (Description) m
							.getAnnotation(Description.class);
					System.out.println(d.desc());
					System.out.println(d.author());
					System.out.println(d.age());
				}
			}

			for (Method m : ms) {
				Annotation[] as = m.getAnnotations();
				for (Annotation a : as) {
					if (a instanceof Description) {
						Description d = (Description) a;
						System.out.println(d.desc());
						System.out.println(d.author());
						System.out.println(d.age());
					}
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
