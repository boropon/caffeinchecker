package com.example.caffeinechecker;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.os.Handler;

public class MainActivity extends Activity {

	/* �A�N�e�B�r�e�B�J�n���ɃR�[�������B */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("LifeCycle", "onCreate");
        
        /* res/layout/activity_main.xml�Œ�`�����r���[��ǂݍ��ށB */
        setContentView(R.layout.activity_main);
        
        /* 5000ms��ɁA���j���[�iMenuActivity�j��\������ */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
        	@Override
        	public void run(){
        		startActivity(new Intent(MainActivity.this, MenuActivity.class));
        		finish();
        	}
        }, 5000);
    }
    
    /* onCreate���\�b�h�̎��ɃR�[�������B
     * ��U���̃A�N�e�B�r�e�B�̗��ɉB��Č����Ȃ��Ȃ��āA�ēx�\�������ꍇ���R�[�������B */
    @Override
    protected void onStart(){
    	super.onStart();
    	Log.v("LifeCycle", "onStart");
    }
    
    /* onStart���\�b�h�̎��ɃR�[�������B
     * ���̃A�N�e�B�r�e�B���J�n���ꂽ��A�ēx�\�������ꍇ���R�[�������B */
    @Override
    protected void onResume(){
    	super.onStart();
    	Log.v("LifeCycle", "onResume");
    }
    
    /* ���̃A�N�e�B�r�e�B���J�n����ƃR�[�������B */
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
    
    /* ���̃A�N�e�B�r�e�B���J�n���ꂽ���Ƃɂ���āA�����Ȃ��Ȃ����ꍇ�ɃR�[�������B */
    @Override
    protected void onStop(){
    	super.onStart();
    	Log.v("LifeCycle", "onStop");
    }
    
    /* �A�N�e�B�r�e�B�̏I�����ɃR�[�������B */
    @Override
    protected void onDestroy(){
    	super.onStart();
    	Log.v("LifeCycle", "onDestroy");
    }
    
}
