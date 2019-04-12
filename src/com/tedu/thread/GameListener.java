package com.tedu.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.tedu.model.manger.ElementManger;
import com.tedu.model.manger.MoveType;
import com.tedu.model.manger.VMoveType;
import com.tedu.model.manger.HMoveType;
import com.tedu.model.vo.Player;

public class GameListener implements KeyListener{
	
	private List<?> list;

	@Override
	public void keyPressed(KeyEvent e)//按下  左37 上38 右39 下40
	{
		list=ElementManger.getManger().getElementList("play");
		Player play=(Player)list.get(0);
		switch(e.getKeyCode())
		{
		case 38:
			play.setMoveType(MoveType.top);
			break;
		case 40:
			play.setMoveType(MoveType.down);
			break;
		case 37:
			play.setMoveType(MoveType.left);
			break;
		case 39:
			play.setMoveType(MoveType.right);
			break;
		case 32:
			play.setShoot(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)//松开
	{
		list=ElementManger.getManger().getElementList("play");
		Player play=(Player)list.get(0);
		switch(e.getKeyCode())
		{
		case 38:
			if(play.getMoveType() == MoveType.top)
				play.setMoveType(MoveType.stop);
			break;
		case 40:
			if(play.getMoveType() == MoveType.down)
				play.setMoveType(MoveType.stop);
			break;
		case 37:
			if(play.getMoveType() == MoveType.left)
				play.setMoveType(MoveType.stop);
			break;
		case 39:
			if(play.getMoveType() == MoveType.right)
				play.setMoveType(MoveType.stop);
			break;
		
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

}
