package cn.qingguow.service;

import java.util.ArrayList;
import java.util.List;

import cn.qingguow.taoshihan.DBOpenHelper;
import cn.qingguow.taoshihan.Person;
import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonService {
	private DBOpenHelper dbhDbOpenHelper;

	public PersonService(Context context) {
		this.dbhDbOpenHelper=new DBOpenHelper(context);
	}

	public void save(String name){
		SQLiteDatabase db=dbhDbOpenHelper.getWritableDatabase();
		db.execSQL("insert into person (name) values(?)", new String[]{name});

	}
	public void update(int id,String name){
		SQLiteDatabase db=dbhDbOpenHelper.getWritableDatabase();
		db.execSQL("update person set name=? where id=?", new Object[]{name,id});
	}
	public void delete(int id){
		SQLiteDatabase db=dbhDbOpenHelper.getWritableDatabase();
		db.execSQL("delete from person where id=?", new Object[]{id});
	}
	public Person find(int id){
		SQLiteDatabase db=dbhDbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from person where id=?", new String[]{String.valueOf(id)});
		if(cursor.moveToFirst()){
			int personid=cursor.getInt(cursor.getColumnIndex("id"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			return new Person(personid,name);
		}
		return new Person(0,null);
	}
	public long getCount(){
		SQLiteDatabase db=dbhDbOpenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*) from person",null);
		cursor.moveToFirst();
		long result=cursor.getLong(0);
		return result;
		
	}
	public List<Person> getScollData(int offset,int maxResult){
		SQLiteDatabase db=dbhDbOpenHelper.getReadableDatabase();
		List<Person> persons=new ArrayList<Person>();
		Cursor cursor=db.rawQuery("select * from person limit ?,?", new String[]{String.valueOf(offset),String.valueOf(maxResult)});
		while(cursor.moveToNext()){
			int personid=cursor.getInt(cursor.getColumnIndex("id"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			persons.add(new Person(personid,name));
		}
		cursor.close();
		return persons;
	}

	public Cursor getCursorScollData(int offset,int maxResult){
		SQLiteDatabase db=dbhDbOpenHelper.getReadableDatabase();
		List<Person> persons=new ArrayList<Person>();
		Cursor cursor=db.rawQuery("select id as _id,name from person limit ?,?", new String[]{String.valueOf(offset),String.valueOf(maxResult)});
		return cursor;
	}
}
