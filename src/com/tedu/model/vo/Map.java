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
		// TODO: �����ͼ����(txt),��0��1��2״̬��
		mapPath = file;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(mapPath));
			String tempString;
			tempString = reader.readLine();
			String[] size = tempString.split(","); // ����
			map = new int[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
			
			int i, j;
			i = 0;
			// ���ļ���ȡ��map������
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
		
		//����Ӧ��ͼƬ�Ž�����ȥ
		showMap(map); // ���ǲ���Ҫ���������Ӧ����һ���ַ�����
	}
	
	public void showMap(int[][] state) {
		
	}
	public void chooseImg(int sta) {
		//TODO:����0��1��2״̬�����ʾ��Ӧ��img
		
	}
}
