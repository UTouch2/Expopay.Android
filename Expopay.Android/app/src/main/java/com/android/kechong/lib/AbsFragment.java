package com.android.kechong.lib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class AbsFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected View findViewById(int id) {
		return getActivity().findViewById(id);
	}

	protected void initView() {
	}

	protected void initPerp() {
	}

	public AbsFragmentActivity getAbsActivity() {
		AbsFragmentActivity ac = null;
		try {
			ac = (AbsFragmentActivity) getActivity();
		} catch (Exception e) {
		}
		return ac;
	}

	protected void dismissLoading() {
		getAbsActivity().dismissLoading();
	}
}
