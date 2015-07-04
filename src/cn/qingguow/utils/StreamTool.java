package cn.qingguow.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.R.integer;

public class StreamTool {
	/*
	 * 读取输入流中的数据
	 */
	public static byte[] read(InputStream inputStream) throws Exception {
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[50000];
		int len=0;
		while((len=inputStream.read(buffer))!=-1){
			outputStream.write(buffer,0,len);
		};
		inputStream.close();
		return outputStream.toByteArray();
	}

}
