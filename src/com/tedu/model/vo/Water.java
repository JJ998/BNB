package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Water extends SuperElement{

	private ImageIcon img;
	private int cleanbox;//��������ˮ���µĸ����Ƿ�Ҫ�ı䣬Ҳ����box�ǲ��Ǳ����
	private int moveX;//����ͼ��
	//moveX 0����  1��ˮ��  2��ˮ��  3��ˮ��  4��ˮ��  5��ˮ��  6��ˮ��  7��ˮ��  8��ˮ��
	public Water(int x, int y, int w, int h,ImageIcon img,int moveX,int cleanbox) {
		super(x, y, w, h);
		this.img=img;
		this.moveX=moveX;
		this.cleanbox=cleanbox;
	}
	
	public static List<Water> createWater(int x,int y,int bomb_energe){
		//bomb_energe��ʾ������������ը������
		ArrayList<Water> waterlist=new ArrayList<Water>();
		ImageIcon img=new ImageIcon("img/Characters/ˮ��.png");
		
		int box1=0,box2=0;
		
		for(int d=0;d<4;d++){
			for(int BoomNum=0;BoomNum<bomb_energe;BoomNum++){
			switch(d)
			{
			case 0://��
				box1=Player.getCanwalkNum(x+1+BoomNum,y);
					if(box1!=0){
						if(box1==1)
							{BoomNum=bomb_energe;break;}
						if(box1==2)
							waterlist.add(new Water((x+1+BoomNum)*45,y*45,45,45,img,6,1));
					}else{
						box2=Player.getCanwalkNum(x+2+BoomNum,y);
						if(box2==1)
							waterlist.add(new Water((x+1+BoomNum)*45,y*45,45,45,img,6,0));
						if(box2==2)
							waterlist.add(new Water((x+1+BoomNum)*45,y*45,45,45,img,2,0));
					}
					break;
			case 1://��
				box1=Player.getCanwalkNum(x-1-BoomNum,y);
					if(box1!=0){
						if(box1==1)
						{BoomNum=bomb_energe;break;}
						if(box1==2)
							waterlist.add(new Water((x-1-BoomNum)*45,y*45,45,45,img,5,1));
					}else{
						box2=Player.getCanwalkNum(x-2-BoomNum,y);
						if(box2==1)
							waterlist.add(new Water((x-1-BoomNum)*45,y*45,45,45,img,5,0));
						if(box2==2)
							waterlist.add(new Water((x-1-BoomNum)*45,y*45,45,45,img,1,0));
					}
					break;
			case 2://��
				box1=Player.getCanwalkNum(x,y-1-BoomNum);
					if(box1!=0){
						if(box1==1)
						{BoomNum=bomb_energe;break;}
						if(box1==2)
							waterlist.add(new Water(x*45,(y-1-BoomNum)*45,45,45,img,8,1));
					}else{
						box2=Player.getCanwalkNum(x*45,(y-2-BoomNum)*45);
						if(box2==1)
							waterlist.add(new Water(x*45,(y-1-BoomNum)*45,45,45,img,8,0));
						if(box2==2)
							waterlist.add(new Water(x*45,(y-1-BoomNum)*45,45,45,img,4,0));
					}
					break;
			case 3://��
				box1=Player.getCanwalkNum(x,y+1+BoomNum);
					if(box1!=0){
						if(box1==1)
						{BoomNum=bomb_energe;break;}
						if(box1==2)
							waterlist.add(new Water(x*45,(y+1+BoomNum)*45,45,45,img,8,1));
					}else{
						box2=Player.getCanwalkNum(x*45,(y+2+BoomNum)*45);
						if(box2==1)
							waterlist.add(new Water(x*45,(y+1+BoomNum)*45,45,45,img,8,0));
						if(box2==2)
							waterlist.add(new Water(x*45,(y+1+BoomNum)*45,45,45,img,4,0));
					}
					break;
				}
			}
		}
		
		
		
	
		return waterlist;
	}
	
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
