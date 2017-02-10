package org.huang.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.huang.model.Bean;
import org.huang.model.Bullet;
import org.huang.model.Card;
import org.huang.model.DoubleBean;
import org.huang.model.FourBean;
import org.huang.model.HatZombie;
import org.huang.model.IceBean;
import org.huang.model.IconZombie;
import org.huang.model.NormalZombie;
import org.huang.model.Plant;
import org.huang.model.RunZombie;
import org.huang.model.SunFlower;
import org.huang.model.SunShine;
import org.huang.model.Zombie;
import org.huang.utils.Globals;
import org.huang.utils.ImageUils;
import org.huang.utils.MusicUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainView  extends View{
	private int sunShineNumber=5050;
	private Plant plantObject=null;
	
	private List<Plant> allPlant=new ArrayList<Plant>();
	
	private List<SunShine>allSunShine=new ArrayList<SunShine>();
	
	private List<Card>  allCard=new ArrayList<Card>();
	
	
	private List<Zombie> allZombie=new ArrayList<Zombie>();
	
	private List<Bullet> allBullet=new ArrayList<Bullet>();
	
	// 产生阳关的倒计时
	private int sunShineCountDown = 0;
	
	//private List
	
	// 帧率
	private int frameTime=50;
	
	
	
	

	public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		MusicUtils.startBg();
		// 定义刷帧线程
		Thread t=new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(frameTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					postInvalidate();
				}
			};
		};
		t.start();
		
		// init allCard
		allCard.add(new Card(0));
		allCard.add(new Card(1));
		allCard.add(new Card(2));
		allCard.add(new Card(3));
		allCard.add(new Card(4));
		
		allPlant.add(new SunFlower(1, 1));
		allPlant.add(new SunFlower(3, 2));
		
		
		allZombie.add(new NormalZombie(1)) ;
		allZombie.add(new IconZombie(3)) ;
		allZombie.add(new NormalZombie(2)) ;
		allZombie.add(new RunZombie(1)) ;
		allZombie.add(new HatZombie(3)) ;
		allZombie.add(new HatZombie(2)) ;
		// set lisener
		setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float x=event.getX();
				float y=event.getY();
				Point p=new Point((int )(x/Globals.getOnePiceWidth()),
						(int)(y/Globals.getOnePieceHeight()));
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					Iterator<SunShine> ssi=allSunShine.iterator();
					while(ssi.hasNext()){
						SunShine s=ssi.next();
						while(s.clicked(x, y)){
							sunShineNumber+=50;
							return false;
						}
					}
					// judge if can click card
					if(y<Globals.getOnePieceHeight()){
						for(int i=0;i<allCard.size();i++){
							if(x>i*Globals.getOnePiceWidth()*2/3&&x<(i+1)*Globals.getOnePiceWidth()*2/3){
								// true click
								Card c =allCard.get(i);
								// the number sunshine is ok
								if(c.getPrice()<=sunShineNumber){
									plantObject=c.getCardPlant();
									plantObject.setX((int)x-(int)Globals.getOnePiceWidth()/2);
									plantObject.setY((int)(y-Globals.getOnePieceHeight()/2));
								}else{
									Toast.makeText(getContext(), "阳光数不足", Toast.LENGTH_LONG).show();
								}
								return false;
							}
						}
					}
				}else if(event.getAction()==MotionEvent.ACTION_MOVE){
					// moving
					
					if(plantObject!=null){
						plantObject.setX((int)(x-Globals.getOnePiceWidth()/2));
						plantObject.setY((int)(y-Globals.getOnePieceHeight()/2));
					}
					
				}else if(event.getAction()==MotionEvent.ACTION_UP){
					// set 
					
					if(p.x>0&&p.y>0){
						// to set on if no plant in 
					/*Iterator<Plant> pis=allPlant.iterator();
						while(pis.hasNext()){
							Plant plant=pis.next();
							// jude has pant
							if(plant.getPosition().equals(p.x,p.y)){
								// has plant
								Toast.makeText(getContext(), "当前位置已有植物", Toast.LENGTH_SHORT).show();
								plantObject=null;
								return false;
							}
						}*/
						growPlant(p);
						
					}
				}
				return false;
			}

			private void growPlant(Point p) {
				if(plantObject instanceof SunFlower){
					allPlant.add(new SunFlower(p.x, p.y));
					sunShineNumber-=50;
				}else if(plantObject instanceof Bean){
					allPlant.add(new Bean(p.x, p.y));
					sunShineNumber-=100;
				}else if(plantObject instanceof DoubleBean){
					allPlant.add(new DoubleBean(p.x, p.y));
					sunShineNumber-=200;
				}else if(plantObject instanceof IceBean){
					allPlant.add(new IceBean(p.x, p.y));
					sunShineNumber-=175;
				}else if(plantObject instanceof FourBean){
					allPlant.add(new FourBean(p.x, p.y));
					sunShineNumber-=250;
				}
				plantObject=null;
			}
		});
	}

	public MainView(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	//Toast.makeText(getContext(), "startD", duration)
		// 计算阳光倒计时
		if(sunShineCountDown==0){
			// 需要产生阳光 随机产生阳光的横坐标
			Random r=new Random();
			float x=Globals.getOnePiceWidth()+r.nextInt((int)Globals.getOnePiceWidth()*8);
			SunShine s=new SunShine(x, 0, false);
			allSunShine.add(s);
			sunShineCountDown=r.nextInt(frameTime*5);
		}
		sunShineCountDown--;
		Paint paint=new Paint();
		canvas.drawBitmap(ImageUils.getMainBg(), 0,Globals.getOnePieceHeight(), paint);
		
		// drawCard
		for(int i=0;i<allCard.size();i++){
			allCard.get(i).drawCard(canvas, paint, i);
		}
		
		//sunshine
		canvas.drawBitmap(ImageUils.getSunShineImage(), Globals.getOnePiceWidth()*6,0, paint);
		canvas.drawText(sunShineNumber+"",Globals.getOnePiceWidth()*6+10,20 , paint);
		
		// chanzi
		canvas.drawBitmap(ImageUils.getChanziImage(), Globals.getOnePiceWidth()*9,0, paint);
		
		if(plantObject!=null){
			plantObject.drawImage(canvas, paint);
		}
		// sunFlower
		Iterator<Plant> allPant=allPlant.iterator();
	    while(allPant.hasNext()){
	    	Plant sf=allPant.next();
	    	sf.drawImage(canvas, paint);
	    	sf.act(this);
	    	
	    	if(sf.checkDead()){
	    		allPant.remove();
	    	}
	    	
	    }
	    
	    // sunShine
	    Iterator<SunShine> si=allSunShine.iterator();
	    while(si.hasNext()){
	    	SunShine sunShine=si.next();
	    	sunShine.drawImage(canvas, paint);
	    	if(sunShine.move()){
	    		si.remove();
	    	}
	    }
	    
	    // zombie
	    Iterator<Zombie> zi=allZombie.iterator();
	    while(zi.hasNext()){
	    	Zombie z=zi.next();
	    	z.drawImage(canvas, paint);
	    	if(z.moveZombie(this)){
	    		zi.remove();
	    	};
	    }
	    
	    
	    //bullet
	    Iterator<Bullet> bi=allBullet.iterator();
	    while(bi.hasNext()){
	    	Bullet bt=bi.next();
	    	bt.drawImage(canvas, paint);
	    	if(bt.move(this)){
	    		bi.remove();
	    	}
	    }
	}

	public List<Plant> getAllPlant() {
		return allPlant;
	}

	public List<SunShine> getAllSunShine() {
		return allSunShine;
	}

	public List<Card> getAllCard() {
		return allCard;
	}

	public List<Zombie> getAllZombie() {
		return allZombie;
	}

	public List<Bullet> getAllBullet() {
		return allBullet;
	}
	
	
	

}
