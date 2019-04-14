package com.tedu.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tedu.thread.GameThread;

/*
 * ��Ϸ�Ĵ�����
 * ���ܣ������ʼ������С���رգ������󶨣�����ע��
 * �̳У�JFrame�����壩
 * */
public class MyJFrame extends JFrame{
//	��������� ����setע��ķ�ʽ
	private KeyListener keyListener;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	private JPanel jp;
	
//	���췽����һ��̳��ڸ���ķ����������е��ø���ĳ�ʼ���Ȳ�д�ڹ��캯���У�
	public MyJFrame()
	{
		init();
		
	}
//	��ʼ�����������췽���޷����̳У���init�������ԣ�init�������Ա���д��
	public void init()
	{
		this.setTitle("game");//���ñ���
		this.setSize(900, 825);//���ô�С
		//this.setResizable(false);//���ô��岻�����޸Ĵ�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùر�״̬
		this.setLocationRelativeTo(null);//���þ���
	}
	
//	�󶨼���
	public void addListener()
	{
		if(keyListener!=null)//������̼�����Ϊ�գ�����Ӽ��̼���
		{
			this.addKeyListener(keyListener);
		}
		if(mouseListener!=null)
		{
			this.addMouseListener(mouseListener);
		}
		if(mouseMotionListener!=null)
		{
			this.addMouseMotionListener(mouseMotionListener);
		}
	}
	
	public void addJpanels()
	{
		if(jp!=null)
		{
			this.add(jp);
		}
//		else
//		{
//			throw new RuntimeException("��Ϸ��ʼ����ʧ��");
//		}
	}
	
	public void start()
	{
//		�߳�����
//		
		GameThread gt=new GameThread();
		gt.start();
		
//		����ˢ���߳�����
		if(jp instanceof Runnable)//jp����ָ���ʵ������ǲ���Runnable������
		{
			new Thread((MyJPanel)jp).start();;
		}
		
		
		this.setVisible(true);//������ʾ
	}
	
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setJp(JPanel jp) {
		this.jp = jp;
	}
	
	
}

