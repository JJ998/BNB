package com.tedu.model.vo;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.PortableRemoteObject;
import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manger.ElementManger;
import com.tedu.model.manger.MoveType;

import static com.tedu.model.manger.MoveType.down;

public class Player extends SuperElement {
	private int hp;//血量
	private int num;//分数
	private ImageIcon img;
	//	地图障碍数组，0表示可以行走
	private static int[][] MapList = new int[16][16];
	private static int[][] canwalk = new int[16][16];
	public static int flag = 0;
	private static int bomb_flag = 0;
	private static int pdemon_flag = 0;//没有吃到紫鬼头
	private static int[][] PropList = new int[16][16];
	private static int p = 1;
	int tap = 0;
	private MoveType moveType;//0 1 2 3

	private int moveX = 0;
	private int moveY = 0;
	private int playtype;


	private boolean shoot;//攻击状态，默认为false

	public Player(int x, int y, int w, int h, ImageIcon img, int playtype) {
		super(x, y, w, h);
		this.setHp(100);
		this.img = img;
		num = 0;
		moveType = MoveType.stop;
		shoot = false;
		this.playtype = playtype;
	}

	//获取canwalk数组状态
	public static int getCanwalkNum(int x1, int y1) {
		// TODO Auto-generated method stub
		return canwalk[x1][y1];
	}

	//  可以直接调用这个方法，用来得到一个玩家对象 str里面包含玩家对象的信息
	public static Player createPlayer(String str) {
		System.out.println("玩家" + p + "已经创建了");
		p++;
		String[] arr = str.split(",");
		int x = Integer.parseInt(arr[2]);
		int y = Integer.parseInt(arr[3]);
		int w = Integer.parseInt(arr[4]);
		int h = Integer.parseInt(arr[5]);
		int playtype = Integer.parseInt(arr[6]);
		System.out.println("playtype等于" + playtype);
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(arr[0]);
		return new Player(x, y, w, h, img, playtype);
	}

	@Override
	public void showElement(Graphics g) {
		if (flag < 1) {
			readMap();
			changeMapList();
		}
		g.drawImage(img.getImage(),
				getX(), getY(),                  //屏幕左上角坐标
				getX() + getW(), getY() + getH(),    //屏幕右下角坐标
				0 + (50 * moveX), 0 + (65 * moveY),    //图片左上角坐标    0 ,0
				50 + (50 * moveX), 65 + (65 * moveY),    //图片右下角坐标  50,60
				null);
		flag = 1;

	}

	//改变canwalk数组状态
	public static void setCanwalkNum(int x1, int y1, int change) {
		System.out.println(x1 + " " + y1 + MapList[y1][x1]);
		MapList[x1][y1] = change;
		System.out.println("set" + x1 + " " + y1 + MapList[y1][x1]);
	}

	public void Set_MedicineBottle_Invisible(int x, int y) {
		List<SuperElement> proList = ElementManger.getManger().getElementList("puzzlp");
		for (int j = 0; j < proList.size(); j++) {
			int propX = proList.get(j).getX();
			int propY = proList.get(j).getY();
			if (x == propX && y == propY) {
				proList.get(j).setVisible(false);
			}
		}
	}

	public boolean judge(int getX, int getY, MoveType moveType)//判断前进的方向是否有障碍物，如果有返回false，没有返回true
	{
		boolean result = false;
		int x = getX / 45;
		int y = getY / 45;

		switch (moveType) {
			case top:
				if (MapList[x][y - 1] == 0) {
					result = true;
				} else {
					System.out.println("top方向有障碍物!");
					result = false;
				}
				break;
			case down:
				if (MapList[x][y + 1] == 0) {
					result = true;
				} else {
					System.out.println("down方向有障碍物!");
					result = false;
				}
				break;
			case left:
				if (MapList[x - 1][y] == 0) {
					result = true;
				} else {
					System.out.println("left方向有障碍物!");
					result = false;
				}
				break;
			case right:
				if (MapList[x + 1][y] == 0) {
					result = true;
				} else {
					System.out.println("right方向有障碍物!");
					result = false;
				}
				break;
			case stop:
				break;
		}
		return result;
	}

	public void showxy() {
		System.out.println("角色得当前位置: " + "(" + getX() / 45 + "," + getY() / 45 + ")");
	}


	private void updateImageStop() {
		// TODO Auto-generated method stub
		moveX = 0;
		moveY = 0;
	}

