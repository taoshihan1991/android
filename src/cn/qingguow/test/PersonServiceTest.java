package cn.qingguow.test;

import junit.framework.Assert;
import cn.qingguow.service.PersonService;
import cn.qingguow.taoshihan.Person;
import android.R.integer;
import android.test.AndroidTestCase;
import android.util.Log;
/*
 * 单元测试案例
 */
public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "PersonServiceTest";
	public void testSave() throws Exception{
		PersonService p=new PersonService(this.getContext());
		p.save("taoshihan1");
	}
	public void testDelete() throws Exception{


	}
	public void testFind() throws Exception{
		PersonService p=new PersonService(this.getContext());
		Person person=p.find(3);
		Log.i(TAG, person.toString());
	}
	public void testUpdate() throws Exception{
		PersonService p=new PersonService(this.getContext());
		p.update(3,"我成功了");
	}
	public void testGetscollData() throws Exception{

	}
	public void testCount() throws Exception{
		PersonService p=new PersonService(this.getContext());
		Long l=p.getCount();
		Log.i(TAG, "总条数:"+l);
	}
}
