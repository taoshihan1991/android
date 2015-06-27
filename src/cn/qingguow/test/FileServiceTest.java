package cn.qingguow.test;

import cn.qingguow.service.FileService;
import android.test.AndroidTestCase;
import android.util.Log;

public class FileServiceTest extends AndroidTestCase {
	private static final String TAG="FileServiceTest";
	public void testRead() throws Throwable{
		FileService service = new FileService(this.getContext());
		String result = service.read("test.txt");
		Log.i(TAG, result);
	}
}
