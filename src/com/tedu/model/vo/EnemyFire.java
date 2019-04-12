package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class EnemyFire extends SuperElement{
	
private ImageIcon img;
private int time=0;

	
	

	public EnemyFire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnemyFire(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void showElement(Graphics g) {
		
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
		
	}

	@Override
	public void move() {
		setY(getY()+10);
//		超过边界的子弹要销毁
		if(getY()>500)
		{
			setVisible(false);
		}
	}
	
	public static EnemyFire createEnemyFire(int x,int y,String str)
	{
		ImageIcon img=new ImageIcon("img/fire/27.png");
		return new EnemyFire(x,y,30,30,img);
	}
	
	
	
	
	
	
	
	

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	@Override
	public void destroy() {
//		销毁的时候，需要创建爆炸对象 添加到爆炸的list集合中
		if(!isVisible())
		{
			System.out.println("子弹销毁");
		}
		
	}
}
