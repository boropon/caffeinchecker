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
		
		/* res/layout/activity_menu.xml�Œ�`�����r���[�O���[�v��ǂݍ��ށB */
		setContentView(R.layout.activity_menu);
		
		/* ���X�g�r���[��CaffeinTarget���A�_�v�^�Őڑ�����B */
		listView = (ListView)findViewById(R.id.listview1);
		adapter = new CaffeinTargetAdapter();
		listView.setAdapter(adapter);
		
		/* �`�F�b�N�{�^���ɁA�N���b�N�C�x���g���X�i��ݒ肷��B */
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
	
	/* CaffeinTarget�N���X�̃I�u�W�F�N�g���쐬���AdataList�ɒǉ�����B */
	protected void addItem(int id, String name, int caffein, int color) {
		dataList.add(new CaffeinTarget(id, name, caffein, color));
		adapter.notifyDataSetChanged();
	}
	
	/* �f�t�H���g��dataList���쐬����B */
	protected void setDefaultDataList() {
		int COFFEE_COLOR = 0xff000000;
		int TEA_COLOR = 0xff990000;
		int GREENTEA_COLOR = 0xff333300;
		int URONTEA_COLOR = 0xff330000;
		int COLA_COLOR = 0xff660033;
		int OTHER_COLOR = 0xff555555;
		
		addItem(R.drawable.coffee_big, "�R�[�q�[���h���b�v (�}�O�J�b�v 220ml)", 150, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "�R�[�q�[���h���b�v (�R�[�q�[�J�b�v 120ml)", 80, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "�R�[�q�[���C���X�^���g (�}�O�J�b�v 220ml)", 100, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "�R�[�q�[���C���X�^���g (�R�[�q�[�J�b�v 120ml)", 50, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "�R�[�q�[���� (�� 350ml)", 240, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "�R�[�q�[���� (�� 190ml)", 130, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "�R�[�q�[���y�b�g�{�g�� (500ml)", 240, COFFEE_COLOR);
		addItem(R.drawable.coffee_big, "�g�����e�B�[�o�b�O (�e�B�[�J�b�v 120ml)", 40, TEA_COLOR);
		addItem(R.drawable.coffee_big, "�g�����y�b�g�{�g�� (�� 500ml)", 50, TEA_COLOR);
		addItem(R.drawable.coffee_big, "�g�����y�b�g�{�g�� (�� 300ml)", 30, TEA_COLOR);
		addItem(R.drawable.coffee_big, "�Β����y�b�g�{�g�� (500ml)", 60, GREENTEA_COLOR);
		addItem(R.drawable.coffee_big, "�Β����y�b�g�{�g���Z���� (500ml)", 100, GREENTEA_COLOR);
		addItem(R.drawable.coffee_big, "�G�������y�b�g�{�g�� (500ml)", 75, URONTEA_COLOR);
		addItem(R.drawable.coffee_big, "�R�[�����y�b�g�{�g�� (500ml)", 50, COLA_COLOR);
		addItem(R.drawable.coffee_big, "�R�[������ (350ml)", 35, COLA_COLOR);
		addItem(R.drawable.coffee_big, "���̑������C�o�܂��h�����N (50ml)", 120, OTHER_COLOR);
		addItem(R.drawable.coffee_big, "���̑������C�h�����N (100ml)", 50, OTHER_COLOR);		
	}
	
	/* �J�t�F�C���̑��ʂ��Z�o����B */
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
				
				/* �u+�v�{�^���̓N���b�N����閈�ɐ����𑝂₵�A99�܂ő�����B */
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
				
				/* �u-�v�{�^���̓N���b�N����閈�ɐ��������炵�A0�܂Ō���B */
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