package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manger.ElementManger;

public class Water extends SuperElement{

	private ImageIcon img;
	private int cleanbox;//用来标明水柱下的格子是否要改变，也就是box是不是被清除
	private int moveX;//绘制图像
	//moveX 0中心  1下水柱  2上水柱  3右水柱  4左水柱  5下水花  6上水花  7右水花  8左水花
	public Water(int x, int y, int w, int h,ImageIcon img,int moveX,int cleanbox) {
		super(x, y, w, h);
		this.img=img;
		this.moveX=moveX;
		this.cleanbox=cleanbox;
	}

    static List<SuperElement> waterlist = ElementManger.getManger().getElementList("wate");

    public static void createWater(int x, int y, int bomb_energe) {//x列 y行
		//bomb_energe表示泡泡威力，能炸到几格
//		ArrayList<Water> waterlist=new ArrayList<Water>();

        ImageIcon img = new ImageIcon("img/水柱.png");
		int box1=0,box2=0;
        waterlist.add(new Water(x * 45, y * 45, 45, 45, img, 0, 0));
        Player.setCanwalkNum(x, y, 0);
		for(int d=0;d<4;d++){
			for(int BoomNum=0;BoomNum<bomb_energe;BoomNum++){
                switch (d) {
			case 0://上
                if (y - 1 - BoomNum < 0) {
                    BoomNum = bomb_energe;
                    break;
                }//越界处理
                box1 = Player.getCanwalkNum(y - 1 - BoomNum, x);
					if(box1!=0){
                        if (box1 == 1) {
                            BoomNum = bomb_energe;
                            break;
                        }
                        if (box1 == 2)//爆掉箱子
                        {
                            waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 6, 1));
                            Player.setCanwalkNum(x, (y - 1 - BoomNum), 0);
                            BoomNum = bomb_energe;
                            break;
                        }
                        if (BoomNum + 1 == bomb_energe) {
                            waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 6, 0));
                            break;
                        }
                        //System.out.println("2x0 "+BoomNum);
                    }
                if (box1 == 0) {
                    if (y - 2 - BoomNum < 0) {
                        waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 6, 0));
                        BoomNum = bomb_energe;
                        break;
                    }//越界处理
                    box2 = Player.getCanwalkNum((y - 2 - BoomNum), x);
                    if (box2 == 1 || BoomNum + 1 == bomb_energe) {
                        waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 6, 0));
                        break;
                    }
                    if (box2 == 2 || box2 == 0) {
                        waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 2, 0));
                        break;
                    }
                    //System.out.println("2x1 "+BoomNum);
					}
					break;
			case 1://下
                if (y + 1 + BoomNum >= 16) {
                    BoomNum = bomb_energe;
                    break;
                }//越界处理
                box1 = Player.getCanwalkNum(y + 1 + BoomNum, x);
//				System.out.println("3box1"+box1);
                if (BoomNum + 1 == bomb_energe) {
                    waterlist.add(new Water(x * 45, (y + 1 + BoomNum) * 45, 45, 45, img, 5, 0));
                    break;
                }
					if(box1!=0){
						if(box1==1)
						{BoomNum=bomb_energe;break;}
                        if (box1 == 2) {
                            waterlist.add(new Water(x * 45, (y + 1 + BoomNum) * 45, 45, 45, img, 5, 1));
                            Player.setCanwalkNum(x, (y + 1 + BoomNum), 0);
                            BoomNum = bomb_energe;
                            break;
                        }
                        if (BoomNum + 1 == bomb_energe) {
                            waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 6, 0));
                            break;
                        }
                        //System.out.println("3x0 "+BoomNum);
                    }
                if (box1 == 0) {
                    if (y + 2 + BoomNum >= 16) {
                        waterlist.add(new Water(x * 45, (y + 1 + BoomNum) * 45, 45, 45, img, 5, 0));
                        BoomNum = bomb_energe;
                        break;
                    }//越界处理
                    box2 = Player.getCanwalkNum((y + 2 + BoomNum), x);
                    if (box2 == 1 || BoomNum + 1 == bomb_energe) {
                        waterlist.add(new Water(x * 45, (y + 1 + BoomNum) * 45, 45, 45, img, 5, 0));
                        break;
                    }
                    if (box2 == 2 || box2 == 0) {
                        waterlist.add(new Water(x * 45, (y + 1 + BoomNum) * 45, 45, 45, img, 1, 0));
                        break;
                    }
                    //System.out.println("3x1 "+BoomNum);
					}
					break;

                    case 2://左
                        if (x - 1 - BoomNum < 0) {
                            BoomNum = bomb_energe;
                            break;
                        }//越界处理
                        box1 = Player.getCanwalkNum(y, x - 1 - BoomNum);
