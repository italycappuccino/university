package com.stone.test.quartzPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTest {

	public static void main(String[] args) {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = new Date();
		String returnstr = DateFormat.format(d);

		TestJob job = new TestJob();
		String job_name = "11";
		try {
			String cronExpression = "0/2 * * * * ?";// 每2秒钟执行一次
			// 0/2 * * * * ?
			// 0 18 2 5 12 ? 2015/1

			System.out.println(returnstr + "【系统启动】");
			QuartzManager.addJob(job_name, job, cronExpression);

			QuartzManager.addJob("22", new TestJob(), cronExpression);

			// Thread.sleep(10000);
			// System.out.println("【修改时间】");
			// QuartzManager.modifyJobTime(job_name,"0/10 * * * * ?");
			// Thread.sleep(20000);
			// System.out.println("【移除定时】");
			// QuartzManager.removeJob(job_name);
			// Thread.sleep(10000);
			//
			// System.out.println("/n【添加定时任务】");
			// QuartzManager.addJob(job_name,job,"0/5 * * * * ?");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
