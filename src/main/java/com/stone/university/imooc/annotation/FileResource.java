package com.stone.university.imooc.annotation;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

public class FileResource implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void readResource(String location) throws IOException {
		Resource res = applicationContext.getResource(location);
		System.out.println(res.getFilename());
		System.out.println(res.contentLength());
	}

}
