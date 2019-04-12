package com.tedu.model.manger;

import java.util.List;
import java.util.Map;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.Enemy;
import com.tedu.model.vo.Player;
import com.tedu.model.vo.SuperElement;

/*
 * 任务：依据参数不同，自动读取资源，填充vo对象，存储到元素管理器
 */

public class ElementFactory {
	
	public static SuperElement elementFactory(String name)
	{
		Map<String,List<String>> map=ElementLoad.getElementLoad().getPlaymap();
		List<String> list1=ElementLoad.getElementLoad().getGameList();
		switch(name)
		{
		case "oneplayer":
			List<String> list=map.get(name);
			String s=list.get(0);//playerA,playFire,150,300,40,40
			System.out.println(s);
			return Player.createPlayer(s);
		case "enemy":
			String str=list1.get(list1.size()-1);
			return Enemy.createEnemy(str);
			
		}
		
		return null;
	}
	
}
