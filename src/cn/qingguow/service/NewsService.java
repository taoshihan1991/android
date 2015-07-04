package cn.qingguow.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.qingguow.taoshihan.News;
import cn.qingguow.utils.StreamTool;

public class NewsService {
	public static List<News> getJsonNews() throws Exception{
		URL url=new URL("http://www.qingguow.cn/index.php/index/getJsonArticle/");
		HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
		httpURLConnection.setConnectTimeout(10000);
		httpURLConnection.setRequestMethod("GET");
		
		if(httpURLConnection.getResponseCode()==200){
			InputStream inputStream=httpURLConnection.getInputStream();
	
			List list=getJsonData(inputStream);
			return list;
		}
	
		return null;
	}
	public static List<News> getJsonData (InputStream inputStream) throws Exception {
		List<News> list=new ArrayList<News>();
		byte[] data=StreamTool.read(inputStream);
		@SuppressWarnings("unused")
		String json=new String(data);
		JSONArray array=new JSONArray(json);
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject=array.getJSONObject(i);
			
			News news= new News(jsonObject.getInt("id"),jsonObject.getString("title"));
			list.add(news);
		}
		return list;
	}
}
