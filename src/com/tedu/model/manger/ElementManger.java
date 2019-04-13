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

//	Ԫ�ع�����
//	java���ģʽ-������ģʽ-��ȫ��ֻ��һ��ʵ��
//	hashCode();��Object��-�����ϵ�����Set���Ϲ�ϣɢ��ԭ��

public class ElementManger {
	
//	��ʼ��
	protected void init()
	{
		map=new HashMap();
//		��ʱ������ʵ���� ������������
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
		
		
//		1.����һ�������࣬�̳�SuperElement
//		2.��д������ShowElement
//		3.����һ��list�����һ��������ȥ
//		4.list���뵽map��
	}
	
//	���� NPCԪ�أ�����Ԫ�أ�������
	Map<String,List<SuperElement>> map;//�ô���ʲô������

	public Map<String, List<SuperElement>> getMap() 
	{
		return map;//�õ�һ��������map����
	}
	
	public List<SuperElement> getElementList(String key)
	{
		return map.get(key);//�õ�һ��Ԫ�صļ���
	}
	
//	��������Ҫһ��Ψһ������
	private static ElementManger elementManger;
//	���췽��˽�л�����ֻ���ڱ����п���new��ֻ�����Լ�����ȥʵ�����������
	private ElementManger()
	{
		init();
	}

	static//��̬��������������ص�ʱ��ͻ�ִ��
	{
		if(elementManger==null)
		{
			
			elementManger=new ElementManger();
		}
	}
//	�ṩ���������ⲿ���ʵ�Ψһ��� synchronized �̱߳�����
	public static /*synchronized*/ ElementManger getManger()
	{
//		if(elementManger==null)
//		{
//			
//			elementManger=new ElementManger();
//		}
		return elementManger;
	}
	
//������Ҫ����Դ
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		ElementLoad.getElementLoad().readGamepro();
//		����һ��״̬�����������ǰϷ�ˣ�ǰ��Ĺ�����Ϣ��
//		������������
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

/*//�������� int time��Ϸ����ʱ��
	public void linkGame(int time) {
//		�����õ�����list
		List<String> list=ElementLoad.getElementLoad().getGameList();
		if(list.size()==0)
		{
//			System.out.println("���̽���");
			return;//���̽���
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
//		if(����ʱ��ƥ����)
//		{
//			map.get("enemy").add(ElementFactory.elementFactory("enemy"));
//			���̵�����ǰ��Ŀ��������
//		}
	}*/
}


