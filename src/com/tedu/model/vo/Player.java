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
	private int hp;//Ѫ��
	private int num;//����
	private ImageIcon img;
	//	��ͼ�ϰ����飬0��ʾ��������
	private static int[][] MapList = new int[16][16];
	private static int[][] canwalk = new int[16][16];
	public static int flag = 0;
	private static int bomb_flag = 0;
	private static int pdemon_flag = 0;//û�гԵ��Ϲ�ͷ
	private static int[][] PropList = new int[16][16];
	private static int p = 1;
	int tap = 0;
	private MoveType moveType;//0 1 2 3

	private int moveX = 0;
	private int moveY = 0;
	private int playtype;


	private boolean shoot;//����״̬��Ĭ��Ϊfalse

	public Player(int x, int y, int w, int h, ImageIcon img, int playtype) {
		super(x, y, w, h);
		this.setHp(100);
		this.img = img;
		num = 0;
		moveType = MoveType.stop;
		shoot = false;
		this.playtype = playtype;
	}

	//��ȡcanwalk����״̬
	public static int getCanwalkNum(int x1, int y1) {
		// TODO Auto-generated method stub
		return canwalk[x1][y1];
	}

	//  ����ֱ�ӵ�����������������õ�һ����Ҷ��� str���������Ҷ������Ϣ
	public static Player createPlayer(String str) {
		System.out.println("���" + p + "�Ѿ�������");
		p++;
		String[] arr = str.split(",");
		int x = Integer.parseInt(arr[2]);
		int y = Integer.parseInt(arr[3]);
		int w = Integer.parseInt(arr[4]);
		int h = Integer.parseInt(arr[5]);
		int playtype = Integer.parseInt(arr[6]);
		System.out.println("playtype����" + playtype);
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
				getX(), getY(),                  //��Ļ���Ͻ�����
				getX() + getW(), getY() + getH(),    //��Ļ���½�����
				0 + (50 * moveX), 0 + (65 * moveY),    //ͼƬ���Ͻ�����    0 ,0
				50 + (50 * moveX), 65 + (65 * moveY),    //ͼƬ���½�����  50,60
				null);
		flag = 1;

	}

	//�ı�canwalk����״̬
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

	public boolean judge(int getX, int getY, MoveType moveType)//�ж�ǰ���ķ����Ƿ����ϰ������з���false��û�з���true
	{
		boolean result = false;
		int x = getX / 45;
		int y = getY / 45;

		switch (moveType) {
			case top:
				if (MapList[x][y - 1] == 0) {
					result = true;
				} else {
					System.out.println("top�������ϰ���!");
					result = false;
				}
				break;
			case down:
				if (MapList[x][y + 1] == 0) {
					result = true;
				} else {
					System.out.println("down�������ϰ���!");
					result = false;
				}
				break;
			case left:
				if (MapList[x - 1][y] == 0) {
					result = true;
				} else {
					System.out.println("left�������ϰ���!");
					result = false;
				}
				break;
			case right:
				if (MapList[x + 1][y] == 0) {
					result = true;
				} else {
					System.out.println("right�������ϰ���!");
					result = false;
				}
				break;
			case stop:
				break;
		}
		return result;
	}

	public void showxy() {
		System.out.println("��ɫ�õ�ǰλ��: " + "(" + getX() / 45 + "," + getY() / 45 + ")");
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
		if (bomb_flag != 0) {//��ʱ�ָ�ԭ���ı�ը����
			bomb_flag--;
			Bomb.Bomb_Energe = 2;
		}
		if (pdemon_flag == 1) {//����Ե��Ϲ�ͷ���ͷ�������
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

		if (PropList[getX() / 45][getY() / 45] == 1) {//����Ե���ҩƿ�������������Ӳ����
			Bomb.Bomb_Energe = 10;
			bomb_flag = 100;
			Set_MedicineBottle_Invisible(getX(), getY());
			PropList[getX() / 45][getY() / 45] = 0;
		} else if (PropList[getX() / 45][getY() / 45] == 2) {//����Ե����Ϲ�ͷ,���
			pdemon_flag = 1;
			Set_MedicineBottle_Invisible(getX(), getY());
			PropList[getX() / 45][getY() / 45] = 0;
		} else if (PropList[getX() / 45][getY() / 45] == 3) {//����Ե�����ҩˮ��ȡ�����
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
		if (!shoot)//���shootΪfalse���Ͳ���Ҫ����ӵ�
		{
			return;
		}
		if (Bomb.CanAddBomb()) {
			List<SuperElement> list = ElementManger.getManger().getElementList("playBomb");
			list.add(Bomb.createBomb(getX(), getY(), ""));
		} else return;
		shoot = false;//ÿ��һ��ֻ�ܷŷ�һ��ը��
	}

	//	��д�����ģ��
	public void update() {
		move();
		addBomb();//��ը��
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
