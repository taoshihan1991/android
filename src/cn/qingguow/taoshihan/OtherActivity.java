package cn.qingguow.taoshihan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//����ʵ���������
		LinearLayout linearLayout=new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		@SuppressWarnings("deprecation")
		ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	
		TextView textView=new TextView(this);
		textView.setText("���Ǳ���ʵ�ֵ�");
		ViewGroup.LayoutParams textViewParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		
		linearLayout.addView(textView, textViewParams);
		//���ع�������
		linearLayout.addView(loadPartUi());
		setContentView(linearLayout,layoutParams);
//		setContentView(R.layout.activity_other);
//		
//		Intent intent=getIntent();
//		String website=(String)intent.getStringExtra("website");
//		int age=intent.getIntExtra("age", 0);
//		
//		TextView textView=(TextView)findViewById(R.id.other_hello);
//		textView.setText("��վ: "+website+",����"+age+"��");
		
	}
	public void closeActivity(View v){
		Intent data=new Intent();
		data.putExtra("result", "�ұ��ر���");
		setResult(30, data);
		finish();
	}
	/*
	 * ���ز��ֲ���
	 */
	private View loadPartUi(){
		LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		
		
		return inflater.inflate(R.layout.part, null);
	}
	/*
	 * �л�ͼƬ
	 */
	public void changeImage(View v){
		ImageView imageView=(ImageView) findViewById(R.id.back_img);
		LayerDrawable layerDrawable=(LayerDrawable) getResources().getDrawable(R.drawable.layer_list);
		Drawable drawable=getResources().getDrawable(R.drawable.softblog);
		layerDrawable.setDrawableByLayerId(R.id.on_img, drawable);
		imageView.setImageDrawable(layerDrawable);
	}

}
