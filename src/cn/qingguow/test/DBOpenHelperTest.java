package cn.qingguow.test;

import cn.qingguow.taoshihan.DBOpenHelper;
import android.test.AndroidTestCase;

public class DBOpenHelperTest extends AndroidTestCase {
	public void testCreateDb() throws Exception{
		DBOpenHelper db=new DBOpenHelper(getContext());
		db.getWritableDatabase();
	}
}
