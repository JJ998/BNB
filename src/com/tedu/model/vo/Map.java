package com.tedu.model.vo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Map {
	private String mapPath = "../../../../../res/map/level1.txt";
	private int map[][];
	public Map(String file) throws IOException {
		// TODO: 读入地图数组(txt),得0，1，2状态。
		mapPath = file;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(mapPath));
			String tempString;
			tempString = reader.readLine();
			String[] size = tempString.split(","); // 行列
			map = new int[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
			
			int i, j;
			i = 0;
			// 将文件读取到map数组里
			while ((tempString = reader.readLine()) != null) {
				String[] numbers = tempString.split(",");
				j = 0;
				for(String num : numbers)
					map[i][j++] = Integer.parseInt(num);
				i++;
            }
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//将对应的图片放进（）去
		showMap(map); // 我们不是要根据数组对应生成一个字符串？
	}
	
	public void showMap(int[][] state) {
		
	}
	public void chooseImg(int sta) {
		//TODO:根据0，1，2状态随机显示对应的img
		
	}
}
