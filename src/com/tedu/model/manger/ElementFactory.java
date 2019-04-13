package com.tedu.model.manger;

import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

/*
 * �������ݲ�����ͬ���Զ���ȡ��Դ�����vo���󣬴洢��Ԫ�ع�����
 */

public class ElementFactory {
	
	public static SuperElement elementFactory(String name)
	{
		Map<String,List<String>> map=ElementLoad.getElementLoad().getPlaymap();
		List<String> list1=ElementLoad.getElementLoad().getGameList();
		List<String> list2=ElementLoad.getElementLoad().getGameList();
		
		switch(name)
		{
		case "oneplayer":
			List<String> list=map.get(name);
			String s=list.get(0);//playerA,playFire,150,300,40,40
			System.out.println("-----draw player-----");
			System.out.println(s);
			return Player.createPlayer(s);
		case "enemy":
			String str=list1.get(list1.size()-1);
			System.out.println("-----draw enemy-----");
			System.out.println(str);
			return Enemy.createEnemy(str);
		/*case"basemap":
			String s2=basemap.get();
			return Map.createBaseMap(s2);*/
		}
		
		return null;
	}
	
}