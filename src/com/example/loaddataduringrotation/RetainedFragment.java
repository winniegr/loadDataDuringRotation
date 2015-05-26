package com.example.loaddataduringrotation;

import android.app.Fragment;
import android.os.Bundle;

/**
 * this class is for save object for MainAcitivity when configuration changes(like orientation changes)
 * @author winniegr
 *
 */
public class RetainedFragment extends Fragment {

	MyAsyncTask mTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	public void setTask(MyAsyncTask task) {
		mTask = task;
	}
	
	public MyAsyncTask getTask() {
		return mTask;
	}
}
