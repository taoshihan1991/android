package cn.qingguow.taoshihan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		
		Intent intent=getIntent();
		String website=(String)intent.getStringExtra("website");
		int age=intent.getIntExtra("age", 0);
		
		TextView textView=(TextView)findViewById(R.id.other_hello);
		textView.setText("网站: "+website+",成立"+age+"年");
	}
	public void closeActivity(View v){
		Intent data=new Intent();
		data.putExtra("result", "我被关闭了");
		setResult(30, data);
		finish();
	}

}
