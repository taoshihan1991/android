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
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    	//������߳��в��ܷ�������
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
    	
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������
        setContentView(R.layout.activity_main);
        
        //�����⶯��
       Animation mainTitleAnimation=AnimationUtils.loadAnimation(this, R.anim.alpha);
        //Animation mainTitleAnimation=AnimationUtils.loadAnimation(this, R.anim.translate);
       // Animation mainTitleAnimation=AnimationUtils.loadAnimation(this, R.anim.scale);
        TextView mainTitleTextView=(TextView)findViewById(R.id.main_title);
        mainTitleTextView.startAnimation(mainTitleAnimation);
        
        Button button=(Button)this.findViewById(R.id.tel_btn);
        button.setOnClickListener(new ButtonClickListener());
        
        //���浽�ļ�
        Button fileBtn=(Button)this.findViewById(R.id.file_btn);
        fileBtn.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		//�����ļ�
        		EditText filename=(EditText) findViewById(R.id.filename);
        		EditText filecontent=(EditText) findViewById(R.id.filecontent);
        		String filenameText = filename.getText().toString();
        		String filecontentText = filecontent.getText().toString();
        		FileService Service=new FileService(getApplicationContext());
        		try {
        			//�ж�sd���Ƿ���ڻ��߿��Զ�д
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
        //��ȡ�ļ�
        
        //listview��ʾ����
        personService=new PersonService(this);
        listView=(ListView) this.findViewById(R.id.listView);
        show();
        //show2();
        
        //����ͼƬ�鿴��
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
					imageView.setImageBitmap(bitmap);//��ʾͼƬ
				} catch (Exception e) {
					e.printStackTrace();
					 Log.e("taoshihan", "Exception: "+Log.getStackTraceString(e));
					Toast.makeText(getApplicationContext(), R.string.get_image_fail, 1).show();
				}
				
			}
		});

    }
    /*
     * ����json����
     */
    public void loadNewsList(View v){
    	 //�����б���ʾ
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
	    	listViewNews.setAdapter(simpleAdapter);
		} catch (Exception e) {
			Toast.makeText(this, R.string.load_news_fail, 1).show();
			e.printStackTrace();
			 Log.e("haha", "Exception: "+Log.getStackTraceString(e));
		}
    }

    





	private final class ButtonClickListener implements View.OnClickListener{
    	public void onClick(View v){
    		EditText mobile=(EditText)MainActivity.this.findViewById(R.id.mobile_input);
    		String tel=mobile.getText().toString();
    		//��֤�绰�����Ƿ����
    		Intent intent=new Intent();
    		intent.setAction("android.intent.action.CALL");
    		intent.setData(Uri.parse("tel:"+tel));
    		startActivity(intent);

    	}
    }
    /*
     * ��ʾ�б�
     */
    @SuppressWarnings("deprecation")
	private void show2() {
    	Cursor cursor=personService.getCursorScollData(0, 10);
		SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this, R.layout.item, cursor, new String[]{"name"}, new int[]{R.id.name});
    	listView.setAdapter(simpleCursorAdapter);	
	}
    
    /*
     * ��ʾ�б�
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
    /*
     * ���µ�activity
     */
    public void openActivity(View v){
    	Intent intent=new Intent();
    	intent.setClass(this, OtherActivity.class);
    	intent.putExtra("website", "�����");
    	intent.putExtra("age", 2);
    	//startActivity(intent);
    	startActivityForResult(intent, 200);
    	this.overridePendingTransition(R.anim.top_to, R.anim.bottom_to);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String result=data.getStringExtra("result");
		Toast.makeText(this, result, 1).show();
	}
    /*
     * ��SingleTask��activity
     */
    public void openSingleTaskActivity(View v){
    	startActivity(new Intent(this,SingleTaskActivity.class));
    	this.overridePendingTransition(R.anim.right_left, R.anim.left_right);
    }
    
}
