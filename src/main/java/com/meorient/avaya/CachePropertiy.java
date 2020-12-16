package com.meorient.avaya;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CachePropertiy {
	
	public static void set(String key , String value, String time){  // 正常的写入操作
		// 生成文件夹和缓存文件
		File fileDir = new File("C:/avaya");
		if(!fileDir.exists()) fileDir.mkdir();
		File file = new File("C:/avaya/avayaCache.txt");

		FileWriter fw = null;

		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			fw.write(key + "," + value + ": " + time + "\r\n");
			fw.flush();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("写入出错");
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
			}
		}
	}
	
	public static String get(String key) {
		// 生成文件夹和缓存文件
		File fileDir = new File("C:/avaya");
		if(!fileDir.exists()) fileDir.mkdir();
		File file = new File("C:/avaya/avayaCache.txt");
		if(!file.exists()) return null;

		FileReader fr = null;
		String line = "";
		int temp;
		boolean find = false;
		try {
			fr = new FileReader(file);


			while(true) {
				temp = fr.read();

				if((char)temp == ',') {
					if(line.equals(key)) {
						find = true;
					}
					line  = "";
					continue;
				}
				if(temp == 58) {									// 13对应 归位键  58对应 ：
					if(find) {
						System.out.println("读取到了value : " + line);
						return line;
					}
					fr.read();
					line = "";
					continue;
				}
				if(temp == -1) {
					System.out.println("没找到");
					break;
				}
				line += String.valueOf((char)temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
		return "";
}

	// for test
	public static void main(String[] args) {
		//set("18368445445","9018366664871");
		get("13134439827");
	}
}
