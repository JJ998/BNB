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
	private List<String> gameList;//��Ϸ�����̿��� �������ֿ���
    private List<String> propList;// ����ͼ
    private Map<String, ImageIcon> basemap;//	��ͼ

	private static ElementLoad load;
//	pro�ļ���ȡ����
	private Properties pro;
	
	private ElementLoad()
	{
		map=new HashMap<>();
		pro=new Properties();
		playmap=new HashMap<>();
        // TODO: �Ժ���԰�gameList����ΪpuzzleList
        gameList = new ArrayList<>(); // �����ַ���
        propList = new ArrayList<>(); // �����ַ���
        music1 audioPlayWave = new music1("music/bg.wav");// ������
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

    //	��ȡ���ӡ�����
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
	
//	��ȡͼƬ
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
	
	
//	��ȡ��������
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
            System.out.println("��play.pro�ļ��ж����������ݳ���" + list.size());
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

    //������ص�ͼ�ϵĸ���ԭ��
    public Map<String, ImageIcon> getBaseMap() {
        return basemap;
    }
	
		
}

