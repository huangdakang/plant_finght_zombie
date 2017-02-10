package org.huang.model;

import java.util.Iterator;

import org.huang.utils.Globals;
import org.huang.view.MainView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public abstract class Plant {
	
	//所在的坐标 单位是像素
	private int x;
	
	private int y;
	
	// 植物所在的格 单位是格
	private Point position;
	
	// 加入动画播放的变量
	// 每隔几帧切换一张新图片
    private int frameCountDown;
	
    //图片的数量
    private int imageCount;
    
    // 当前图片的索引
    private int  index;
    
    //life
    
    private int life;
    
    public abstract void drawImage(Canvas canvas,Paint paint);
    public abstract void act(MainView mainView);
    public abstract void drawCard(Canvas canvas ,Paint paint,int index);
    
    public boolean checkHasZombie(MainView mainView){
    	// judge if need bult
    	boolean flag=false;
    	Iterator<Zombie> zi=mainView.getAllZombie().iterator();
    	while(zi.hasNext()){
    		Zombie z=zi.next();
    		
    		if(z.getY()==getPosition().y){
    			// fount fire  other not fire
    			/*if((z.getX()-this.x)>Globals.getOnePiceWidth()/6&&(this.x-z.getX())<Globals.getOnePiceWidth()*3/4){
    				flag=false;
    				break;
    			}else{
    				flag= true;
    				break;
    			}*/
    			if(z.getX()>this.x){
    				flag=true;
    				break;
    			}
    		}
    	}
    	return flag;
    }
    
    public boolean checkDead(){
    	if(getLife()<=0){
    		return true;
    	}
    	return false;
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
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
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	

}
