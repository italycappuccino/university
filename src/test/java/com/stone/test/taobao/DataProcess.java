package com.stone.test.taobao;

import org.apache.commons.lang.StringUtils;

public class DataProcess {
	public static final String EMPTY_FIELD_VALUE = "NULL";
	protected static final String DEFAULT_SEPARATOR = Character
			.toString((char) 0x01);
	private static final int INDEX_CHANNEL_BIZ_ID = 17;

	public static void main(String[] args) {
		String data = "";
		String[] items = StringUtils.splitPreserveAllTokens(data,
				DEFAULT_SEPARATOR);

		String insuredId = getStringField(items, INDEX_CHANNEL_BIZ_ID, true);
		System.out.println("column=" + insuredId);
		if (StringUtils.isBlank(insuredId)) {
			System.out.println("===================");
		} else {
			System.out.println("===========有东西。。。。");
		}

	}

	public static String getStringField(String[] fieldData, int index,
			boolean canBeEmpty) {
		if (index >= fieldData.length) {
			return canBeEmpty ? "" : null;
		}
		String data = fieldData[index];
		if (!canBeEmpty
				&& (StringUtils.isBlank(data) || EMPTY_FIELD_VALUE.equals(data))) {
			return null;
		}
		return EMPTY_FIELD_VALUE.equals(data) ? "" : data;
	}
}
