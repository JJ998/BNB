package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Prop extends SuperElement {
    public static int num_medicineBottle = 0;//记录药瓶数量
    private static int[][] PropList = new int[16][16];//记录地图里道具的位置
    //	先加：
//	泡泡：增加泡泡设置数目，获得两个增加一个泡泡
//	蓝药瓶：增加泡泡爆炸威力
    private ImageIcon img;
    private int moveX = 0;

    public Prop() {
        super();
    }

    public Prop(int x, int y, int w, int h, ImageIcon img) {
        super(x, y, w, h);
        this.img = img;
    }

    //	随机设置是否有道具
    public static String randomProp() {
        Random r = new Random();
        int a = r.nextInt(10) + 1;
        switch (a) {
            case 1:
            case 2:
            case 3:
            case 4:
                return "nothing";
            case 5://加药瓶
            case 6:
                return "medicineBottle";
            case 7://加道具泡泡
            case 8:
                return "purpleDemon";
            case 9:
            case 10:
            default:
                return "Antidote";
        }
    }

    public static Prop createProp(String str) {
        String[] arr = str.split(",");
        int x = Integer.parseInt(arr[1]);
        int y = Integer.parseInt(arr[2]);
		/*int w=Integer.parseInt(arr[3]);
		int h=Integer.parseInt(arr[4]);*/
        String type = arr[0];

        if (type.equals("medicineBottle")) {
            ImageIcon img = new ImageIcon("img/play/medicineBottle.png");
            PropList[x / 45][y / 45] = 1;
            return new Prop(x, y, 45, 45, img);
        } else if (type.equals("purpleDemon")) {
            ImageIcon img = new ImageIcon("img/play/PurpleDemon.png");
            PropList[x / 45][y / 45] = 2;
            return new Prop(x, y, 45, 45, img);
        } else if (type.equals("Antidote")) {
            ImageIcon img = new ImageIcon("img/play/Antidote.png");
            PropList[x / 45][y / 45] = 3;
            return new Prop(x, y, 45, 45, img);
        }
        return null;
    }

    //	private class PropBump implements Runnable{
//		@Override
//		public void run() {
//			try {
//				Thread.sleep(500);
//				updateImage();
//				Thread.sleep(500);
//				updateImage();
//				Thread.sleep(500);
//				updateImage();
//				Thread.sleep(500);
//			}catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//
//		}
//	}
    public static int[][] getPropList() {
        return PropList;
    }

    public static void setPropList(int[][] propList) {
        PropList = propList;
    }

    @Override
    public void showElement(Graphics g) {
        //TODO:在地图组件下面设置道具
        g.drawImage(img.getImage(), getX(), getY(), getX() + getW(), getY() + getH(), 0 + (32 * moveX), 0, 32 + (32 * moveX), 47, null);
    }

    public void updateImage() {
        moveX = (moveX + 1) % 4;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

}
