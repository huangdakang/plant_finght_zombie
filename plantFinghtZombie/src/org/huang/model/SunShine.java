package org.huang.model;

import java.util.Random;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class SunShine {
	//坐标
	private float  x;
	private float  y;
	
	// 横向或纵向的速度
	
	private int xSpeed;
	private int ySpeed;
	
	// 下=落的时间 单位是帧
	
	private int downTime;
	// 上升的时间  单位是帧
	private int upTime;
	
	//记录帧绿
	private int  frameCount=5;
	
	// 保留的时间
	private int stayCountDown=20;
	
	// 记录是否点击过
	private boolean clickFlag=false;
	
	//x 上的飞行速度
	private float flyXSpeed;
	
	//y 上的飞行速度
	private float flyYSpeed;
	
	
	// 飞行的时间 计算单位为帧率
	private int flyTime=5;
	
	// 绘制阳光的图片
	public void drawImage(Canvas canvas,Paint paint){
		canvas.drawBitmap(ImageUils.getSunShineImage(), x, y,paint);
	}
	// upFlag 是true 为太阳化产生的  为false 自动产生的
	public SunShine(float x,float y,boolean upFlag) {
		this.x=x;
		this.y=y;
		if(upFlag){
			ySpeed=-20;
			//随机产生一个10 到-10 的数 作为横向的速度
			this.xSpeed=(int)(Math.random()*20-10);
			//设置向上移动的时间
			upTime=5;
		}else{
			ySpeed=5;
			
			//   
			downTime=getDownTimeRandom();
		}
	}
	//随机生成一个下落的时间
	private int getDownTimeRandom(){
		Random ran=new Random();
		return (int)Globals.getOnePieceHeight()/5+ran.nextInt((int)Globals.getOnePieceHeight()/4*5);
	}
	
	
	//移动阳光的坐标
	//返回是否需要销毁词阳光
	public boolean move(){
		// 计算帧率 判断是否需要移动
		frameCount--;
		if(frameCount<=0){
			// 判断是否被点击过 如果点击过 则需要移动
			if(clickFlag){
				y+=flyYSpeed;
				x+=flyXSpeed;
				flyTime--;
				if(flyTime<=0){
					return true;
				}
				
			}else{
				frameCount=5;
				if(ySpeed<0){
					// 向上移动，同事向x方向移动
					upTime--;
					y+=ySpeed;
					x+=xSpeed;
					//判断是否上升到头
					if(upTime<=0){
						// 开始下落
						ySpeed=5;
						downTime=(int)(Globals.getOnePieceHeight()*1.25/5);
					}
				}else if(ySpeed>0){
					// 下落状态  不横向移动，只向下移动
					y+=ySpeed;
					downTime--;
					if(downTime<=0){
						ySpeed=0;
					}
				}else{
					//不动时 计算该对象是否超过保留的时间，如果超过保留的时间销毁
					stayCountDown--;
					if(stayCountDown<=0){
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
	// 判断是否点击过
	public boolean clicked(float fingerX,float fingerY){
		if(!clickFlag){
			// 判断点的位置对不对
			Log.i("--------","---------click in");
			if(fingerX>this.x&&fingerY>this.y&&fingerX<this.x+Globals.getOnePiceWidth()
					&&fingerY<this.y+Globals.getOnePieceHeight()){
				Log.i("--------","---------click in  in");
				// 点到范围之内 计算飞行的速度
				flyXSpeed=(Globals.getOnePiceWidth()*6-this.x)/5;
				flyYSpeed=-this.y/5;
				clickFlag=true;
				return true;
			}
		}
		return false;
	}
	public boolean isClickFlag() {
		return clickFlag;
	}
	public void setClickFlag(boolean clickFlag) {
		this.clickFlag = clickFlag;
	}
	
	
	
	

}
