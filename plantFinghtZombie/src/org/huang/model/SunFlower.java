package org.huang.model;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;
import org.huang.view.MainView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class SunFlower  extends Plant{
	private int createSunCountDown;
	
	
	public SunFlower(int x,int y) {
		setPosition(new Point(x,y));
		setX((int)(getPosition().x*Globals.getOnePiceWidth()));
		setY((int)(getPosition().y*Globals.getOnePieceHeight()));
		setFrameCountDown(3);
		setImageCount(8);
		setIndex(0);
		setLife(100);
		createSunCountDown=(int)(200+Math.random()*200);
		
	}

	@Override
	public void drawImage(Canvas canvas, Paint paint) {
		//根据植物所在的位置 来绘制图片
		canvas.drawBitmap(ImageUils.getSunFlowerImage(getIndex()), getX(),getY(), paint);
		setFrameCountDown(getFrameCountDown());
		if(getFrameCountDown()==0){
			setFrameCountDown(3);
			setIndex(getIndex()+1);
			if(getIndex()>=getImageCount()){
				setIndex(0);
			}
		}
	}

	@Override
	public void act(MainView mainView) {
		
	}

	@Override
	public void drawCard(Canvas canvas, Paint paint, int index) {
		canvas.drawBitmap(ImageUils.getSunFlowerCard(), (int)index*Globals.getOnePiceWidth()*2/3,0, paint);
		
	}
	

}
