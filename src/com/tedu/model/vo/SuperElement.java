package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class SuperElement {
	
	private int hp;//Ѫ��
	private int x;
	private int y;
	private int w;
	private int h;
	private boolean visible;//Ĭ��Ϊtrue ������
	
//	jvm��ÿ���඼��Ĭ������һ��Ĭ���޲����Ĺ��췽��
//	���ǣ���������ֶ�д��һ�����췽����������ʲô���죨�в������޲�������jvm�����������Ĭ�Ϲ���
//	һ����Ϊ���࣬������������죬���дһ���޲������죬��ֹ�̳г���
	public SuperElement(int x,int y,int w,int h) 
	{
		this.hp=0;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		visible=true;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	protected SuperElement()
	{
		
	}
	
	
//	��Щ�����ǿ�����˳��ִ�еġ�ģ��ģʽ
	public void update()
	{
		move();
//		destroy();
	}
	
//	����ѡ��this pk���� ���ǲ���pk����
	public boolean gamepk(SuperElement se)
	{
		Rectangle r1=new Rectangle(x,y,w,h);
		Rectangle r2=new Rectangle(se.x,se.y,se.w,se.h);
		return r1.intersects(r2);//����н���������true
	}
	
	public boolean gamepk(SuperElement se1,SuperElement se2)
	{
		Rectangle r1=new Rectangle(se1.x,se1.y,se1.w,se1.h);
		Rectangle r2=new Rectangle(se2.x,se2.y,se2.w,se2.h);
		return r1.intersects(r2);//����н���������true
	}
	
	public abstract void showElement(Graphics g);
	public abstract void move();
	public abstract void destroy();
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
