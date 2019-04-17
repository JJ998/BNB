package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;

public class BaseMap extends SuperElement{
	private URL mapPath = BaseMap.class.getClassLoader().getResource("com/tedu/pro/map/level1.pro");
    private static int[][] map;//��Ŷ�Ӧλ���ϵ�0��1��2
	private int rows;//��ͼ��Ԫ������
	private int cols;//��ͼ��Ԫ������
	private ImageIcon img; //����һ�ε�һ�ſؼ�ͼƬ
	private int state;//�ؼ���״̬��0��1��2��
	private List<String> gameList;
    private List<String> Prop_map;

	public BaseMap(String file) throws IOException {
		mapPath = BaseMap.class.getClassLoader().getResource(file);
//		System.out.println(mapPath);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(mapPath.toString().split("file:")[1]));
			String tempString;
			tempString = reader.readLine();
			String[] size = tempString.split(","); // ����
			map = new int[Integer.parseInt(size[0])][Integer.parseInt(size[1])];

			int i, j;
			i = 0;
			j = 0;
			// ���ļ���ȡ��map������
			while ((tempString = reader.readLine()) != null) {
				String[] numbers = tempString.split(",");
				j = 0;
				for(String num : numbers)
					map[i][j++] = Integer.parseInt(num);
				i++;
			}

            cols = i;
            rows = j;
			reader.close();
            //TODO:��ȡ��Ӧλ�õ�״̬
            //������״̬��������ɶ�Ӧ�����
            //���� ��ȡ��λ�õ���� ���ַ�������001=enemyA,enemyA,20,40,40,40,100
            Integer x, y;
            gameList = new ArrayList<>();

            for (i = 0; i < cols; i++) {
                for (j = 0; j < rows; j++) {
                    // enemjA,enemjA,20,40,40,40,100
                    int state = map[i][j];
                    String s = randomImg(state);
                    y = i * 45;
                    x = j * 45;
                    String tempStr = s + ",puzzle," + x.toString() + "," + y.toString() + ",45,45,0";
//                    System.out.println(tempStr);
                    if (state > 0) gameList.add(tempStr);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int[][] getMap() {
        return map;
    }

	public List<String> getGameList() {
		return gameList;
	}

    public List<String> getPropList() {
//      ����ӵ���
        Prop_map = new ArrayList<>();
        int i, j;
        Integer x, y;
        for (i = 0; i < cols; i++) {
            for (j = 0; j < rows; j++) {
                if (map[i][j] != 2) continue;
                String haveProp = Prop.randomProp();
                if (!haveProp.equals("nothing")) {
                    x = j * 45;
                    y = i * 45;
                    String temp_str = haveProp + "," + x.toString() + "," + y.toString();
                    Prop_map.add(temp_str);
                }
            }
        }
        return Prop_map;
    }
	
	public String randomImg(int state) {
		Random r=new Random();
		switch (state) {
//		��
		case 0:
			return null;
//		���ܱ�ը�����
		case 1:
			int a=r.nextInt(5)+1;
			switch(a) {
			case 1:
				return "fence";
			case 2:
				return "tree";
			case 3:
				return "yellowHouse";
			case 4:
				return "blueHouse";
                default:
                    return "smallBush";
			}
//		���Ա�ը�����
		case 2:
			a=r.nextInt(6)+1;
			switch(a) {
			case 1:
				return "blueBox";
			case 2:
				return "lightblueBox";
			case 3:
				return "orangeBox";
			case 4:
				return "redBox";
			case 5:
				return "woodenBox";
                default:
				return "trafficCones";
			}
		default:
			return null;
		}
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO:�ڵ�ͼ����ʾ�ؼ�
		g.drawImage(img.getImage(), getX(), getY(),45, 45, null);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	

	static boolean haveBarrier(int x, int y) {
		return (map[y][x] > 0);
//		return false;
	}

}
