package com.tedu.main;

import com.tedu.frame.MyJFrame;
import com.tedu.frame.MyJPanel;
import com.tedu.thread.GameListener;

public class GameStart {
//  ������Ϸ����ڣ�����
	public static void main(String[] args) {
//		��Դ����
//		������أ��Զ���...��
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		GameListener listener=new GameListener();
//		��������
		jf.setJp(jp);//ע��
		jf.setKeyListener(listener);
		
		jf.addListener();
		jf.addJpanels();
		jf.addJpanels();//����
//		��Ϸ��������ʼ��	
		jf.start();
	}

}
