package cn.qingguow.taoshihan;

import android.R.integer;

public class Person {
	public int id;
	public String name;
	public Person(int personid, String name) {
		this.name=name;
		this.id=personid;
	}
	public String toString(){
		return "Person: [id:"+id+",name:"+name+"]";
	}
}
