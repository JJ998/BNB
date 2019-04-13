package com.tedu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tedu.model.manger.ElementManger;
import com.tedu.model.vo.Puzzle;
import com.tedu.model.vo.SuperElement;

//java�ǵ��̳У���ʵ�֡�ͨ���ڲ���ķ�ʽ���ֲ����̳е�ȱ��
public class GameThread extends Thread{
//	��ʱ����
	private int time=0;
	
	public void run()
	{
		//��ѭ���������б�����״̬�����п���
		while(true)//������Ϸ�������
		{
		
			//1.���ص�ͼ������
			loadElement();
			//2.��ʾ�����ͼ�����̣��Զ������ƶ�����ײ����
			time=0;
			runGame();
			//3.��������ͼ
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
		while(true)//ÿ���ؿ��е�״̬
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
//			for(String key:list)//�������ڱ����Ĺ����У��������ڵ�Ԫ�ز��������ӻ�ɾ��
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
			
//			ʹ��һ�������ķ����������ж��Ƿ���ײ
//			PK();
			
			
			
			linkGame();
			
			
			//������ͨ�صȽ���runGame
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
//		���Խ��бȽ�
		
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

//	���̿���
	public void linkGame()
	{
//		Map<String,List<SuperElement>> map=ElementManger.getManger().getMap();
//		List<SuperElement> list=map.get("enemy");
//		if(time%100==0)
//			list.add(Enemy.createEnemy(null));
	}	
	
//	���ƽ��ȣ����ǣ���Ϊ���ƣ��벻Ҫ�Ӵ�load
	private void loadElement() {

		ElementManger.getManger().load();
	}

}
