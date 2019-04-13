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
	private int hp;//Ѫ��
	private int num;//����
	private ImageIcon img;
	private int time=0;//��������ӵ���������
	int tap=0;
//  ��ǰ��ҵ����ơ�����������
//	��ҵĿ��Ʒ���1���������õ���֪ʶ�����������ʱʹ��booleanֵ 2����4��
//	private boolean top;
//	private boolean left;

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

	private boolean shoot;//����״̬��Ĭ��Ϊfalse
	
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
	
//  ����ֱ�ӵ�����������������õ�һ����Ҷ��� str���������Ҷ������Ϣ
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
		ImageIcon img = ElementLoad.getElementLoad().getMap().get(arr[0]);
		return new Player(x,y,w,h,img);
	}
	
	@Override
	public void showElement(Graphics g) 
	{
		g.drawImage(img.getImage(), 
				getX(), getY(),                  //��Ļ���Ͻ�����
				getX()+getW(), getY()+getH(),    //��Ļ���½�����
					0+(100*moveX), 0+(100*moveY),    //ͼƬ���Ͻ�����        60 ,0
					100+(100*moveX), 100+(100*moveY),    //ͼƬ���½�����  120,60
//					moveX, 0,    //ͼƬ���Ͻ�����        60 ,0
//					moveX+60, 60,	
					null);

	}
	
	
	public void move()
	{
		switch(moveType)
		{
		case top:
			tap=1;
			setY(getY()-45);
			updateImageTop();
			break;
		case down:
			tap=3;
			setY(getY()+45);
			updateImageDown();
			break;
		case left:
			tap=4;
			setX(getX()-45);
			updateImageLeft();
			break;
		case right:
			tap=2;
			setX(getX()+45);
			updateImageRight();
			break;
		case stop:
			tap=0;
			if (hMoveType == hMoveType.stop) {
				updateImageStop();
			}
			break;
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

	/*public void addFire()
	{
//		��ס�ո񲻷ų������
//		if(time<10) {
//			return;
//		}
//		time=0;
		if(!shoot)//���shootΪfalse���Ͳ���Ҫ����ӵ�
		{
			return;
		}
		List<SuperElement> list=ElementManger.getManger().getElementList("playFire");
//		if(list==null)
//		{
//			list=new ArrayList<>();
//		}
		list.add(PlayerFire.createPlayerFire(getX(), getY(), ""));
		
//		ElementManger.getManger().getMap().put("playFire", list);
		
		shoot=false;//ÿ��һ��ֻ�ܷ���һ���ӵ�
	}*/
	
//	��д�����ģ��
	public void update()
	{
		//time++;
		super.update();//���û����仰��������д�ƶ�ģ��
		//addFire();//׷��
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
	
	
}
