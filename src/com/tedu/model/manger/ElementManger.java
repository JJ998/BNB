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

//	Ԫ�ع�����
//	java���ģʽ-������ģʽ-��ȫ��ֻ��һ��ʵ��
//	hashCode();��Object��-�����ϵ�����Set���Ϲ�ϣɢ��ԭ��

public class ElementManger {
	
//	��ʼ��
	protected void init()
	{
		map=new HashMap();
		List<SuperElement> list=new ArrayList<>();
		List<SuperElement> list1=new ArrayList<>();
		map.put("puzzlp", new ArrayList<>());//�ŵ��ߵ�
		map.put("puzzle", list1);
		map.put("player", list);
		map.put("playBomb", new ArrayList<>());
		map.put("wate", new ArrayList<>());

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

		System.out.println("player����ĳ���Ϊ" + players.length);
	}

}


