package com.tedu.model.vo;

public class MapArray {
	private int[][] canwalk=new int [16][16];
	
	public MapArray(){
		
	}
	
	public int[][] getCanwalk() {
		return canwalk;
	}
	//�����ͼ
	public void setCanwalk(int[][] canwalk) {
		this.canwalk = canwalk;
	}
	
	
	
	//��������������������Ԫ��
	public int gatCanwalkNum(int x,int y){
		return canwalk[x][y];
		}
	
	
	
}
