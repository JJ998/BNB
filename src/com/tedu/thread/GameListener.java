package com.tedu.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.tedu.model.manger.ElementManger;
import com.tedu.model.manger.MoveType;
import com.tedu.model.vo.Player;


public class GameListener implements KeyListener{
	
	private List<?> list;

    @Override
    public void keyPressed(KeyEvent e) {
        list = ElementManger.getManger().getElementList("player");
        if (list.size() <= 0) {
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                Player player = (Player) list.get(i);
                this.playerPressOperate(e, player);
            }
        }
    }

    // ¼üÅÌËÉ¿ª
    @Override
    public void keyReleased(KeyEvent e) {
        list = ElementManger.getManger().getElementList("player");
        if (list.size() <= 0) {
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                Player player = (Player) list.get(i);
                this.playerReleaseOperate(e, player);
            }
        }
    }

    public void playerPressOperate(KeyEvent e, Player player) {
        if (player == null) {
            return;
        } else {
            if (player.getPlaytype() == 2) {
                switch (e.getKeyCode()) {
                    case 87:
                        player.setMoveType(MoveType.top);
                        break;
                    case 83:
                        player.setMoveType(MoveType.down);
                        break;
                    case 65:
                        player.setMoveType(MoveType.left);
                        break;
                    case 68:
                        player.setMoveType(MoveType.right);
                        break;
                    case 32:
                        player.setShoot(true);
                        break;
                }
            }
            if (player.getPlaytype() == 1) {
                switch (e.getKeyCode()) {
                    case 38:
                        player.setMoveType(MoveType.top);
                        break;
                    case 40:
                        player.setMoveType(MoveType.down);
                        break;
                    case 37:
                        player.setMoveType(MoveType.left);
                        break;
                    case 39:
                        player.setMoveType(MoveType.right);
                        break;
                    case 47:
                        player.setShoot(true);
                        break;
                }
            }
        }
    }

    public void playerReleaseOperate(KeyEvent e, Player player) {
        if (player == null) {
            return;
        } else {
            if (player.getPlaytype() == 2) {
                switch (e.getKeyCode()) {
                    case 87:
                        if (player.getMoveType() == MoveType.top)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 83:
                        if (player.getMoveType() == MoveType.down)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 65:
                        if (player.getMoveType() == MoveType.left)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 68:
                        if (player.getMoveType() == MoveType.right)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 47:
                        player.setShoot(false);
                }
            }

            if (player.getPlaytype() == 1) {
                switch (e.getKeyCode()) {
                    case 38:
                        if (player.getMoveType() == MoveType.top)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 40:
                        if (player.getMoveType() == MoveType.down)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 37:
                        if (player.getMoveType() == MoveType.left)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 39:
                        if (player.getMoveType() == MoveType.right)
                            player.setMoveType(MoveType.stop);
                        break;
                    case 32:
                        player.setShoot(false);
                }
            }
        }
    }


	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

}
