package org.huang.model;

import org.huang.view.MainView;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Zombie {
	//距离左边的距离
	private float x;
	
	// 所在的行
	private int y;
	
	//动画属性
    private int frameCountDown;
    
    // 图片的数量
    private int imageCount;
    
    //当前使用图片的索引
    private int index;
    
    //移动的速度
    //控制几帧移动5像素
    private int speed;
    
    //life
    private int life=50;
    
    // status 0 normal 1 cold
    private int status;
    
    //code time
    private int  freezeCountDown=75;
    
    
    //dead frame
    private int deadFrame=2;
    
    //dead index
    private int deadIndex=0;
    
    private int attach;
    
    
    
    
    
   public abstract void drawImage(Canvas canvas ,Paint paint);
   
   public abstract boolean moveZombie(MainView mainView);
   

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	

	public int getFrameCountDown() {
		return frameCountDown;
	}

	public void setFrameCountDown(int frameCountDown) {
		this.frameCountDown = frameCountDown;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFreezeCountDown() {
		return freezeCountDown;
	}

	public void setFreezeCountDown(int freezeCountDown) {
		this.freezeCountDown = freezeCountDown;
	}

	public int getDeadFrame() {
		return deadFrame;
	}

	public void setDeadFrame(int deadFrame) {
		this.deadFrame = deadFrame;
	}

	public int getDeadIndex() {
		return deadIndex;
	}

	public void setDeadIndex(int deadIndex) {
		this.deadIndex = deadIndex;
	}

	public int getAttach() {
		return attach;
	}

	public void setAttach(int attach) {
		this.attach = attach;
	}
    
    
	

}
