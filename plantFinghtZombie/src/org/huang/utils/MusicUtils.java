package org.huang.utils;

import java.util.HashMap;
import java.util.Map;

import com.example.pvzdemon.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class MusicUtils {
	private  static MediaPlayer bgMuisc;
	
	private  static SoundPool pool;
	
	private static Map<String,Integer> soundIds=new HashMap<String ,Integer>();
	
	public static void init(Context ctx){
		try {
			bgMuisc=MediaPlayer.create(ctx, R.raw.crazydave);
			bgMuisc.setLooping(true);
			pool=new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
			soundIds.put("准备", pool.load(ctx, R.raw.readysetplant, 1));
			soundIds.put("僵尸出现", pool.load(ctx, R.raw.awooga,1));
			soundIds.put("僵尸攻击1", pool.load(ctx,R.raw.chomp,1));
			soundIds.put("僵尸攻击2", pool.load(ctx, R.raw.chomp,1));
			soundIds.put("最后一波",pool.load(ctx,R.raw.finalwave,1));
			soundIds.put("过关",pool.load(ctx, R.raw.winmusic,1));
			soundIds.put("失败", pool.load(ctx, R.raw.losemusic,1));
			soundIds.put("种植", pool.load(ctx, R.raw.plant2,1));
			soundIds.put("击中", pool.load(ctx, R.raw.splat2, 1));
			soundIds.put("阳光", pool.load(ctx, R.raw.points,1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playSound(String name){
		pool.play(soundIds.get(name), 1, 1, 1, 0, 1);
	}
	
	public static void startBg(){
		bgMuisc.start();
	}
	
	public static void pauseBg(){
		bgMuisc.pause();
	}
	
	public static void closeBg(){
		try {
			if(bgMuisc!=null){
				bgMuisc.stop();
				bgMuisc.release();
			}
			pool.release();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

}
