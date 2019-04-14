package com.tedu.model.vo;

public class MapArray {
	private int[][] canwalk=new int [16][16];
	
	public MapArray(){
		
	}
	
	public int[][] getCanwalk() {
		return canwalk;
	}
	//载入地图
	public void setCanwalk(int[][] canwalk) {
		this.canwalk = canwalk;
	}
	
	
	
	//给出索引，返回数组内元素
	public int gatCanwalkNum(int x,int y){
		return canwalk[x][y];
		}
	
	
	
}
