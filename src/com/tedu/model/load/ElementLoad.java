package com.tedu.model.load;

import com.tedu.model.vo.BaseMap;
import com.tedu.model.vo.music1;
import com.tedu.model.vo.myMusicPlay;

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
    private List<String> propList;// 道具图
    private Map<String, ImageIcon> basemap;//	地图

	private static ElementLoad load;
//	pro文件读取对象
	private Properties pro;
	
	private ElementLoad()
	{
		map=new HashMap<>();
		pro=new Properties();
		playmap=new HashMap<>();
        // TODO: 以后可以把gameList改名为puzzleList
        gameList = new ArrayList<>(); // 箱子字符串
        propList = new ArrayList<>(); // 道具字符串
        music1 audioPlayWave = new music1("music/bg.wav");// 开音乐
        audioPlayWave.start();
	}
	public static synchronized ElementLoad getElementLoad()
	{
		if(load==null)
		{
			load=new ElementLoad();
		}
		return load;
	}

    //	读取箱子、道具
	public void readGamepro()
	{
		try {
            BaseMap baseMap = new BaseMap("com/tedu/pro/map/level1.pro");
            gameList = baseMap.getGameList();
            propList = baseMap.getPropList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	读取图片
	public void readImgPro()
	{
        InputStream in = ElementLoad.class.getClassLoader().getResourceAsStream("com/tedu/pro/map.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set)
			{
				String url=pro.getProperty(o.toString());
				map.put(o.toString(), new ImageIcon(url));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(map);
	}
	
	
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
            System.out.println("从play.pro文件中读出来的数据长度" + list.size());
		} catch (IOException e) {
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

    public List<String> getPropList() {
        return propList;
    }

    //下面加载地图上的各种原件
    public Map<String, ImageIcon> getBaseMap() {
        return basemap;
    }
	
		
}

