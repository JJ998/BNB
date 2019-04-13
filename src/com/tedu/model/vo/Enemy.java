package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manger.ElementManger;

public class Enemy extends SuperElement{
	
	private ImageIcon img;
	private int time=0;//敌机射击开关
	private String name;//敌机种类
	
	public Enemy (int x,int y,int w,int h,ImageIcon img)
	{
		super(x,y,w,h);
		this.setHp(10);
		this.img=img;
	}
	
	public static Enemy createEnemy(String str)
	{
		
//		int x=(int)(Math.random()*300);
//		int y=0;
//		int w=50;
//		int h=50;
//		int num=(int)(Math.random()*12);
//		String url="img/enemy/"+num+".png";
//		return new Enemy(x,y,w,h,new ImageIcon(url));
		
		String []arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(arr[0]);

		Enemy enemy=null;
		switch(arr[0])
		{
		case "enemyA":
			enemy=new EnemyLeftToRight(x,y,w,h,img);
			break;
		case "enemyB":
			enemy=new EnemyRightToLeft(x,y,w,h,img);
			break;
		default:
			enemy=new Enemy(x,y,w,h,img);
			break;
		}
		
		return enemy;//(x,y,w,h,img);
	}
	
	public void update()
	{
		time++;
		super.update();
		addEnemyFire();
	}

	public void addEnemyFire()
	{
		if(time<30) {
			return;
		}
		time=0;
		List<SuperElement> list=ElementManger.getManger().getElementList("EnemyFire");
		if(list==null)
		{
			list=new ArrayList<>();
		}
		list.add(EnemyFire.createEnemyFire(getX(), getY(), ""));
		
		ElementManger.getManger().getMap().put("EnemyFire", list);
	}

	private boolean shootby(SuperElement superElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
//		int movex;
//		for(movex=0;movex<8;movex++)
//		{
//			g.drawImage(img.getImage(), getX(), getY(), getX()+getW(), getY()+getH(), 65*movex, 0, 65*(movex+1), 70, null);
//		}
	}

	@Override
	public void move() {
		/*setY(getY()+3);
		if(getY()>500)
		{
//			setVisible(false);
			setY(0);
			setX((int)(Math.random()*250));
		}*/
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		if(!isVisible())
		{
			System.out.println("敌机销毁");
		}
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	
}
