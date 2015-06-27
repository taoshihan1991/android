package cn.qingguow.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.R.integer;
import android.content.Context;

public class FileService {
	private Context context;
	
	public FileService(Context context) {
		super();
		this.context = context;
	}

	public void save(String filename,String filecontent) throws Exception{
		//私有操作模式 : 创建出来的文件只能被本应用访问,覆盖原文件内容
		FileOutputStream outputStream = context.openFileOutput(filename, context.MODE_PRIVATE);
		outputStream.write(filecontent.getBytes());
		outputStream.close();
	}
	/*
	 * 读取文件内容
	 */
	public String read(String filename) throws Exception{
		FileInputStream inStream = context.openFileInput(filename);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len = 0;
		while((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		byte[] data=outStream.toByteArray();
		
		
		return new String(data);
	}
}
