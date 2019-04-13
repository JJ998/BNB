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
	private int map[][];//��Ŷ�Ӧλ���ϵ�0��1��2
	private int rows;//��ͼ��Ԫ������
	private int cols;//��ͼ��Ԫ������
	private String mapPuzzlePath[];//���ÿһ����Ԫ���ŵ�ͼƬ�ĵ�ַ
	private ImageIcon img; //����һ�ε�һ�ſؼ�ͼƬ
	private int state;//�ؼ���״̬��0��1��2��
	private List<String> gameList;
		
//	����һ��Ԫ��
	/*public BaseMap(int state,int x,int y,int w,int h,int time,ImageIcon img) {
		super(x,y,w,h);
		this.setImg(img);
		this.setState(state);
	}*/

	public BaseMap(String file) throws IOException {
		mapPath = BaseMap.class.getClassLoader().getResource(file);
		System.out.println(mapPath);
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
			cols = i-1;
			rows = j-1;
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//TODO:��ȡ��Ӧλ�õ�״̬
		//������״̬��������ɶ�Ӧ�����
		//���� ��ȡ��λ�õ���� ���ַ�������001=enemyA,enemyA,20,40,40,40,100
		int i,j,k;
		Integer x, y;
		int th=0;
		k=0;
		gameList = new ArrayList<String>();
		for(i=0;i<cols;i++) {

			for(j=0;j<rows;j++) {
				// enemjA,enemjA,20,40,40,40,100
				int state = map[i][j];
				String s=randomImg(state);
				/*mapPuzzlePath[k] = th +"="+ s +",enemj,"+ i*45 +","+ j*45 + "45,45,0";
				th++;
				System.out.println(mapPuzzlePath[k]+"\n");
				img = ElementLoad.getElementLoad().getBaseMap().get(s);*/
				x = i*45; y = j * 45;
				String tempStr = s +",enemy,"+ x.toString() +","+ y.toString() + ",45,45,0";
				System.out.println(tempStr);
				gameList.add(tempStr);
			}
		}
	}

	public List<String> getGameList() {
		return gameList;
	}

//	�����ļ�����[��ͼ����(txt),��0��1��2״̬]����Ϊ��Ԫ������ͼƬ��
	/*public void createBaseMap(String file) throws IOException {
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
			j = 0;
			// ���ļ���ȡ��map������
			while ((tempString = reader.readLine()) != null) {
				String[] numbers = tempString.split(",");
				j = 0;
				for(String num : numbers)
					map[i][j++] = Integer.parseInt(num);
				i++;
            }
			cols = i-1;
			rows = j-1;
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//TODO:��ȡ��Ӧλ�õ�״̬
		//������״̬��������ɶ�Ӧ�����
		//���� ��ȡ��λ�õ���� ���ַ�������001=enemyA,enemyA,20,40,40,40,100
		int x,y,k;
		int th=0;
		k=0;
		BaseMap puzzle=null;
		for(x=0;x<cols;x++) {
			
			for(y=0;y<rows;y++) {			
				
				int state = map[x][y];
				String s=randomImg(state);
				mapPuzzlePath[k] = th +"="+ s +",enemy,"+ x*45 +","+ y*45 + "45,45,0";
				th++;
				System.out.println(mapPuzzlePath[k]+"\n");
				img = ElementLoad.getElementLoad().getBaseMap().get(s);
				puzzle=new BaseMap(state,x,y,45,45,0,img);
			}
		}
		
	}*/
	
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
			case 5:
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
			case 6:
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
	
	
}
