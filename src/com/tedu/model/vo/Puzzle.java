package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manger.ElementManger;

public class Puzzle extends SuperElement {
	
	private ImageIcon img;
	private int time=0;//敌机射击开关

	public Puzzle(int x, int y, int w, int h, ImageIcon img)
	{
		super(x,y,w,h);
		this.setHp(10);
		this.img=img;
	}

	public static Puzzle createPuzzle(String str)
	{
		String []arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(arr[0]);

		Puzzle enemy = null;
		enemy = new Puzzle(x, y, w, h, img);
		
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
		if(img != null) g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
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
