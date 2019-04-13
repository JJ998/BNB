package com.tedu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tedu.model.manger.ElementManger;
import com.tedu.model.vo.Puzzle;
import com.tedu.model.vo.SuperElement;

//java是单继承，多实现。通过内部类的方式，弥补单继承的缺陷
public class GameThread extends Thread{
//	计时数据
	private int time=0;
	
	public void run()
	{
		//死循环，但会有变量（状态）进行控制
		while(true)//控制游戏整体进度
		{
		
			//1.加载地图，人物
			loadElement();
			//2.显示人物地图（流程，自动化（移动，碰撞））
			time=0;
			runGame();
			//3.结束本地图
			overGame();
		
			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void overGame() {
		// TODO Auto-generated method stub
		
	}

	private void runGame() {
		while(true)//每个关卡中的状态
		{
//			List<SuperElement>list=ElementManger.getManger().getElementList("play");
//			for(int i=0;i<list.size();i++)
//			{
//				list.get(i).move();
//				list.get(i).update();
//			}
			Map<String,List<SuperElement>> map=ElementManger.getManger().getMap();
			Set<String> set=map.keySet();
			List<String> list=new ArrayList<>();
			list.addAll(set);
			for(int j=0;j<list.size();j++)
//			for(String key:list)//迭代器在遍历的过程中，迭代器内的元素不可以增加或删除
			{
				String key=list.get(j);
				
				List<SuperElement> list1=map.get(key);
				for(int i=list1.size()-1;i>=0;i--)
				{
					list1.get(i).update();
					if(!list1.get(i).isVisible())
					{
						list1.remove(i);
					}
				}
			}
			
//			使用一个独立的方法来进行判定是否碰撞
//			PK();
			
			
			
			linkGame();
			
			
			//死亡，通关等结束runGame
			try {
				sleep(110);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time++;
		}
	}
	
	
	
	private void PK() 
	{
		List<SuperElement> list1=ElementManger.getManger().getElementList("playFire");
		List<SuperElement> list2=ElementManger.getManger().getElementList("enemy");
		List<SuperElement> list3=ElementManger.getManger().getElementList("EnemyFire");
		List<SuperElement> list4=ElementManger.getManger().getElementList("play");
		listPK(list1,list2);
		listPK(list3,list4);
//		可以进行比较
		
	}
	
	public void listPK(List<SuperElement> list1,List<SuperElement> list2)
	{
		for(int i=0;i<list1.size();i++)
		{
			for(int j=0;j<list2.size();j++)
			{
				if(list1.get(i).gamepk(list2.get(j))) 
				{
					int hp1=list1.get(i).getHp();
					int hp2=list2.get(i).getHp();
					list1.get(i).setHp(hp1-10);
					list2.get(i).setHp(hp2-10);
					if(list1.get(i).getHp()==0)
					{
						list1.get(i).setVisible(false);
					}
					if(list2.get(j).getHp()==0)
					{
						list2.get(j).setVisible(false);
					}
					
					
					break;
				}
			}
		}
	}

//	流程控制
	public void linkGame()
	{
//		Map<String,List<SuperElement>> map=ElementManger.getManger().getMap();
//		List<SuperElement> list=map.get("enemy");
//		if(time%100==0)
//			list.add(Enemy.createEnemy(null));
	}	
	
//	控制进度，但是，作为控制，请不要接触load
	private void loadElement() {

		ElementManger.getManger().load();
	}

}
