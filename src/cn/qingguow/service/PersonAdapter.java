package cn.qingguow.service;

import java.util.List;

import cn.qingguow.taoshihan.Person;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PersonAdapter extends BaseAdapter {
	private List<Person> person;
	public PersonAdapter(List<Person> person){
		this.person=person;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
