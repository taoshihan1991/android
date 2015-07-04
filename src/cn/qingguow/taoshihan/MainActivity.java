package cn.qingguow.taoshihan;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.qingguow.service.FileService;
import cn.qingguow.service.ImageService;
import cn.qingguow.service.NewsService;
import cn.qingguow.service.PersonService;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listView;
	private PersonService personService;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
    	//解决主线程中不能访问网络
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
    	
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
        personService=new PersonService(this);
        listView=(ListView) this.findViewById(R.id.listView);
        show();
        //show2();
        
        //网络图片查看器
        Button imageBtn=(Button)findViewById(R.id.image_path_btn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText imagePath=(EditText) findViewById(R.id.imagepath);
				String imageUrl=String.valueOf(imagePath.getText());
				byte[] data = null;
				try {
					
					data = ImageService.getImage(imageUrl);
					Bitmap bitmap=BitmapFactory.decodeByteArray(data,0, data.length);
					
					ImageView imageView=(ImageView)findViewById(R.id.image_path_show);
					imageView.setImageBitmap(bitmap);//显示图片
				} catch (Exception e) {
					e.printStackTrace();
					 Log.e("taoshihan", "Exception: "+Log.getStackTraceString(e));
					Toast.makeText(getApplicationContext(), R.string.get_image_fail, 1).show();
				}
				
			}
		});
        //文章列表显示
        NewsService newsService=new NewsService();
        @SuppressWarnings("unused")
		ListView listViewNews=(ListView)findViewById(R.id.listViewNews);
        try {
        	List<News> listNews= newsService.getJsonNews();
//        	List<News> listNews= new ArrayList<News>();
//        	listNews.add(new News(1, "aaaaa"));
//        	listNews.add(new News(1, "aaaaa"));
//        	listNews.add(new News(1, "aaaaa"));
	    	List<HashMap<String, Object>> data=new ArrayList<HashMap<String, Object>>();
	    	for(News news:listNews){
	    		HashMap<String, Object> item=new HashMap<String, Object>();
	    		item.put("title",news.title);
	    		data.add(item);
	    	}
	    	SimpleAdapter simpleAdapter=new SimpleAdapter(this, data, R.layout.news, new String[]{"title"}, new int[]{R.id.title});
	    	listView.setAdapter(simpleAdapter);
		} catch (Exception e) {

			e.printStackTrace();
			 Log.e("haha", "Exception: "+Log.getStackTraceString(e));
		}
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
    @SuppressWarnings("deprecation")
	private void show2() {
    	Cursor cursor=personService.getCursorScollData(0, 10);
		SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this, R.layout.item, cursor, new String[]{"name"}, new int[]{R.id.name});
    	listView.setAdapter(simpleCursorAdapter);	
	}
    
    /*
     * 显示列表
     */
    public void show(){
    	List<Person> persons=personService.getScollData(0, 10);
    	List<Person> persons2=new ArrayList<Person>();
    	persons2.add(new Person(1, "test1"));
    	persons2.add(new Person(2, "test2"));
    	persons2.add(new Person(2, "test2"));
    	persons2.add(new Person(2, "test2"));
    	persons2.add(new Person(2, "test2"));
    	persons2.add(new Person(2, "test2"));
    	List<HashMap<String, Object>> data=new ArrayList<HashMap<String, Object>>();
    	for(Person person:persons){
    		HashMap<String, Object> item=new HashMap<String, Object>();
    		item.put("name",person.name);
    		item.put("id",person.id);
    		data.add(item);
    	}
    	SimpleAdapter simpleAdapter=new SimpleAdapter(this, data, R.layout.item, new String[]{"name"}, new int[]{R.id.name});
    	listView.setAdapter(simpleAdapter);
    }
    
}
