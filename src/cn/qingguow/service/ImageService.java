package cn.qingguow.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;



import cn.qingguow.utils.StreamTool;

public class ImageService {
	/*
	 * 获取网络图片的数据
	 */
	public static byte[] getImage(String imageUrl) throws Exception {
		
		URL url=new URL(imageUrl);
		HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
		httpURLConnection.setConnectTimeout(10000);
		httpURLConnection.setRequestMethod("GET");
		
		if(httpURLConnection.getResponseCode()==200){
			InputStream inputStream=httpURLConnection.getInputStream();
		
			byte[] data=StreamTool.read(inputStream);
			return data;
		}
		return null;
	}

}
