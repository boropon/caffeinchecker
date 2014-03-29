package com.example.caffeinechecker;

import android.app.Activity;
import android.util.Log;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

public class ResultActivity extends Activity {
	
	private int caffein = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("ResultActivity", "onCreate");
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(linearLayout);
		
		Intent intent = getIntent();
		if (intent != null) {
			caffein = intent.getIntExtra("CAFFEIN", 0);
		}
		
		TextView textView = new TextView(this);
		int percent = calcCaffeinPercent(caffein);
		textView.setText(String.valueOf(percent));
		
		ImageView imageView = new ImageView(this);
		int imageid = getImageId(percent);
		imageView.setImageResource(imageid);
		
		linearLayout.addView(textView);
		linearLayout.addView(imageView);
	}
	
	protected int calcCaffeinPercent(int caffein) {
		int UNSAFELEVEL = 1200;
		int percent;
		
		percent = caffein * 100 / UNSAFELEVEL / 10 * 10;
		if (percent > 100) {
			percent = 100;
		}
		
		return percent;
	}
	
	protected int getImageId(int percent) {
		int imageid;
		if (percent < 10) {
			imageid = R.drawable.checker_0per_fit;
		} else if (percent < 20) {
			imageid = R.drawable.checker_10per_fit;
		} else if (percent < 30) {
			imageid = R.drawable.checker_20per_fit;
		} else if (percent < 40) {
			imageid = R.drawable.checker_30per_fit;
		} else if (percent < 50) {
			imageid = R.drawable.checker_40per_fit;
		} else if (percent < 60) {
			imageid = R.drawable.checker_50per_fit;
		} else if (percent < 70) {
			imageid = R.drawable.checker_60per_fit;
		} else if (percent < 80) {
			imageid = R.drawable.checker_70per_fit;
		} else if (percent < 90) {
			imageid = R.drawable.checker_80per_fit;
		} else if (percent < 100) {
			imageid = R.drawable.checker_90per_fit;
		} else {
			imageid = R.drawable.checker_100per_fit;
		}
		return imageid;
	}
}
