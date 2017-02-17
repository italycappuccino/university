package com.stone.university.file;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 类ReadTaobaoLog.java的实现描述：TODO 类实现描述
 * 
 * @author liulei 2015年7月30日 下午4:27:22
 */
public class ReadTaobaoLog {

	private static void readLine(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				handleLine(tempString);
				line++;
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	static void handleLine(String line) {

		if (line.contains("耗时=")) {
			System.out.println(line);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Noname2-taobao.txt
		// Noname3-taobao.txt
		String fileName = "E:/taobao/Noname3-taobao.txt";
		readLine(fileName);
	}
}
