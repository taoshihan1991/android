package cn.qingguow.taoshihan;

import java.security.PublicKey;

import cn.qingguow.service.FileService;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)this.findViewById(R.id.tel_btn);
        button.setOnClickListener(new ButtonClickListener());
        
        //保存到文件
        Button fileBtn=(Button)this.findViewById(R.id.file_btn);
        fileBtn.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		//保存文件
        		EditText filename=(EditText) findViewById(R.id.filename);
        		EditText filecontent=(EditText) findViewById(R.id.filecontent);
        		String filenameText = filename.getText().toString();
        		String filecontentText = filecontent.getText().toString();
        		FileService Service=new FileService(getApplicationContext());
        		try {
        			//判断sd卡是否存在或者可以读写
        			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
        				Service.saveToSdCard(filenameText,filecontentText);
        				Toast.makeText(getApplicationContext(), R.string.success, 1).show();
        			}else{
        				Toast.makeText(getApplicationContext(), R.string.noexist, 1).show();
        			}
            				
    			} catch (Exception e) {
    				Toast.makeText(getApplicationContext(), R.string.fail, 1).show();
    			}

        	}
        });
        //读取文件
        
        //listview显示数据
        listView=(ListView) this.findViewById(R.id.listView);
        show();
    }



    
    private final class ButtonClickListener implements View.OnClickListener{
    	public void onClick(View v){
    		EditText mobile=(EditText)MainActivity.this.findViewById(R.id.mobile_input);
    		String tel=mobile.getText().toString();
    		//验证电话号码是否可用
    		Intent intent=new Intent();
    		intent.setAction("android.intent.action.CALL");
    		intent.setData(Uri.parse("tel:"+tel));
    		startActivity(intent);

    	}
    }
    
    /*
     * 显示列表
     */
    public void show(){
    	
    }
    
}
