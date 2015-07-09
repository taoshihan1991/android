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
		//编码实现软件界面
		LinearLayout linearLayout=new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		@SuppressWarnings("deprecation")
		ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	
		TextView textView=new TextView(this);
		textView.setText("我是编码实现的");
		ViewGroup.LayoutParams textViewParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		
		linearLayout.addView(textView, textViewParams);
		//加载公共部分
		linearLayout.addView(loadPartUi());
		setContentView(linearLayout,layoutParams);
//		setContentView(R.layout.activity_other);
//		
//		Intent intent=getIntent();
//		String website=(String)intent.getStringExtra("website");
//		int age=intent.getIntExtra("age", 0);
//		
//		TextView textView=(TextView)findViewById(R.id.other_hello);
//		textView.setText("网站: "+website+",成立"+age+"年");
		
	}
	public void closeActivity(View v){
		Intent data=new Intent();
		data.putExtra("result", "我被关闭了");
		setResult(30, data);
		finish();
	}
	/*
	 * 加载部分布局
	 */
	private View loadPartUi(){
		LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		
		
		return inflater.inflate(R.layout.part, null);
	}
	/*
	 * 切换图片
	 */
	public void changeImage(View v){
		ImageView imageView=(ImageView) findViewById(R.id.back_img);
		LayerDrawable layerDrawable=(LayerDrawable) getResources().getDrawable(R.drawable.layer_list);
		Drawable drawable=getResources().getDrawable(R.drawable.softblog);
		layerDrawable.setDrawableByLayerId(R.id.on_img, drawable);
		imageView.setImageDrawable(layerDrawable);
	}

}
