package com.tedu.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tedu.model.manger.ElementManger;
import com.tedu.model.vo.SuperElement;

public class MyJPanel extends JPanel implements Runnable{
	
	/*1.这个paint是由底层自动调用的，我们重写父类的方法
	2.这个方法只会执行一次，如果不持续调用，就不会持续执行
	帧：50-100毫秒每帧 20-10帧每秒*/
	
	@Override//作用是用来显示
	public void paint(Graphics g) {
//		给一个判定值 也可以使用枚举
		super.paint(g);
//		1.前动画
		this.setBackground(Color.BLACK);
		
//		2.gameRuntime
		gameRuntime(g);//Graphics 画笔
//		3.衔接动画
		
	}

	private void gameRuntime(Graphics g) 
	{
//		List<SuperElement> list=ElementManger.getManger().getElementList("XX");
//		g.drawString("*", 100, 100);
		Map<String,List<SuperElement>> map=ElementManger.getManger().getMap();
		Set<String> set=map.keySet();
		for(String key:set)
		{
			List<SuperElement> list1=map.get(key);
			for(int i=0;i<list1.size();i++)
			{
				list1.get(i).showElement(g);
			}
		}
	}
	
	/*
	 *什么是重写
	 *1.是有继承关系的类与类之间的语法现象（多态的一种实现）
	 *2.重写的方法必须和父类的方法的签名一样（返回值，方法名称，参数序列）
	 *3.重写的方法访问修饰符只可以比父类的更加开放，不可以比父类更加封闭
	 *4.重写的方法抛出的异常不可以比父类的更加开放
	 */

	@Override
	public void run() 
	{
		while(true)//死循环：界面刷新不会停止
		{
//			线程的休眠
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Image image=new ImageIcon("img/Background/Fin_baseMap.png").getImage();
		g.drawImage(image, 0, 0,900,720, this);
	}

}
