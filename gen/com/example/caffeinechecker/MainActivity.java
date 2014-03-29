package com.example.caffeinechecker;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.os.Handler;

public class MainActivity extends Activity {

	/* アクティビティ開始時にコールされる。 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("LifeCycle", "onCreate");
        
        /* res/layout/activity_main.xmlで定義したビューを読み込む。 */
        setContentView(R.layout.activity_main);
        
        /* 5000ms後に、メニュー（MenuActivity）を表示する */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
        	@Override
        	public void run(){
        		startActivity(new Intent(MainActivity.this, MenuActivity.class));
        		finish();
        	}
        }, 5000);
    }
    
    /* onCreateメソッドの次にコールされる。
     * 一旦他のアクティビティの裏に隠れて見えなくなって、再度表示される場合もコールされる。 */
    @Override
    protected void onStart(){
    	super.onStart();
    	Log.v("LifeCycle", "onStart");
    }
    
    /* onStartメソッドの次にコールされる。
     * 他のアクティビティが開始された後、再度表示される場合もコールされる。 */
    @Override
    protected void onResume(){
    	super.onStart();
    	Log.v("LifeCycle", "onResume");
    }
    
    /* 他のアクティビティが開始するとコールされる。 */
    @Override
    protected void onPause(){
    	super.onStart();
    	Log.v("LifeCycle", "onPause");
    }

    @Override
    protected void onRestart(){
    	super.onStart();
    	Log.v("LifeCycle", "onRestart");
    }
    
    /* 他のアクティビティが開始されたことによって、見えなくなった場合にコールされる。 */
    @Override
    protected void onStop(){
    	super.onStart();
    	Log.v("LifeCycle", "onStop");
    }
    
    /* アクティビティの終了時にコールされる。 */
    @Override
    protected void onDestroy(){
    	super.onStart();
    	Log.v("LifeCycle", "onDestroy");
    }
    
}
