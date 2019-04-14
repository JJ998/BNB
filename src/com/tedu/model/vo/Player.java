package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.load.ElementLoad;
import com.tedu.model.manger.ElementManger;
import com.tedu.model.manger.MoveType;
import com.tedu.model.manger.VMoveType;
import com.tedu.model.manger.HMoveType;

public class Player extends SuperElement{
	private int hp;//血量
	private int num;//分数
	private ImageIcon img;
	private int time=0;//控制玩家子弹发射速率
//	地图障碍数组，0表示可以行走
	private int MapList[][]=new int [16][16];
	static int[][] canwalk={
			{0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,1,1,0,1,0,1,0,1,0,1,0,0},
			{0,1,0,1,1,1,1,0,0,1,0,1,0,1,0,0},
			{1,0,0,1,1,0,0,2,1,0,1,0,0,1,1,0},
			{1,0,1,0,1,1,0,1,0,1,1,0,1,1,1,0},
			{0,0,1,0,1,0,0,2,1,0,1,0,0,1,1,0},
			{0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,0},
			{0,0,0,1,1,1,0,1,0,1,1,0,1,1,1,0},
			{1,1,0,0,0,1,1,0,1,0,1,0,1,0,1,0},
			{0,1,0,0,1,1,0,1,0,1,0,1,0,1,0,0},
			{0,0,0,0,1,1,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,1,0,1,1,0,1,0,1,0,1,0,1,0},
			{0,1,1,0,1,0,0,2,1,0,1,0,0,1,1,0},
			{0,0,0,0,0,1,1,0,1,0,1,0,1,0,1,0},
			{0,0,0,0,0,1,1,0,1,0,1,0,1,0,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
//	MapArray ma=new MapArray();
//	ma.setCanwalk(canwalk);
	int tap=0;
	

	private MoveType moveType;//0 1 2 3
	private HMoveType hMoveType;
	private VMoveType vMoveType;
	
	private int moveX=0;
	private int moveY=0;
	


	public HMoveType gethMoveType() {
		return hMoveType;
	}

	public void sethMoveType(HMoveType hMoveType) {
		this.hMoveType = hMoveType;
	}

	public VMoveType getvMoveType() {
		return vMoveType;
	}

	public void setvMoveType(VMoveType vMoveType) {
		this.vMoveType = vMoveType;
	}

	private boolean shoot;//攻击状态，默认为false
	
	public Player(int x,int y,int w,int h,ImageIcon img)
	{
		super(x,y,w,h);
		this.setHp(100);
		this.img=img;
		num=0;
		moveType=MoveType.stop;
		vMoveType = VMoveType.stop;
		hMoveType = HMoveType.stop;
		shoot=false;
	}
	
//  可以直接调用这个方法，用来得到一个玩家对象 str里面包含玩家对象的信息
	public static Player createPlayer(String str)
	{
//		int x=170;
//		int y=500;
//		int w=50;
//		int h=50;
//		String url="img/play/11.png";
		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img=ElementLoad.getElementLoad().getMap().get(arr[0]);
		return new Player(x,y,w,h,img);
	}
	
	@Override
	public void showElement(Graphics g) 
	{
		changeMapList();
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //屏幕左上角坐标
				getX()+getW(), getY()+getH(),    //屏幕右下角坐标
					0+(50*moveX), 0+(60*moveY),    //图片左上角坐标    0 ,0
					50+(50*moveX), 60+(60*moveY),    //图片右下角坐标  50,60
					null);

	}
	
	
	public void move()
	{
			switch(moveType)
			{
			case top:
				tap=1;
				if(getY()>0)
					if(judge(getX(),getY(),MoveType.top))
						setY(getY()-45);
				showxy();
				updateImageTop();
				break;
			case down:
				tap=3;
				if(getY()<675)
					if(judge(getX(),getY(),MoveType.down))
						setY(getY()+45);
				showxy();
				updateImageDown();
				break;
			case left:
				tap=4;
				if(getX()>10)
					if(judge(getX(),getY(),MoveType.left))
						setX(getX()-45);
				showxy();
				updateImageLeft();
				break;
			case right:
				tap=2;
				if(getX()<675)
					if(judge(getX(),getY(),MoveType.right))
						setX(getX()+45);
				showxy();
				updateImageRight();
				break;
			case stop:
				tap=0;
				if (moveType == MoveType.stop) {
					updateImageStop();
				}
				break;
			}
	}
	
	public boolean judge(int getX,int getY,MoveType moveType)//判断前进的方向是否有障碍物，如果有返回false，没有返回true
	{
		boolean result=false;
		int x=getX/45;
		int y=getY/45;
		switch(moveType)
		{
		case top:
			if(MapList[x][y-1]==0)
			{
				result=true;
			}
			else
			{
				System.out.println("top方向有障碍物!");
				result=false;
			}
			break;
		case down:
			if(MapList[x][y+1]==0)
			{
				result=true;
			}
				
			else
			{
				System.out.println("down方向有障碍物!");
				result=false;
			}
			break;
		case left:
			if(MapList[x-1][y]==0)
			{
				result=true;
			}
			else
			{
				System.out.println("left方向有障碍物!");
				result=false;
			}
			break;
		case right:
			if(MapList[x+1][y]==0)
			{
				result=true;
			}
			else
			{
				System.out.println("right方向有障碍物!");
				result=false;
			}
			break;
		case stop:
			break;
		}
		return result;
	}
	
	public void showxy()
	{
		System.out.println("角色得当前位置: "+"("+getX()/45+","+getY()/45+")");
	}
	
	public void changeMapList()
	{
		for(int i=0;i<16;i++)
		{
			for(int j=0;j<16;j++)
			{
				MapList[j][i]=canwalk[i][j];
			}
		}
	}
	
	private void updateImageStop() {
		// TODO Auto-generated method stub
		moveX=0; 
		moveY=0;
	}

	private void updateImageTop() {
		// TODO Auto-generated method stub
		moveY=3;
		moveX++;
		if(moveX>3)
		{
			moveX=0;
		}
	}
	
	public void updateImageDown()
	{
		moveY=0;
		moveX++;
		if(moveX>3)
		{
			moveX=0;
		}
	}

	private void updateImageLeft() {
		moveY=1;
		moveX++;
		if(moveX>3)
		{
			moveX=0;
		}
	}

	private void updateImageRight() {
		moveY=2;
		moveX++;
		if(moveX>3)
		{
			moveX=0;
		}
	}

	public void addFire()
	{
//		按住空格不放持续射击
//		if(time<10) {
//			return;
//		}
//		time=0;
		if(!shoot)//如果shoot为false，就不需要添加子弹
		{
			return;
		}
		
		
//		if(list==null)
//		{
//			list=new ArrayList<>();
//		}
		
		if(Bomb.CanAddBomb())
		{
			List<SuperElement> list=ElementManger.getManger().getElementList("playFire");
			list.add(Bomb.createBomb(getX(), getY(), ""));
		}
		else return;
		
//		ElementManger.getManger().getMap().put("playFire", list);
		
		shoot=false;//每按一次只能发射一颗子弹
	}
	
//	重写父类的模板
	public void update()
	{
		//time++;
		super.update();//如果没有这句话，就是重写制定模板
		addFire();//追加

//		updateImage();
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

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	//给出索引，返回数组内元素
	public static int getCanwalkNum(int x,int y){
		return canwalk[x][y];
		}
	
	
	
}
