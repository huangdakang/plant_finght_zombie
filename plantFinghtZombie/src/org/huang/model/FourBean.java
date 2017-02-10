package org.huang.model;

import java.util.Iterator;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;
import org.huang.view.MainView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class FourBean  extends Plant{
	private int fireCountDown=10;
	private int bulletCount=0;
	
	
	
	public FourBean(int x,int y) {
		setPosition(new Point(x,y));
		setX((int)(getPosition().x*Globals.getOnePiceWidth()));
		setY((int)(getPosition().y*Globals.getOnePieceHeight()));
		setFrameCountDown(3);
		setImageCount(8);
		setIndex(0);
		setLife(100);
		
	}

	@Override
	public void drawImage(Canvas canvas, Paint paint) {
		//根据植物所在的位置 来绘制图片
		canvas.drawBitmap(ImageUils.getFourBeanImages(getIndex()), getX(),getY(), paint);
		setFrameCountDown(getFrameCountDown()-1);
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
		if(checkHasZombie(mainView)){
			// to fire
			if(fireCountDown==0){
				Bullet bt=new Bullet(this.getX()+Globals.getOnePiceWidth()/2, getPosition().y, 0);
				mainView.getAllBullet().add(bt);
				if(bulletCount<4){
					fireCountDown=8;
				}else{
					fireCountDown=48;
				}
				bulletCount++;
				if(bulletCount==5){
					bulletCount=1;
				}
			}
			fireCountDown--;
		}
	}

	@Override
	public void drawCard(Canvas canvas, Paint paint, int index) {
		canvas.drawBitmap(ImageUils.getFourBeanCard(), (int )index*Globals.getOnePiceWidth()*2/3,0, paint);
		
	}
	

}
