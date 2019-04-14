package com.tedu.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bomb extends SuperElement{
	private ImageIcon img;
	private int moveX=0;
	public static int Bomb_Max=1;
	public static int Bomb_Num=1;
	public static int Bomb_Energe=2;
	private boolean boomed=true;
	public Bomb() {
		super();
	}
	public Bomb(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
	}
	
	public static boolean CanAddBomb(){
		System.out.println(Bomb_Num);
		if(Bomb_Num-1<0)
			return false;
		else return true;
	}
	public static Bomb createBomb(int x,int y,String str){
	
		ImageIcon img=new ImageIcon("img/Characters/paopao.png");
		Bomb_Num--;
		return new Bomb(x,y,50,50,img);
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //ÆÁÄ»×óÉÏ½Ç×ø±ê
				getX()+getW(), getY()+getH(),    //ÆÁÄ»ÓÒÏÂ½Ç×ø±ê
				
				0+(32*moveX), 0,    //Í¼Æ¬×óÉÏ½Ç×ø±ê        60 ,0
				32+(32*moveX), 46, 
				
//					0, 0,    //Í¼Æ¬×óÉÏ½Ç×ø±ê        60 ,0
//					32, 46,    //Í¼Æ¬ÓÒÏÂ½Ç×ø±ê  120,60
//					moveX, 0,    //Í¼Æ¬×óÉÏ½Ç×ø±ê        60 ,0
//					moveX+60, 60,	
					null);
	}
	@Override
	public void move() {
		if(boomed) {
		new Thread(new BombBump()).start();
		}
		boomed=false;
	}
	
	public void updateImage(){
		moveX=(moveX+1)%4;
	}
	private class BombBump implements Runnable
	{
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
				updateImage();
				Thread.sleep(1000);
				updateImage();
				Thread.sleep(1000);
				updateImage();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setVisible(false);
			destroy();
		}
		
	} 
	
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	@Override
	public void destroy() {
		Bomb_Num++;
}

}