//				System.out.println("0box1"+box1);
                        if (BoomNum + 1 == bomb_energe)
                            waterlist.add(new Water((x - 1 - BoomNum) * 45, y * 45, 45, 45, img, 8, 0));
					if(box1!=0){
                        if (box1 == 1) {
                            BoomNum = bomb_energe;
                            break;
                        }
                        if (box1 == 2) {
                            waterlist.add(new Water((x - 1 - BoomNum) * 45, y * 45, 45, 45, img, 8, 1));
                            Player.setCanwalkNum((x - 1 - BoomNum), y, 0);
                            BoomNum = bomb_energe;
                            break;
                        }
                        if (BoomNum + 1 == bomb_energe) {
                            waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 6, 0));
                            break;
                        }
                        //System.out.println("0x0 "+BoomNum);
                    }
                        if (box1 == 0) {
                            if (x - 2 - BoomNum < 0) {
                                waterlist.add(new Water((x - 1 - BoomNum) * 45, y * 45, 45, 45, img, 8, 0));
                                BoomNum = bomb_energe;
                                break;
                            }//越界处理
                            box2 = Player.getCanwalkNum(y, x - 2 - BoomNum);
                            if (box2 == 1 || BoomNum + 1 == bomb_energe) {
                                waterlist.add(new Water((x - 1 - BoomNum) * 45, y * 45, 45, 45, img, 8, 0));
                                break;
                            }
                            if (box2 == 2 || box2 == 0) {
                                waterlist.add(new Water((x - 1 - BoomNum) * 45, y * 45, 45, 45, img, 4, 0));
                                break;
                            }
                            //System.out.println("0x1 "+BoomNum);
					}
					break;
			case 3://右
                if (x + 1 + BoomNum > 15) {
                    BoomNum = bomb_energe;
                    break;
                }//越界处理
                box1 = Player.getCanwalkNum(y, x + 1 + BoomNum);
                if (BoomNum + 1 == bomb_energe)
                    waterlist.add(new Water((x + 1 + BoomNum) * 45, y * 45, 45, 45, img, 7, 0));
					if(box1!=0){
						if(box1==1)
						{BoomNum=bomb_energe;break;}
                        if (box1 == 2) {
                            waterlist.add(new Water((x + 1 + BoomNum) * 45, y * 45, 45, 45, img, 7, 1));
                            Player.setCanwalkNum((x + 1 + BoomNum), y, 0);
                            BoomNum = bomb_energe;
                            break;
                        }
                        if (BoomNum + 1 == bomb_energe) {
                            waterlist.add(new Water(x * 45, (y - 1 - BoomNum) * 45, 45, 45, img, 6, 0));
                            break;
                        }
                        //System.out.println("1x0 "+BoomNum);
                    }
                if (box1 == 0) {
                    if (x + 2 + BoomNum >= 16) {
                        waterlist.add(new Water((x + 1 + BoomNum) * 45, y * 45, 45, 45, img, 7, 0));
                        BoomNum = bomb_energe;
                        break;
                    }//越界处理同障碍物
                    box2 = Player.getCanwalkNum(y, x + 2 + BoomNum);
//						System.out.println("1box2"+box2);
                    if (box2 == 1 || BoomNum + 1 == bomb_energe) {
                        waterlist.add(new Water((x + 1 + BoomNum) * 45, y * 45, 45, 45, img, 7, 0));
                        break;
                    }
                    if (box2 == 2 || box2 == 0) {
                        waterlist.add(new Water((x + 1 + BoomNum) * 45, y * 45, 45, 45, img, 3, 0));
                        break;
                    }
                    //System.out.println("1x1 "+BoomNum);
					}
					break;

                }
			}
		}
//		return waterlist;
//		System.out.println("!!!" + waterlist.size());
    }

    public static void waterdestroy() {
        waterlist.clear();
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public int getCleanbox() {
        return cleanbox;
    }

    public void setCleanbox(int cleanbox) {
        this.cleanbox = cleanbox;
    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
        g.drawImage(img.getImage(),
                getX(), getY(),                  //屏幕左上角坐标
                getX() + getW(), getY() + getH(),    //屏幕右下角坐标

                0 + (47 * moveX), 0,    //图片左上角坐标        60 ,0
                45 + (47 * moveX), 45,
                null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
