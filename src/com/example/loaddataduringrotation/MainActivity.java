package com.example.loaddataduringrotation;

import java.util.List;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class MainActivity extends ListActivity implements TaskCompleted{
	
	MyAsyncTask mTask;
	List<String> mDatas;
	ListAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setBackgroundColor(Color.WHITE);
		if (mDatas != null && mDatas.size() > 0) {
			mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
			setListAdapter(mAdapter);
		} else {
			FragmentManager manager = getFragmentManager();
			RetainedFragment fragment = (RetainedFragment)manager.findFragmentByTag("task");
			if (fragment == null) {
				fragment = new RetainedFragment();
				manager.beginTransaction().add(fragment, "task").commit();
			}
			
			mTask = fragment.getTask();
			if (mTask == null) {
				mTask = new MyAsyncTask(this);
				fragment.setTask(mTask);
				mTask.execute();
			} else {
				mTask.setActivity(this);
			}
		}
	}

	@Override
	public void onTaskCompleted() {
		mDatas = mTask.getData(); 
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
		setListAdapter(mAdapter);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		if (mTask != null) {
			mTask.setActivity(null);
		}
		super.onSaveInstanceState(outState);
	}
	
}
