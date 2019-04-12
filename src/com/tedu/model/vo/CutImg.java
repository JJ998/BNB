package com.tedu.model.vo;

/**
 * @Author Fisher
 * @Date 2019/4/10 14:58
 **/

import javax.swing.*;

/**
 * ͼƬ�и���
 * ����ͼƬ�ķָ�
 * ����ͼƬ����
 */
public class CutImg {
    /**
     * ����ͼƬ���Ͻ����꣬Ĭ��Ϊ0
     */
    private int ImgTopX=0;

    /**
     * ����ͼƬ���½����꣬Ĭ��Ϊ0
     */
    private int ImgTopY=0;

    /**
     * ͼƬ�Ŀ��
     */
    private int ImgWidth;

    /**
     * ͼƬ�ĸ߶�
     */
    private int ImgHeight;

    /**
     * ��XY��СͼƬ��xֵ��Ĭ��Ϊ0
     */
    private int NoX=0;

    /**
     * ��XY��СͼƬ��Yֵ��Ĭ��Ϊ0
     */
    private int NoY=0;

    /**
     * ͼƬһ���ָ�ΪX��
     */
    private int MaxX;

    /**
     * ͼƬһ���ָ�ΪY��
     */
    private int MaxY;

    public CutImg() {
    }

    public CutImg(ImageIcon imageIcon, int maxX, int maxY) {
        ImgWidth = imageIcon.getIconWidth();
        ImgHeight = imageIcon.getIconHeight();
        MaxX = maxX;
        MaxY = maxY;
    }

    /**
     * �����л�����XY��ͼƬ
     * @return boolean
     */
    public boolean changeImg(int Nox, int Noy) {
        setNoX(Nox);
        setNoY(Noy);
        return true;
    }

    /**
     * ��ȡXY��ͼƬ���Ͻ�X����
     * @return int
     */
    public int getTopX() {
        return getImgTopX()+(getImgWidth()/getMaxY()*getNoY());
    }

    /**
     * ��ȡXY��ͼƬ���Ͻ�Y����
     * @return int
     */
    public int getTopY() {
        return getImgTopY()+(getImgHeight()/getMaxX()*getNoX());
    }

    /**
     * ��ȡXY��ͼƬ���½�X����
     * @return int
     */
    public int getBottomX() {
        return getImgTopX()+(getImgWidth()/getMaxY()*(getNoY()+1));
    }

    /**
     * ��ȡXY��ͼƬ���½�Y����
     * @return
     */
    public int getBottomY() {
        return getImgTopY()+(getImgHeight()/getMaxX()*(getNoX()+1));
    }

    public int getImgTopX() {
        return ImgTopX;
    }

    public void setImgTopX(int imgTopX) {
        ImgTopX = imgTopX;
    }

    public int getImgTopY() {
        return ImgTopY;
    }

    public void setImgTopY(int imgTopY) {
        ImgTopY = imgTopY;
    }

    public int getImgWidth() {
        return ImgWidth;
    }

    public void setImgWidth(int imgWidth) {
        ImgWidth = imgWidth;
    }

    public int getImgHeight() {
        return ImgHeight;
    }

    public void setImgHeight(int imgHeight) {
        ImgHeight = imgHeight;
    }

    public int getNoX() {
        return NoX;
    }

    public void setNoX(int noX) {
        NoX = noX;
    }

    public int getNoY() {
        return NoY;
    }

    public void setNoY(int noY) {
        NoY = noY;
    }

    public int getMaxX() {
        return MaxX;
    }

    public void setMaxX(int maxX) {
        MaxX = maxX;
    }

    public int getMaxY() {
        return MaxY;
    }

    public void setMaxY(int maxY) {
        MaxY = maxY;
    }
}
