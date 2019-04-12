package com.tedu.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class SuperElement {
	
	private int hp;//血量
	private int x;
	private int y;
	private int w;
	private int h;
	private boolean visible;//默认为true 代表存活
	
//	jvm给每个类都会默认增加一个默认无参数的构造方法
//	但是，如果我们手动写了一个构造方法（无论是什么构造（有参数或无参数），jvm都不会再添加默认构造
//	一般作为父类，如果有其他构造，最好写一个无参数构造，防止继承出错
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
	
	
//	这些方法是可以有顺序执行的。模板模式
	public void update()
	{
		move();
//		destroy();
	}
	
//	你是选择this pk参数 还是参数pk参数
	public boolean gamepk(SuperElement se)
	{
		Rectangle r1=new Rectangle(x,y,w,h);
		Rectangle r2=new Rectangle(se.x,se.y,se.w,se.h);
		return r1.intersects(r2);//如果有交集，返回true
	}
	
	public boolean gamepk(SuperElement se1,SuperElement se2)
	{
		Rectangle r1=new Rectangle(se1.x,se1.y,se1.w,se1.h);
		Rectangle r2=new Rectangle(se2.x,se2.y,se2.w,se2.h);
		return r1.intersects(r2);//如果有交集，返回true
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
