package cn.qingguow.taoshihan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "qingguow.db", null, 1);//Ĭ��<��>/database/
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//���ݿ��һ�α�������ʱ�����
		db.execSQL("create table person (id integer primary key autoincrement , name varchar(124))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {//���ݿ��ļ��İ汾�ű����ʱ�����
		// TODO Auto-generated method stub

	}

}
