package com.example.loaddataduringrotation;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class LoadingDialogFragment extends DialogFragment {
	
	private Dialog dialog;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		dialog = new Dialog(getActivity(), R.style.dialog);
		dialog.setContentView(R.layout.loading_dialog);
		return dialog;
	}
}
