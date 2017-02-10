package org.huang.utils;

import java.io.IOException;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.example.pvzdemon.R;

public class ImageUils {
	private static Bitmap mainBg;
	//images
	private static Bitmap[] sunFlowerImage;
	private static Bitmap[] beanImages;
	private static Bitmap[] iceBeanImages;
	private static Bitmap[] doubleBeanImages;
	private static Bitmap[] fourBeanImages;

	// card
	private static Bitmap sunFlowerCard;
	private static Bitmap beanCard;
	private static Bitmap iceBeanCard;
	private static Bitmap doubleBeanCard;
	private static Bitmap fourBeanCard;
	
	//bullet
	private static Bitmap normalBullet;
	private static Bitmap iceBullet;
	private static Bitmap fireBullet;
	private static Bitmap firBulletPlus;
	
	private static Bitmap allCardImage;
	
	private static Bitmap sunShineImage;
	
	private static Bitmap  chanziImage;
	
	private static Bitmap[] normalZombieImages;
	
	private static Bitmap[] zombieDeadImags;
	private static Bitmap[] hatZombieImages;
	private static Bitmap[] runZombieImages;
	private static Bitmap[] iconZombieImages;
	
	
	
	public static void init(Resources res,AssetManager manager){
		//获取主界面的北京
		mainBg=splitImage(
				changeBitmapSize(BitmapFactory.decodeResource(res, R.drawable.bk1), 
				Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT),0,(int)Globals.getOnePieceHeight(),
				Globals.SCREEN_WIDTH,(int)(Globals.getOnePieceHeight()*5));
		try {
			sunShineImage=changeBitmapSize(BitmapFactory.decodeStream(manager.open("other_image/sun_shine.png"))
					, Globals.getOnePiceWidth(), Globals.getOnePieceHeight());
		
			chanziImage=changeBitmapSize(BitmapFactory.decodeStream(manager.open("other_image/shovel_bk.png"))
					, Globals.getOnePiceWidth(), Globals.getOnePieceHeight());
			allCardImage = BitmapFactory.decodeStream(manager
					.open("other_image/seedpackets.jpg"));
			
			sunFlowerCard = splitCardImage(0);
			beanCard = splitCardImage(1);
			doubleBeanCard = splitCardImage(2);
			iceBeanCard = splitCardImage(3);
			fourBeanCard = splitCardImage(4);

			sunFlowerImage = splitToManyImage(
					BitmapFactory.decodeStream(manager
							.open("plant_image/plant_0.png")), 8,
					(int) Globals.getOnePieceHeight());
			beanImages = splitToManyImage(BitmapFactory.decodeStream(manager
					.open("plant_image/plant_1.png")), 5,
					(int) Globals.getOnePieceHeight());
			doubleBeanImages = splitToManyImage(
					BitmapFactory.decodeStream(manager
							.open("plant_image/plant_2.png")), 8,
					(int) Globals.getOnePieceHeight());
			iceBeanImages = splitToManyImage(BitmapFactory.decodeStream(manager
					.open("plant_image/plant_3.png")), 8,
					(int) Globals.getOnePieceHeight());
			fourBeanImages = splitToManyImage(
					BitmapFactory.decodeStream(manager
							.open("plant_image/plant_4.png")), 8,
					(int) Globals.getOnePieceHeight());
			
		    //bullet
			
			normalBullet=changeBitmapSize(BitmapFactory.decodeStream(manager.open("other_image/bullet_0.png")),
					Globals.getOnePiceWidth()/2, Globals.getOnePieceHeight()/2);
			iceBullet=changeBitmapSize(BitmapFactory.decodeStream(manager.open("other_image/bullet_1.png")),
					Globals.getOnePiceWidth()/2, Globals.getOnePieceHeight()/2);
			fireBullet=changeBitmapSize(BitmapFactory.decodeStream(manager.open("other_image/bullet_2.png")),
					Globals.getOnePiceWidth()/2, Globals.getOnePieceHeight()/2);
			firBulletPlus=changeBitmapSize(BitmapFactory.decodeStream(manager.open("other_image/bullet_4.png")),
					Globals.getOnePiceWidth()/2, Globals.getOnePieceHeight()/2);
			
			sunFlowerCard=BitmapFactory.decodeStream(manager.open("other_image/seedpackets.jpg"));
			sunFlowerCard=splitImage(sunFlowerCard, 0, 0, sunFlowerCard.getWidth()/27, sunFlowerCard.getHeight());
			
			sunFlowerCard=changeBitmapSize(sunFlowerCard, Globals.getOnePiceWidth()*2/3, Globals.getOnePieceHeight());
			
			sunFlowerImage=splitToManyImage(BitmapFactory.decodeStream(manager.open("plant_image/plant_0.png")),
					8, (int)Globals.getOnePieceHeight());
			
			normalZombieImages=splitToManyImage(BitmapFactory.decodeStream(manager.open("zombie_image/zomb_0.png")),
					8, (int)(Globals.getOnePieceHeight()*1.25));
			hatZombieImages=splitToManyImage(BitmapFactory.decodeStream(manager.open("zombie_image/zomb_1.png")),
					8, (int)(Globals.getOnePieceHeight()*1.25));
			iconZombieImages=splitToManyImage(BitmapFactory.decodeStream(manager.open("zombie_image/zomb_2.png")),
					8, (int)(Globals.getOnePieceHeight()*1.25));
			runZombieImages=splitToManyImage(BitmapFactory.decodeStream(manager.open("zombie_image/zomb_3.png")),
					8, (int)(Globals.getOnePieceHeight()*1.65));
			
			//deadImgs
			zombieDeadImags=splitToManyImage(BitmapFactory.decodeStream(manager.open("zombie_image/zomb_d_0.png")),
					4, (int)(Globals.getOnePieceHeight()*1.25));
			hatZombieImages=splitToManyImage(BitmapFactory.decodeStream(manager.open("zombie_image/zomb_d_1.png")),
					4, (int)(Globals.getOnePieceHeight()*1.25));
			runZombieImages=splitToManyImage(BitmapFactory.decodeStream(manager.open("zombie_image/zomb_d_2.png")),
					4, (int)(Globals.getOnePieceHeight()*1.25));
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	private static Bitmap splitCardImage(int index) {
		Bitmap temp = splitImage(allCardImage, index * allCardImage.getWidth()
				/ 27, 0, allCardImage.getWidth() / 27, allCardImage.getHeight());
		return changeBitmapSize(temp, Globals.getOnePiceWidth() * 2 / 3,
				Globals.getOnePieceHeight());
	}
	/**
	 * 缩放图片的方法
	 * @param src
	 * @param toWidth
	 * @param toHeight
	 * @return
	 */
	public static Bitmap changeBitmapSize(Bitmap src,float toWidth,float toHeight){
		Matrix m=new Matrix();
		m.postScale(toWidth/src.getWidth(), toHeight/src.getHeight());
		Bitmap result=Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
		return result;
	}
	
	/**
	 * 拆分图片中的某一部风  生成新的图片
	 * @param src
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap splitImage (Bitmap src ,int startX,int startY,int width,int height){
		Bitmap result =Bitmap.createBitmap(src, startX, startY, width, height);
	    return result;
	}
	
	public static Bitmap[] splitToManyImage(Bitmap src ,int count,int toHeight){
		Bitmap[] result =new Bitmap[count];
		// caculate every img width
		float overWidth =src.getWidth()*1.0f/count;
		
		for(int i=0;i<count;i++){
			result[i]=changeBitmapSize(
					splitImage(src, (int)overWidth*i, 0,(int)overWidth, src.getHeight()),
					Globals.getOnePiceWidth(), toHeight);
		}
		
		return result;
	}


	public static Bitmap getMainBg() {
		return mainBg;
	}


	public static Bitmap getSunFlowerImage(int index) {
		return sunFlowerImage[index];
	}


	public static Bitmap getSunFlowerCard() {
		return sunFlowerCard;
	}


	public static Bitmap getSunShineImage() {
		return sunShineImage;
	}


	public static Bitmap getChanziImage() {
		return chanziImage;
	}


	public static Bitmap getNormalZombieImages(int index) {
		return normalZombieImages[index];
	}

	

	public static Bitmap getBeanImages(int index) {
		return beanImages[index];
	}

	public static Bitmap getIceBeanImages(int index) {
		return iceBeanImages[index];
	}

	public static Bitmap getDoubleBeanImages(int index) {
		return doubleBeanImages[index];
	}

	public static Bitmap getFourBeanImages(int index) {
		return fourBeanImages[index];
	}

	public static Bitmap getBeanCard() {
		return beanCard;
	}

	public static Bitmap getIceBeanCard() {
		return iceBeanCard;
	}

	public static Bitmap getDoubleBeanCard() {
		return doubleBeanCard;
	}

	public static Bitmap getFourBeanCard() {
		return fourBeanCard;
	}

	public static Bitmap getNormalBullet() {
		return normalBullet;
	}

	public static Bitmap getIceBullet() {
		return iceBullet;
	}

	public static Bitmap getFireBullet() {
		return fireBullet;
	}

	public static Bitmap getFirBulletPlus() {
		return firBulletPlus;
	}

	public static Bitmap getZombieDeadImags(int index) {
		return zombieDeadImags[index];
	}

	public static Bitmap getHatZombieImages(int index) {
		return hatZombieImages[index];
	}

	public static Bitmap getRunZombieImages(int index) {
		return runZombieImages[index];
	}

	public static Bitmap getIconZombieImages(int index) {
		return iconZombieImages[index];
	}


	
	
	

}
