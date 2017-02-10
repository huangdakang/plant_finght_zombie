package org.huang.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Card {
	
	// card 的价格
	private int price;
	
	//Plant 
	private Plant cardPlant;
	
	public Card(int type) {
		// TODO Auto-generated constructor stub
		if(type==0){
			// sunflower
			price =50;
			cardPlant=new SunFlower(0, 0);
		}else if(type==1){
			// beans
			price =100;
			cardPlant=new Bean(0,0);
		}else if(type==2){
			// doublebeans
			price= 200;
			cardPlant=new DoubleBean(0 , 0);
		}else if(type==3){
			// ice bean
			price= 175;
			cardPlant=new IceBean(0 , 0);
		}else if(type==4){
			// four bean
			price= 250;
			cardPlant=new FourBean(0 , 0);
		}
	}
	
	// useed to draw card
	public void drawCard(Canvas canvas,Paint paint,int index){
		cardPlant.drawCard(canvas, paint, index);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Plant getCardPlant() {
		return cardPlant;
	}

	public void setCardPlant(Plant cardPlant) {
		this.cardPlant = cardPlant;
	}
	
	

}
