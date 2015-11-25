package com.android.kechong.lib.listener;

import android.view.View;
/**
 * 接口是用来事件委托的，将fragment中的事件处理,委托给fragment所在的activity进行处理！
 * 可扩展，
 * @author misxu012
 *
 */
public interface IFragmentListener {
	/**
	 * 
	 * @param v
	 */
	void OnButtonOnclick(View v);

}
