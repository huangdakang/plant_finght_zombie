package org.huang.model;

import java.util.Iterator;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;
import org.huang.view.MainView;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet {
	public static  final int NORMAL_BULLET=0;
	public static final int ICE_BULLET=1;
	public static final int FIRE_BULLET=2;
	
	private float x;
	
	private int y;
	
	//speed
	private int xSpeed=9;
	
	//type
	private int type;
	
	// power
	private int power=5;
	
	
	public Bullet(float x,int y,int type) {
		setType(type);
		this.x=x;
		this.y=y;
	}
	
	public void drawImage(Canvas canvas,Paint paint){
		if(type==NORMAL_BULLET){
			canvas.drawBitmap(ImageUils.getNormalBullet(), x-Globals.getOnePiceWidth()/4, (int)((y+0.05)*Globals.getOnePieceHeight()),paint);
		}else if(type==ICE_BULLET){
			canvas.drawBitmap(ImageUils.getIceBullet(), x-Globals.getOnePiceWidth()/4, (int)((y+0.05)*Globals.getOnePieceHeight()),paint);
		}else if(type==FIRE_BULLET){
			canvas.drawBitmap(ImageUils.getFireBullet(), x-Globals.getOnePiceWidth()/4, (int)((y+0.05)*Globals.getOnePieceHeight()),paint);
			canvas.drawBitmap(ImageUils.getFirBulletPlus(), x-Globals.getOnePiceWidth()/4, (int)((y+0.05)*Globals.getOnePieceHeight()),paint);
		}
	}
	
	public boolean move(MainView mainView){
		x+=xSpeed;
		if(x>Globals.SCREEN_WIDTH){
			// remove
			return true;
		}
		Iterator<Zombie> zi=mainView.getAllZombie().iterator();
		while(zi.hasNext()){
			Zombie z=zi.next();
			// is on same line
			if(z.getY()==this.y){
				//is fire
				if(z.getX()-this.x<Globals.getOnePiceWidth()/4&&
						this.x-z.getX()<Globals.getOnePiceWidth()){
					//fireing
					
						z.setLife(z.getLife()-power);
						// is lowing
						if(getType()==ICE_BULLET){
							z.setStatus(1);
							z.setFreezeCountDown(75);
						}
					
					return true;
				}
			}
		}
		return false;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
		if(type==ICE_BULLET){
			power=7;
		}else if(type==FIRE_BULLET){
			power=10;
		}else{
			power=5;
		}
	}
	
	

}
