package com.tedu.model.manger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Puzzle;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

//	元素管理器
//	java设计模式-》单例模式-》全局只有一个实例
//	hashCode();是Object类-》集合的排序（Set集合哈希散列原理）

public class ElementManger {
	
//	初始化
	protected void init()
	{
		map=new HashMap();
		List<SuperElement> list=new ArrayList<>();
		List<SuperElement> list1=new ArrayList<>();
		map.put("puzzlp", new ArrayList<>());//放道具的
		map.put("puzzle", list1);
		map.put("player", list);
		map.put("playBomb", new ArrayList<>());
		map.put("wate", new ArrayList<>());

	}
	
//	集合 NPC元素，场景元素，。。。
	Map<String,List<SuperElement>> map;//好处是什么？？？

	public Map<String, List<SuperElement>> getMap() 
	{
		return map;//得到一个完整的map集合
	}
	
	public List<SuperElement> getElementList(String key)
	{
		return map.get(key);//得到一个元素的集合
	}
	
//	单例：需要一个唯一的引用
	private static ElementManger elementManger;
//	构造方法私有化，就只有在本类中可以new，只有我自己可以去实例化这个对象
	private ElementManger()
	{
		init();
	}

	static//静态的语句块是在类加载的时候就会执行
	{
		if(elementManger==null)
		{
			
			elementManger=new ElementManger();
		}
	}
//	提供出来给予外部访问的唯一入口 synchronized 线程保护锁
	public static /*synchronized*/ ElementManger getManger()
	{
//		if(elementManger==null)
//		{
//			
//			elementManger=new ElementManger();
//		}
		return elementManger;
	}
	
//加载需要的资源
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		ElementLoad.getElementLoad().readGamepro();
//		开放一个状态，界面可以做前戏了（前面的过渡信息）
//		。。。。。。
		int i;
		SuperElement[] props = ElementFactory.elementFactory("prop");
		if (props != null) {
			for (i = 0; i < props.length; i++) map.get("puzzlp").add(props[i]);
		}

		SuperElement[] puzzles = ElementFactory.elementFactory("puzzle");
		if (puzzles != null) {
			for (i = 0; i < puzzles.length; i++) map.get("puzzle").add(puzzles[i]);
		}

		SuperElement[] players = ElementFactory.elementFactory("oneplayer");
		if (players != null) {
			for (i = 0; i < players.length; i++) map.get("player").add(players[i]);
		}

		System.out.println("player数组的长度为" + players.length);
	}

}


