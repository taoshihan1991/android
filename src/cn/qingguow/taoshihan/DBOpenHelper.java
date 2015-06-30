package cn.qingguow.taoshihan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "qingguow.db", null, 1);//默认<包>/database/
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//数据库第一次被创建的时候调用
		db.execSQL("create table person (id integer primary key autoincrement , name varchar(124))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {//数据库文件的版本号变更的时候调用
		// TODO Auto-generated method stub

	}

}
