package com.example.pvzdemon;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;
import org.huang.utils.MusicUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Globals.init(this);
        ImageUils.init(getResources(), getAssets());
        MusicUtils.init(this);
        
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	MusicUtils.closeBg();
    }


  
}