	private void updateImageTop() {
		// TODO Auto-generated method stub
		moveY = 3;
		moveX++;
		if (moveX > 3) {
			moveX = 0;
		}
	}

	public void updateImageDown() {
		moveY = 0;
		moveX++;
		if (moveX > 3) {
			moveX = 0;
		}
	}

	private void updateImageLeft() {
		moveY = 1;
		moveX++;
		if (moveX > 3) {
			moveX = 0;
		}
	}

	private void updateImageRight() {
		moveY = 2;
		moveX++;
		if (moveX > 3) {
			moveX = 0;
		}
	}

	public void readMap() {
		try {
			BaseMap baseMap = new BaseMap("com/tedu/pro/map/level1.pro");
			PropList = Prop.getPropList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		canwalk = BaseMap.getMap();
	}

	public void move() {
		if (bomb_flag != 0) {//定时恢复原来的爆炸威力
			bomb_flag--;
			Bomb.Bomb_Energe = 2;
		}
		if (pdemon_flag == 1) {//如果吃到紫鬼头，就反向行走
			contrary_move();
		} else {
			switch (moveType) {
				case top:
					tap = 1;
					if (getY() > 0)
						if (judge(getX(), getY(), MoveType.top))
							setY(getY() - 45);
					showxy();
					updateImageTop();
					break;
				case down:
					tap = 3;
					if (getY() < 675)
						if (judge(getX(), getY(), down))
							setY(getY() + 45);
					showxy();
					updateImageDown();
					break;
				case left:
					tap = 4;
					if (getX() > 10)
						if (judge(getX(), getY(), MoveType.left))
							setX(getX() - 45);
					showxy();
					updateImageLeft();
					break;
				case right:
					tap = 2;
					if (getX() < 675)
						if (judge(getX(), getY(), MoveType.right))
							setX(getX() + 45);
					showxy();
					updateImageRight();
					break;
				case stop:
					tap = 0;
					if (moveType == MoveType.stop) {
						updateImageStop();
					}
					break;
			}
		}

		if (PropList[getX() / 45][getY() / 45] == 1) {//如果吃的是药瓶，泡泡威力增加并标记
			Bomb.Bomb_Energe = 10;
			bomb_flag = 100;
			Set_MedicineBottle_Invisible(getX(), getY());
			PropList[getX() / 45][getY() / 45] = 0;
		} else if (PropList[getX() / 45][getY() / 45] == 2) {//如果吃的是紫鬼头,标记
			pdemon_flag = 1;
			Set_MedicineBottle_Invisible(getX(), getY());
			PropList[getX() / 45][getY() / 45] = 0;
		} else if (PropList[getX() / 45][getY() / 45] == 3) {//如果吃到了紫药水，取消标记
			pdemon_flag = 0;
			Set_MedicineBottle_Invisible(getX(), getY());
			PropList[getX() / 45][getY() / 45] = 0;
		}

	}


	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}


	public int getPlaytype() {
		return playtype;
	}


	public void setPlaytype(int playtype) {
		playtype = playtype;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void changeMapList() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				MapList[j][i] = canwalk[i][j];
			}
		}
	}

	public void addBomb() {
		if (!shoot)//如果shoot为false，就不需要添加子弹
		{
			return;
		}
		if (Bomb.CanAddBomb()) {
			List<SuperElement> list = ElementManger.getManger().getElementList("playBomb");
			list.add(Bomb.createBomb(getX(), getY(), ""));
		} else return;
		shoot = false;//每按一次只能放放一个炸弹
	}

	//	重写父类的模板
	public void update() {
		move();
		addBomb();//放炸弹
	}

	public void contrary_move() {
		switch (moveType) {
			case down:
				tap = 1;
				if (getY() > 0)
					if (judge(getX(), getY(), MoveType.top))
						setY(getY() - 45);
				showxy();
				updateImageTop();
				break;
			case top:
				tap = 3;
				if (getY() < 675)
					if (judge(getX(), getY(), down))
						setY(getY() + 45);
				showxy();
				updateImageDown();
				break;
			case right:
				tap = 4;
				if (getX() > 10)
					if (judge(getX(), getY(), MoveType.left))
						setX(getX() - 45);
				showxy();
				updateImageLeft();
				break;
			case left:
				tap = 2;
				if (getX() < 675)
					if (judge(getX(), getY(), MoveType.right))
						setX(getX() + 45);
				showxy();
				updateImageRight();
				break;
			case stop:
				tap = 0;
				if (moveType == MoveType.stop) {
					updateImageStop();
				}
				break;
		}
	}


}
