package com.stone.university.imooc.binary;

public class Convert {

	public static void main(String[] args) {
		byte[] arr = Convert.int2byte(8143);

		System.err.println(arr[0] + "\t" + arr[1] + "\t" + arr[2] + "\t"
				+ arr[3]);

		System.out.println(Convert.byte2int(arr));

		String abc = "asdoisa";

		byte[] brr = abc.getBytes();
		String des = new String(brr);
		System.out.println(des);

		int b = 1000;

		int c = ~b;

		System.err.println(c);

		System.out.println(b >> 3);

		int a = 28;
		int d = a & 1;

		System.out.println(d);

		int e = 25 * 4;

		int f = 25 << 2;

		System.err.println("e=" + e);
		System.err.println("f=" + f);

		// System.err.println(Integer.toBinaryString(arr[0]) + "\t"
		// + Integer.toBinaryString(arr[1]) + "\t"
		// + Integer.toBinaryString(arr[2]) + "\t"
		// + Integer.toBinaryString(arr[3]));

	}

	public static int byte2int(byte[] arr) {
		// int rs0 = (int) ((arr[0] & 0xff) << 0 * 8);
		// int rs1 = (int) ((arr[1] & 0xff) << 1 * 8);
		// int rs2 = (int) ((arr[2] & 0xff) << 2 * 8);
		// int rs3 = (int) ((arr[3] & 0xff) << 3 * 8);
		// return rs0 + rs1 + rs2 + rs3;
		//
		int rs = 0;
		for (int i = 0; i < arr.length; i++) {
			rs += (int) ((arr[i] & 0xff) << i * 8);
		}
		return rs;
	}

	public static byte[] int2byte(int id) {
		byte[] arr = new byte[4];
		// arr[0] = (byte) ((int) (id >> 0 * 8) & 0xff);
		// arr[1] = (byte) ((int) (id >> 1 * 8) & 0xff);
		// arr[2] = (byte) ((int) (id >> 2 * 8) & 0xff);
		// arr[3] = (byte) ((int) (id >> 3 * 8) & 0xff);

		for (int i = 0; i < 4; i++) {
			arr[i] = (byte) ((int) (id >> i * 8) & 0xff);
		}

		return arr;
	}

}
