package com.tedu.model.manger;

import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.vo.*;

/*
 * 任务：依据参数不同，自动读取资源，填充vo对象，存储到元素管理器
 */

public class ElementFactory {

    public static SuperElement[] elementFactory(String name)
	{
		Map<String,List<String>> map=ElementLoad.getElementLoad().getPlaymap();
		List<String> list1=ElementLoad.getElementLoad().getGameList();
        List<String> list_prop = ElementLoad.getElementLoad().getPropList();

        int i;
		switch(name)
		{
		case "oneplayer":
			List<String> list=map.get(name);
            System.out.println("-----draw player-----");
            SuperElement[] players = new SuperElement[list.size()];
            for (i = 0; i < list.size(); i++) {
                String s = list.get(i);
                System.out.println(s);
                players[i] = Player.createPlayer(s);
            }
            return players;
            case "puzzle":
                System.out.println("-----draw puzzle-----");
                Puzzle[] puzzles = new Puzzle[list1.size()];
                for (i = 0; i < list1.size(); i++) {
                    String str = list1.get(i);
                    System.out.println(str);
                    puzzles[i] = Puzzle.createPuzzle(str);
                }
                return puzzles;
            case "prop":
                System.out.println("-----draw prop-----");
                Prop[] prop = new Prop[list_prop.size()];
                for (i = 0; i < list_prop.size(); i++) {
                    String str = list_prop.get(i);
                    System.out.println(str);
                    prop[i] = Prop.createProp(str);
                }
                return prop;
		}
		
		return null;
	}
	
}
