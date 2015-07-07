package cn.qingguow.taoshihan;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.qingguow.taoshihan.R.id;
import cn.qingguow.utils.StreamTool;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SingleTaskActivity extends Activity {
	public EditText article_title;
	public EditText article_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_singletask);
		
		article_title=(EditText)findViewById(R.id.article_title);
		article_content=(EditText)findViewById(R.id.article_content);
	}
	/*
	 * 添加文章
	 */
	public void saveArticle(View v){
		String articleTitle=article_title.getText().toString();
		String articleContent=article_content.getText().toString();
		Map<String, String> data=new HashMap<String, String>();
		data.put("articleTitle", articleTitle);
		data.put("articleContent", articleContent);
		
		String path="http://www.qingguow.cn/index.php/index/appSaveArticle/";
		try {
			String resString=sendGetRequest(path,data);
			Toast.makeText(this, resString, 1000).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "发送失败1", 1000).show();
		}
	}
	/*
	 * 发送get请求
	 */
	private String sendGetRequest(String path, Map<String, String> data) throws Exception {
		
//		// TODO Auto-generated method stub
		//http://www.qingguow.cn/index.php/index/appSaveArticle/title/xxx/content/xxx
		@SuppressWarnings("unused")
		StringBuilder urlBuilder=new StringBuilder(path);
		for(Map.Entry<String,String> me:data.entrySet()){
			String keyString=me.getKey();
			String valueString=me.getValue();
			urlBuilder.append(keyString).append("/").append(valueString).append("/");
		}
		urlBuilder.deleteCharAt(urlBuilder.length()-1);
		String url=urlBuilder.toString();

		
		HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==200){
			InputStream inputStream=conn.getInputStream();
			byte[] resData=StreamTool.read(inputStream);
			String resStr=new String(resData);
			
			return resStr;
		}
		return null;
	}
}
