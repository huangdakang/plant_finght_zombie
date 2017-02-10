package com.example.pvzdemon;

import org.huang.utils.Globals;
import org.huang.utils.ImageUils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Globals.init(this);
        ImageUils.init(getResources(), getAssets());
        setContentView(R.layout.activity_main);
    }


  
}
