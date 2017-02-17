package com.stone.university.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 计算赢众通的借款日期是否超出招财宝的还款日期，员工贷
 * 
 * @author Peter
 *
 */
public class EmployeeLoan {

	public static void main(String[] args) throws ParseException {

		calDate(4, 22);

	}

	/**
	 * 计算本次借款到期日
	 * 
	 * @param month
	 *            本次借款的月份数量
	 * @param day
	 *            本次借款的天数
	 * @throws ParseException
	 */
	static void calDate(int month, int day) throws ParseException {
		/**
		 * 起息日：2015-07-03 还款日：2015-12-30
		 */

		// int month = 5;// add month
		// int day = 17;// add day

		String startDateStr = "2015-07-03";// 招财宝起息日
		String endDateStr = "2015-12-30";// 招财宝还款日

		System.out.println("【招财宝】起息日=" + startDateStr);
		System.out.println("【招财宝】还款日=" + endDateStr);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 赢众通本次借款的还款日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DAY_OF_MONTH, day);

		System.out.println("【赢众通】本次借款期限：" + month + "个月，" + day + "天");

		String desc = "";

		Date endDate = sdf.parse(endDateStr);// 招财宝还款日

		/*
		 * 日期比较时，以第一个日期为参照物，如果第一个日期小于第二个日期（即入参）则结果小于0
		 */
		int r = cal.getTime().compareTo(endDate);
		if (r == 0) {
			desc = "刚好是还款日期";
		} else if (r < 0) {
			long left = 0;
			left = (endDate.getTime() - cal.getTime().getTime())
					/ (24 * 60 * 60 * 1000);
			desc = "没有超出还款日，还剩" + left + "天";
		} else if (r > 0) {
			long over = 0;
			over = (cal.getTime().getTime() - endDate.getTime())
					/ (24 * 60 * 60 * 1000);
			desc = "超出还款日" + over + "天";
		}

		System.out.println("【赢众通】本次借款的本息到期日=" + sdf.format(cal.getTime())
				+ ", " + desc);
	}

}
