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
		super.update();
	}

	private boolean shootby(SuperElement superElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showElement(Graphics g) {
		if(img != null) g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
	}

	@Override
	public void move() {
		
	}

	@Override
	public void destroy() {
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
}
