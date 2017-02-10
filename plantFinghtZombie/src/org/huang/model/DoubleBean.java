package org.huang.model;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;
import org.huang.view.MainView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class DoubleBean  extends Plant{
	
	private int fireCountDown=10;
	private int bulltCount=0;
	
	
	public DoubleBean(int x,int y) {
		setPosition(new Point(x,y));
		setX((int)(getPosition().x*Globals.getOnePiceWidth()));
		setY((int)(getPosition().y*Globals.getOnePieceHeight()));
		setFrameCountDown(3);
		setImageCount(8);
		setIndex(0);
		setLife(150);
		
	}

	@Override
	public void drawImage(Canvas canvas, Paint paint) {
		//根据植物所在的位置 来绘制图片
		canvas.drawBitmap(ImageUils.getDoubleBeanImages(getIndex()), getX(),getY(), paint);
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
			if(fireCountDown==0){
				Bullet b=new Bullet(this.getX()+Globals.getOnePiceWidth(), this.getPosition().y,0);
				mainView.getAllBullet().add(b);
				//next all bullet fire
				if(bulltCount==1){
					fireCountDown=8;
				}else{
					fireCountDown=72;
				}
				bulltCount++;
				if(bulltCount==3){
					bulltCount=1;
				}
			}
			fireCountDown--;
		}
	}

	@Override
	public void drawCard(Canvas canvas, Paint paint, int index) {
		canvas.drawBitmap(ImageUils.getDoubleBeanCard(), (int )index*Globals.getOnePiceWidth()*2/3,0, paint);
		
	}
	

}
