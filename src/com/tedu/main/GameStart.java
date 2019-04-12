package com.tedu.main;

import com.tedu.frame.MyJFrame;
import com.tedu.frame.MyJPanel;
import com.tedu.thread.GameListener;

public class GameStart {
//  整个游戏的入口，启动
	public static void main(String[] args) {
//		资源加载
//		窗体加载（自动化...）
		MyJFrame jf=new MyJFrame();
		MyJPanel jp=new MyJPanel();
		GameListener listener=new GameListener();
//		监听加载
		jf.setJp(jp);//注入
		jf.setKeyListener(listener);
		
		jf.addListener();
		jf.addJpanels();
		jf.addJpanels();//加载
//		游戏启动（开始）	
		jf.start();
	}

}
