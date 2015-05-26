package com.example.loaddataduringrotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
	
	private Activity mActivity;
	private ArrayList<String> mList = new ArrayList<String>();
	private LoadingDialogFragment mDialogFragment;
	boolean finished;

	public MyAsyncTask(Activity activity) {
		mActivity = activity;
	}
	@Override
	protected Void doInBackground(Void ... params) {
		generateTimeConsumingData();
		return null;
	}
	
	@Override
	protected void onPreExecute() {
		FragmentManager manager = mActivity.getFragmentManager();
		mDialogFragment = new LoadingDialogFragment();
		mDialogFragment.show(manager, "loadingdialog");
	}
	
	@Override
	protected void onPostExecute(Void result) {
		finished = true;
		if (mDialogFragment != null) {
			mDialogFragment.dismiss();
		}
		if (mActivity instanceof TaskCompleted) {
			((MainActivity)mActivity).onTaskCompleted();
		}
	};
	
	void generateTimeConsumingData() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mList.addAll(Arrays.asList(new String[]{"element1", "element2","element3","element4","element5"}));
	}
	
	List<String> getData() {
		return mList;
	}
	
	void setActivity(Activity activity) {
		//activity销毁前dismiss dialog
		if (activity == null) {
			mDialogFragment.dismiss();
		}
		mActivity = activity;
		if (mActivity != null && !finished){
			//对话框与新的activity绑定
			mDialogFragment = new LoadingDialogFragment();
			FragmentManager manager = mActivity.getFragmentManager();
			mDialogFragment.show(manager, "loadingdialog");
		}
		if (finished) {
			if (mActivity instanceof TaskCompleted) {
				((MainActivity)mActivity).onTaskCompleted();
			}
		}
	}
}
