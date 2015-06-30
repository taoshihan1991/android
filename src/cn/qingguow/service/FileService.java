package cn.qingguow.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.R.integer;
import android.content.Context;
import android.os.Environment;

public class FileService {
	private Context context;
	
	public FileService(Context context) {
		super();
		this.context = context;
	}
	/*
	 * ���浽sd��
	 */
	public void saveToSdCard(String filename,String filecontent) throws Exception{
		File file = new File(Environment.getExternalStorageDirectory(),filename);
		FileOutputStream outputStream=new FileOutputStream(file);
		outputStream.write(filecontent.getBytes());
		outputStream.close();
	}
	public void save(String filename,String filecontent) throws Exception{
		//˽�в���ģʽ : �����������ļ�ֻ�ܱ���Ӧ�÷���,����ԭ�ļ�����
		FileOutputStream outputStream = context.openFileOutput(filename, context.MODE_PRIVATE);
		outputStream.write(filecontent.getBytes());
		outputStream.close();
	}
	/*
	 * ��ȡ�ļ�����
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
