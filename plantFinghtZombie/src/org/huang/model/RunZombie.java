package org.huang.model;

import java.util.Iterator;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;
import org.huang.view.MainView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

public class RunZombie extends Zombie {
	public RunZombie(int y) {
		setY(y);
		setX(Globals.SCREEN_WIDTH);
		setSpeed(3);
		setFrameCountDown(10);
		setImageCount(8);
		setIndex(0);
		setAttach(8);
		setStatus(1);
	}

	@Override
	public void drawImage(Canvas canvas, Paint paint) {
		// 鏍规嵁妞嶇墿鎵�鍦ㄧ殑浣嶇疆 鏉ョ粯鍒跺浘鐗�
		if(getLife()>0){
			canvas.drawBitmap(ImageUils.getRunZombieImages(getIndex()), getX(), (getY()-0.5f)*
					Globals.getOnePieceHeight(),paint);
			setFrameCountDown(getFrameCountDown()-1);
			if(getFrameCountDown()==0){
				setFrameCountDown(3);
				setIndex(getIndex()+1);
				if(getIndex()>=getImageCount()){
					setIndex(0);
				}
			}
		}else{
			// dead
			canvas.drawBitmap(ImageUils.getZombieDeadImags(getDeadIndex()), getX(),(getY()-0.5f)*
					Globals.getOnePieceHeight(),paint);
		}
		
	}

	@Override
	public boolean moveZombie(MainView mainView) {
		if (getStatus() == 1) {
			setFreezeCountDown(getFreezeCountDown() - 1);
			if (getFreezeCountDown() == 0) {
				setStatus(0);
			}
		}
		if (getLife() > 0) {
			setSpeed(getSpeed() - 1);
			if (getSpeed() == 0) {
			   boolean 	moveFlog=true;
				Iterator<Plant> ip=mainView.getAllPlant().iterator();
				while(ip.hasNext()){
					
					Plant p=ip.next();
					if(p.getPosition().y==getY()){
						if((p.getX() - this.getX() < Globals.getOnePiceWidth() / 2)
								&& (this.getX() - p.getX()) <=( Globals
								.getOnePiceWidth()/ 2)){
							p.setLife(p.getLife()-getAttach());
							moveFlog=false;
							break;
						}
					}
				}
				if(moveFlog){
					setX(getX() - 15);
				}
			    if (getStatus() == 0) {
						//setSpeed(5);
					setSpeed(3);
				} else if (getStatus() == 1) {
						//setSpeed(10);
					setSpeed(10);
				}
				
			}
			
			
		} else {
			setDeadFrame(getDeadFrame() - 1);
			if (getDeadFrame() == 0) {
				setDeadFrame(2);
				setDeadIndex(getDeadIndex() + 1);
				if (getDeadIndex() >= 4) {
					return true;
				}
			}
		}
		return false;
}

}
