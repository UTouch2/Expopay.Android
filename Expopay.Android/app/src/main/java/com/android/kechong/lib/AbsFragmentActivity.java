package com.android.kechong.lib;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.listener.IFragmentListener;

@SuppressLint("NewApi")
public abstract class AbsFragmentActivity extends FragmentActivity {

	protected List<Request> requests;
	protected Dialog loadingDialog;
	private IFragmentListener fragmentListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requests = new ArrayList<Request>();
	}

	protected void addFragment(int root, Fragment f) {
		getSupportFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.addToBackStack(null).commitAllowingStateLoss();
	}

	protected void repleaceFragment(int root, Fragment f) {
		getSupportFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.replace(root, f).addToBackStack(null)
				.commitAllowingStateLoss();
	}

	protected void removeFragment(Fragment f) {
		getSupportFragmentManager().beginTransaction().remove(f)
				.commitAllowingStateLoss();
	}

	protected void initView() {
	}

	protected void initPerp() {
	}

	@Override
	protected void onDestroy() {
		for (int i = 0; i < requests.size(); i++) {
			Request request = requests.get(i);
			if (request != null) {
				request.cancel();
			}
		}
		super.onDestroy();
	}

	/**
	 * 加入取消异步任务列表
	 */
	public void cancelRequest(Request request) {
		requests.add(request);
	}	

	/**
	 * 显示loading
	 */
	public void showLoading(Dialog loadingDialog) {
		this.loadingDialog = loadingDialog;
		this.loadingDialog.show();
	}

	/**
	 * 取消显示loading
	 */
	public void dismissLoading() {
		if (loadingDialog == null) {
			return;
		}
		loadingDialog.dismiss();
	}

	public IFragmentListener getFragmentListener() {
		return fragmentListener;
	}

	public void setFragmentListener(IFragmentListener fragmentListener) {
		this.fragmentListener = fragmentListener;
	}
}
