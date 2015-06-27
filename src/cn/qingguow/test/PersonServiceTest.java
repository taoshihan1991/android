package cn.qingguow.test;

import junit.framework.Assert;
import cn.qingguow.service.PersonService;
import android.R.integer;
import android.test.AndroidTestCase;
/*
 * µ¥Ôª²âÊÔ°¸Àý
 */
public class PersonServiceTest extends AndroidTestCase {
	public void testSave() throws Exception{
		PersonService p=new PersonService();
		p.save(null);
	}
	public void testAdd(){
		PersonService p = new PersonService();
		int result=p.add(1,2);
		Assert.assertEquals(3, result);
	}
}
