package com.tedu.model.load;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

public class ElementLoad {
	
	private Map<String,ImageIcon> map;
	private Map<String,List<String>> playmap;
	private Map<String,List<String>> enemymap;
	private List<String> gameList;//游戏的流程控制 兵力出现控制
	
	private static ElementLoad load;
//	pro文件读取对象
	private Properties pro;
	
	private ElementLoad()
	{
		map=new HashMap<>();
		pro=new Properties();
		playmap=new HashMap<>();
		gameList=new ArrayList<>();
	}
	public static synchronized ElementLoad getElementLoad()
	{
		if(load==null)
		{
			load=new ElementLoad();
		}
		return load;
	}
	
//	读取流程
	public void readGamepro()
	{
		InputStream in=ElementLoad.class.getClassLoader().getResourceAsStream("com/tedu/pro/GameRunA.pro");
		pro.clear();
		try {
			pro.load(in);
			for(Object o:pro.keySet())
			{
				String str=pro.getProperty(o.toString());
//				System.out.println(pro.keySet());
//				System.out.println(str);
				gameList.add(str);
//				System.out.println(str);
			}
			System.out.println(gameList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	读取图片
	public void readImgPro()
	{
		InputStream in=ElementLoad.class.getClassLoader().getResourceAsStream("com/tedu/pro/map1.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set)
			{
				//pro.getProperty((String)o);
				String url=pro.getProperty(o.toString());
				map.put(o.toString(), new ImageIcon(url));
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);
	}
	
//	public void readImgPros(int num)
//	{
//		InputStream in=ElementLoad.class.getClassLoader().getResourceAsStream("com/tedu/pro/map"+num+".pro");
//		try {
//			pro.clear();
//			pro.load(in);
//			Set<?> set=pro.keySet();
//			for(Object o:set)
//			{
////				pro.getProperty((String)o);
//				String url=pro.getProperty(o.toString());
//				map.put(o.toString(), new ImageIcon(url));
//				
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	读取主角配置
	public void readPlayPro()
	{
		InputStream in=ElementLoad.class.getClassLoader().getResourceAsStream("com/tedu/pro/play.pro");
		try {
			pro.clear();
			pro.load(in);
			List<String> list=new ArrayList<>();
			for(Object o:pro.keySet())
			{
				String str=pro.getProperty(o.toString());
				list.add(str);
				playmap.put((String) o,list);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(playmap);
	}
	
	public static void main(String[] args)
	{
		
		getElementLoad().readPlayPro();
		getElementLoad().readImgPro();
		getElementLoad().readGamepro();
	}
	
	
	public Map<String, ImageIcon> getMap() {
		return map;
	}
	public Map<String, List<String>> getPlaymap() {
		return playmap;
	}
	public Map<String, List<String>> getEnemymap() {
		return enemymap;
	}
	public List<String> getGameList() {
		return gameList;
	}
	
		
}

