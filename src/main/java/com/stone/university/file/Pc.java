package com.stone.university.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Pc {

	private static void readLineCC2(String fileName) {

		LinkedList<String> policy = readPolicy("/Users/Peter/Downloads/pp");

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {

				String[] tl = tempString.split(",");
				String pl = null;
				if (line > 1) {
					for (String p : policy) {
						if (p.startsWith(tl[1].trim())) {
							pl = p;
							break;
						}
					}
				}

				if (pl == null) {
					System.out.println(tempString + ",null");
				} else {
					String[] tt = pl.split(",");
					if (tt[1].trim().equals(tl[0].trim())) {
						System.out.println(tempString + ",2");
					} else if (tt[2].trim().equals(tl[0].trim())) {
						System.out.println(tempString + ",1");
					} else {
						System.out.println(tempString + ",null");
					}
				}

				// handleLine(tempString);
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

	private static void readLine(String fileName) {

		LinkedList<String> policy = readPolicy("/Users/Peter/Downloads/pp");

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {

				String[] tl = tempString.split(",");
				String pl = null;
				if (line > 1) {
					for (String p : policy) {
						if (p.startsWith(tl[1].trim())) {
							pl = p;
							break;
						}
					}
				}

				if (pl == null) {
					System.out.println(tempString + ",null");
				} else {
					String[] tt = pl.split(",");
					if (tt[1].trim().equals(tl[0].trim())) {
						System.out.println(tempString + ",2");
					} else if (tt[2].trim().equals(tl[0].trim())) {
						System.out.println(tempString + ",1");
					} else {
						System.out.println(tempString + ",null");
					}
				}

				// handleLine(tempString);
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

	private static LinkedList<String> readPolicy(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		String[] result = new String[3];
		LinkedList<String> link = new LinkedList<String>();
		try {

			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				// handleLine(tempString);
				link.add(tempString.trim());

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
		return link;
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
		String fileName = "/Users/Peter/Downloads/cc";
		readLine(fileName);

	}

}
