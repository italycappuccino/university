package com.stone.test.spring;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.stone.university.imooc.annotation.BeanAnnotation;
import com.stone.university.imooc.annotation.CupWater;
import com.stone.university.imooc.annotation.FileResource;
import com.stone.university.imooc.annotation.FriendService;

//@RunWith(BlockJUnit4ClassRunner.class)

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JunitTestSpring extends AbstractJUnit4SpringContextTests {

	@Resource
	FriendService friendService;

	@Test
	public void work() {

		BeanAnnotation bean = (BeanAnnotation) applicationContext
				.getBean("beanAnnotation");
		bean.say();

		friendService.sayHello();

		CupWater water = (CupWater) applicationContext.getBean("cupWater");
		water.water();

		FileResource fileResource = (FileResource) applicationContext
				.getBean("fileResource");
		try {
			// fileResource.readResource("classpath:config/file1.txt");
			fileResource.readResource("url:https://www.baidu.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
