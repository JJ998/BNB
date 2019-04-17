package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manger.ElementManger;

public class Bomb extends SuperElement{
	private ImageIcon img;
	private int moveX=0;
	public static int Bomb_Max=1;
	public static int Bomb_Num=1;
	public static int Bomb_Energe=2;
	private boolean boomed=true;
	public Bomb() {
		super();
	}
	public Bomb(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;

    }
	
	public static boolean CanAddBomb(){
		System.out.println(Bomb_Num);
        return Bomb_Num - 1 >= 0;
	}
	public static Bomb createBomb(int x,int y,String str){

        ImageIcon img = new ImageIcon("img/paopao.png");
		Bomb_Num--;
		return new Bomb(x,y,50,50,img);
	}

	@Override
    public void showElement(Graphics g) {
        g.drawImage(img.getImage(), getX(), getY(), getX() + getW(), getY() + getH(), 0 + (32 * moveX), 0, 32 + (32 * moveX), 46, null);

    }
	
//	public void throwBomb() {
//		if(boomed) {
//		new Thread(new BombBump()).start();
//		}
//		boomed=false;
//	}

    public void updateImage() {
        moveX = (moveX + 1) % 4;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    @Override
    public void destroy() {
        Bomb_Num++;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        if (boomed) {
            new Thread(new BombBump()).start();
        }
        boomed = false;
    }

    public void judge() {
        List<SuperElement> waterList = ElementManger.getManger().getElementList("wate");
        List<SuperElement> puzzleList = ElementManger.getManger().getElementList("puzzle");
//		List<SuperElement> proList = ElementManger.getManger().getElementList("zpro");

//		for(int i=0;i<puzzleList.size();i++)
//		{
//
//			int waterX=waterList.get(i).getX();
//			int waterY=waterList.get(i).getY();
//			if(waterX==puzzleX)
//			{
//				puzzleList.get(i).setVisible(false);
//			}
//			if(waterX==puzzleX)
//			{
//				puzzleList.get(i).setVisible(false);
//			}
//			if((y+90)==puzzleY)
//			{
//				puzzleList.get(i).setVisible(false);
//			}
//			if((y-90)==puzzleY)
//			{
//				puzzleList.get(i).setVisible(false);
//			}
//		}
        for (int i = 0; i < waterList.size(); i++) {
            int waterX = waterList.get(i).getX();
            int waterY = waterList.get(i).getY();
            for (int j = 0; j < puzzleList.size(); j++) {
                int puzzleX = puzzleList.get(j).getX();
                int puzzleY = puzzleList.get(j).getY();
                if (waterX == puzzleX && waterY == puzzleY)
                    puzzleList.get(j).setVisible(false);
            }
        }

        System.out.println("箱子一共有" + puzzleList.size() + "个！！！！！！！！！！！");
    }

    private class BombBump implements Runnable {
        @Override
        public void run() {
            try {
                myMusicPlay audioPlayWave = new myMusicPlay("music/appear~1.wav");// 开音乐
                audioPlayWave.start();
                Player.setCanwalkNum(getX() / 45, getY() / 45, 1);
                System.out.println(":::::");
                Thread.sleep(500);
                updateImage();
                Thread.sleep(500);
                updateImage();
                Thread.sleep(500);
                updateImage();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setVisible(false);
            destroy();

            //V0
            Water.createWater(getX() / 45, getY() / 45, Bomb_Energe);//2->Bomb_Energy
            judge();
            List<SuperElement> waterList = ElementManger.getManger().getElementList("wate");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Water.waterdestroy();


            myMusicPlay audioPlayWave = new myMusicPlay("music/boom~1.wav");// 开音乐
            audioPlayWave.start();
        }

    }
}
