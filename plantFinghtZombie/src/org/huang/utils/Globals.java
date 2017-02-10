package org.huang.utils;

import android.app.Activity;

public class Globals {
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	private static final float WIDTH_PIECE=10;
	private static final float HEIGHT_PIECE=6;
	
	public static void init(Activity a){
		SCREEN_HEIGHT=a.getWindowManager().getDefaultDisplay().getHeight();
		SCREEN_WIDTH=a.getWindowManager().getDefaultDisplay().getWidth();
	}
	
	public static float getOnePiceWidth(){
		return SCREEN_WIDTH/WIDTH_PIECE;
	}
	
	public  static float getOnePieceHeight(){
		return SCREEN_HEIGHT/HEIGHT_PIECE;
	}

}
