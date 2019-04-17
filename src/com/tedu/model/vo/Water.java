package com.tedu.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.tedu.model.manger.ElementManger;

public class Water extends SuperElement{

	private ImageIcon img;
	private int cleanbox;//��������ˮ���µĸ����Ƿ�Ҫ�ı䣬Ҳ����box�ǲ��Ǳ����
	private int moveX;//����ͼ��
	//moveX 0����  1��ˮ��  2��ˮ��  3��ˮ��  4��ˮ��  5��ˮ��  6��ˮ��  7��ˮ��  8��ˮ��
	public Water(int x, int y, int w, int h,ImageIcon img,int moveX,int cleanbox) {
		super(x, y, w, h);
		this.img=img;
		this.moveX=moveX;
		this.cleanbox=cleanbox;
	}

    static List<SuperElement> waterlist = ElementManger.getManger().getElementList("wate");

    public static void createWater(int x, int y, int bomb_energe) {//x�� y��
		//bomb_energe��ʾ������������ը������
//		ArrayList<Water> waterlist=new ArrayList<Water>();

        ImageIcon img = new ImageIcon("img/ˮ��.png");
		int box1=0,box2=0;
        waterlist.add(new Water(x * 45, y * 45, 45, 45, img, 0, 0));
        Player.setCanwalkNum(x, y, 0);
		for(int d=0;d<4;d++){
			for(int BoomNum=0;BoomNum<bomb_energe;BoomNum++){
                switch (d) {
			case 0://��
                if (y - 1 - BoomNum < 0) {
                    BoomNum = bomb_energe;
                    break;
                }//Խ�紦��
                box1 = Player.getCanwalkNum(y - 1 - BoomNum, x);
					if(box1!=0){
                        if (box1 == 1) {
                            BoomNum = bomb_energe;
                            break;
                        }
                        if (box1 == 2)//��������
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
                    }//Խ�紦��
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
			case 1://��
                if (y + 1 + BoomNum >= 16) {
                    BoomNum = bomb_energe;
                    break;
                }//Խ�紦��
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
                    }//Խ�紦��
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

                    case 2://��
                        if (x - 1 - BoomNum < 0) {
                            BoomNum = bomb_energe;
                            break;
                        }//Խ�紦��
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
                            }//Խ�紦��
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
			case 3://��
                if (x + 1 + BoomNum > 15) {
                    BoomNum = bomb_energe;
                    break;
                }//Խ�紦��
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
                    }//Խ�紦��ͬ�ϰ���
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
                getX(), getY(),                  //��Ļ���Ͻ�����
                getX() + getW(), getY() + getH(),    //��Ļ���½�����

                0 + (47 * moveX), 0,    //ͼƬ���Ͻ�����        60 ,0
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
