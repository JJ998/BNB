package com.tedu.model.manger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.Player;
//import com.tedu.model.vo.Moon;
//import com.tedu.model.vo.Star;
import com.tedu.model.vo.SuperElement;

//	元素管理器
//	java设计模式-》单例模式-》全局只有一个实例
//	hashCode();是Object类-》集合的排序（Set集合哈希散列原理）

public class ElementManger {
	
//	初始化
	protected void init()
	{
		map=new HashMap();
//		暂时在这里实例化 工厂制作星星
		List<SuperElement> list=new ArrayList<>();
		List<SuperElement> list1=new ArrayList<>();
		map.put("enemy", list1);
		map.put("player", list);
		//map.put("playFire", new ArrayList<>());
		//map.put("EnemyFire", new ArrayList<>());
		
		
//		List<SuperElement> list1=new ArrayList<>();
//		Random random=new Random();
//		for(int i=0;i<100;i++)
//		{
//			Star star=new Star(random.nextInt(300),random.nextInt(400),0,0);
//			list.add(star);
//		}
//		map.put("a", list);
//		Moon moon1=new Moon(200,200,50,50);
//		Moon moon2=new Moon(150,200,50,50);
//		list1.add(moon1);
//		list1.add(moon2);
//		map.put("b", list1);
		
		
//		1.定义一个月亮类，继承SuperElement
//		2.重写方法，ShowElement
//		3.定义一个list，存放一个月亮进去
//		4.list加入到map中
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
        SuperElement[] enemies = ElementFactory.elementFactory("enemy");
        if (enemies != null) {
            for(i = 0; i < enemies.length; i++) map.get("enemy").add(enemies[i]);
        }
		SuperElement[] players = ElementFactory.elementFactory("oneplayer");
		if (players != null) {
			for(i = 0; i < players.length; i++) map.get("player").add(players[i]);
		}
	}

/*//控制流程 int time游戏进行时间
	public void linkGame(int time) {
//		可以拿到流程list
		List<String> list=ElementLoad.getElementLoad().getGameList();
		if(list.size()==0)
		{
//			System.out.println("流程结束");
			return;//流程结束
		}
		String s=list.get(list.size()-1);
		String [] arr=s.split(",");
		int runTime=Integer.parseInt(arr[arr.length-1]);
		if(time>runTime)
		{
		    int i;
            SuperElement[] enemies = ElementFactory.elementFactory("enemy");
            if (enemies != null) {
                for(i = 0; i < enemies.length; i++) map.get("enemy").add(enemies[i]);
            }
			list.remove(list.size()-1);
		}
//		if(两个时间匹配上)
//		{
//			map.get("enemy").add(ElementFactory.elementFactory("enemy"));
//			流程当中最前面的可以清除了
//		}
	}*/
}


