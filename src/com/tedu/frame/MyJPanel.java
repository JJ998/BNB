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
	
	/*1.���paint���ɵײ��Զ����õģ�������д����ķ���
	2.�������ֻ��ִ��һ�Σ�������������ã��Ͳ������ִ��
	֡��50-100����ÿ֡ 20-10֡ÿ��*/
	
	@Override//������������ʾ
	public void paint(Graphics g) {
//		��һ���ж�ֵ Ҳ����ʹ��ö��
		super.paint(g);
//		1.ǰ����
		this.setBackground(Color.BLACK);
		
//		2.gameRuntime
		gameRuntime(g);//Graphics ����
//		3.�νӶ���
		
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
	 *ʲô����д
	 *1.���м̳й�ϵ��������֮����﷨���󣨶�̬��һ��ʵ�֣�
	 *2.��д�ķ�������͸���ķ�����ǩ��һ��������ֵ���������ƣ��������У�
	 *3.��д�ķ����������η�ֻ���Աȸ���ĸ��ӿ��ţ������Աȸ�����ӷ��
	 *4.��д�ķ����׳����쳣�����Աȸ���ĸ��ӿ���
	 */

	@Override
	public void run() 
	{
		while(true)//��ѭ��������ˢ�²���ֹͣ
		{
//			�̵߳�����
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
