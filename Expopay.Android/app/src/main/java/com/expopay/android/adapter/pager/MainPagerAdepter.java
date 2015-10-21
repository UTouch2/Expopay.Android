package com.expopay.android.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdepter extends FragmentPagerAdapter {

	Fragment[] frags;

	public MainPagerAdepter(FragmentManager fm, Fragment[] frags) {
		super(fm);
		this.frags = frags;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "";
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public Fragment getItem(int position) {
		return frags[position];
	}
}