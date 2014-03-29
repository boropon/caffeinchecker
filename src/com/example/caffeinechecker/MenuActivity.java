package com.example.caffeinechecker;

import java.util.List;
import java.util.ArrayList;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;

public class MenuActivity extends Activity {
	
	ListView listView;
	Button checkbutton;
	static List<CaffeinTarget> dataList = new ArrayList<CaffeinTarget>();
	static CaffeinTargetAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("MenuActivity", "onCreate");
		
		/* res/layout/activity_menu.xmlで定義したビューグループを読み込む。 */
		setContentView(R.layout.activity_menu);
		
		/* リストビューとCaffeinTargetをアダプタで接続する。 */
		listView = (ListView)findViewById(R.id.listview1);
		adapter = new CaffeinTargetAdapter();
		listView.setAdapter(adapter);
		
		/* チェックボタンに、クリックイベントリスナを設定する。 */
		checkbutton = (Button)findViewById(R.id.checkbutton);
		checkbutton.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Log.v("checkButton", "onClick");
				int total = getTotalCaffein();
				Intent intent = new Intent();
				intent.setClassName("com.example.caffeinechecker","com.example.caffeinechecker.ResultActivity");
				intent.putExtra("CAFFEIN", total);
				startActivity(intent);
			}
		});
		
		setDefaultDataList();
	}
	
	/* CaffeinTargetクラスのオブジェクトを作成し、dataListに追加する。 */
	protected void addItem(int id, String name, int caffein, int color) {
		dataList.add(new CaffeinTarget(id, name, caffein, color));
		adapter.notifyDataSetChanged();
	}
	
	/* デフォルトのdataListを作成する。 */
	protected void setDefaultDataList() {
		int COFFEE_COLOR = 0xff000000;
		int TEA_COLOR = 0xff990000;
		int GREENTEA_COLOR = 0xff333300;
		int URONTEA_COLOR = 0xff330000;
		int COLA_COLOR = 0xff660033;
		int OTHER_COLOR = 0xff555555;
		
		addItem(R.drawable.coffee_big, "コーヒー★ドリップ (マグカップ 220ml)", 150, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "コーヒー★ドリップ (コーヒーカップ 120ml)", 80, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "コーヒー★インスタント (マグカップ 220ml)", 100, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "コーヒー★インスタント (コーヒーカップ 120ml)", 50, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "コーヒー★缶 (大 350ml)", 240, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "コーヒー★缶 (小 190ml)", 130, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "コーヒー★ペットボトル (500ml)", 240, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "紅茶★ティーバッグ (ティーカップ 120ml)", 40, TEA_COLOR);
		addItem(R.drawable.coffee_big, "紅茶★ペットボトル (大 500ml)", 50, TEA_COLOR);
		addItem(R.drawable.coffee_big, "紅茶★ペットボトル (小 300ml)", 30, TEA_COLOR);
		addItem(R.drawable.coffee_big, "緑茶★ペットボトル (500ml)", 60, GREENTEA_COLOR);
		addItem(R.drawable.coffee_big, "緑茶★ペットボトル濃い目 (500ml)", 100, GREENTEA_COLOR);
		addItem(R.drawable.coffee_big, "烏龍茶★ペットボトル (500ml)", 75, URONTEA_COLOR);
		addItem(R.drawable.coffee_big, "コーラ★ペットボトル (500ml)", 50, COLA_COLOR);
		addItem(R.drawable.coffee_big, "コーラ★缶 (350ml)", 35, COLA_COLOR);
		addItem(R.drawable.coffee_big, "その他★眠気覚ましドリンク (50ml)", 120, OTHER_COLOR);
		addItem(R.drawable.coffee_big, "その他★元気ドリンク (100ml)", 50, OTHER_COLOR);		
	}
	
	/* カフェインの総量を算出する。 */
	protected int getTotalCaffein() {
		int total = 0;
		for (int i = 0; i < dataList.size(); ++i) {
			total += dataList.get(i).getCaffein() * dataList.get(i).getNum();
		}
		return total;
	}
	
	private class CaffeinTargetAdapter extends BaseAdapter{
		@Override
		public int getCount(){
			Log.v("CaffeinTargetAdapter", "getCount");
			return dataList.size();
		}
		
		@Override
		public Object getItem(int position) {
			Log.v("CaffeinTargetAdapter", "getItem");
			return dataList.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			Log.v("CaffeinTargetAdapter", "getItemId");
			return position;
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			Log.v("CaffeinTargetAdapter", "getView");
			ImageView imageView1;
			TextView textView1;
			TextView textView2;
			Button button1;
			Button button2;
			
			View view = convertView;
			if (view == null) {
				LayoutInflater inflater =
						(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.row, null);
			}
			
			final CaffeinTarget target = (CaffeinTarget)getItem(position);
			if (target != null) {
				imageView1 = (ImageView)view.findViewById(R.id.row_imageview1);
				imageView1.setImageResource(R.drawable.coffee_big);
				
				textView1 = (TextView)view.findViewById(R.id.row_textview1);
				textView1.setText(target.getName());
				textView1.setTextColor(target.getColor());
				
				textView2 = (TextView)view.findViewById(R.id.row_textview2);
				textView2.setText(String.valueOf(target.getNum()));
				
				/* 「+」ボタンはクリックされる毎に数字を増やし、99まで増える。 */
				button1 = (Button)view.findViewById(R.id.row_button1);
				button1.setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						Log.v("ClickListener_Button1", "onClick");
						int num = target.getNum();
						if (num < 99) {
							target.setNum(num + 1);
						}
						adapter.notifyDataSetChanged();
					}
				});
				
				/* 「-」ボタンはクリックされる毎に数字を減らし、0まで減る。 */
				button2 = (Button)view.findViewById(R.id.row_button2);
				button2.setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						Log.v("ClickListener_Button2", "onClick");
						int num = target.getNum();
						if (num > 0) {
							target.setNum(num - 1);
						}
						adapter.notifyDataSetChanged();
					}
				});
				
				int SELECTTEXT_COLOR = 0xff000000;
				int UNSELECTTEXT_COLOR = 0xffc0c0c0;
				if (target.getNum() == 0) {
					textView2.setTextColor(UNSELECTTEXT_COLOR);
				} else {
					textView2.setTextColor(SELECTTEXT_COLOR);
				}
			}
			return view;
		}
	}
}