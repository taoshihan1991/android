package cn.qingguow.taoshihan;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.qingguow.service.FileService;
import cn.qingguow.service.PersonService;

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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listView;
	private PersonService personService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    	for(Person person:persons2){
    		HashMap<String, Object> item=new HashMap<String, Object>();
    		item.put("name",person.name);
    		item.put("id",person.id);
    		data.add(item);
    	}
    	SimpleAdapter simpleAdapter=new SimpleAdapter(this, data, R.layout.item, new String[]{"name"}, new int[]{R.id.name});
    	listView.setAdapter(simpleAdapter);
    }
    
}
