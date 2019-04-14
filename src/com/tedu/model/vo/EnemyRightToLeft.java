package com.tedu.model.vo;

import javax.swing.ImageIcon;

public class EnemyRightToLeft extends Enemy{
	
	public EnemyRightToLeft(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h, img);
		// TODO Auto-generated constructor stub
	}
	
	public void move()
	{
		setX(getX()-5);
		if(getX()<10)
		{
			this.setVisible(false);
		}
	}
}
