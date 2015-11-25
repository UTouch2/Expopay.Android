package com.android.kechong.lib.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.os.Build;

public class NFCUtil {
	/**
	 * nfc可用
	 */
	public static final String NFC_SUPPORT = "00";
	/**
	 * 不支持nfc
	 */
	public static final String NFC_NO_SUPPORT = "01";
	/**
	 * 没打开nfc
	 */
	public static final String NFC_NO_OPEN = "02";
	/**
	 * @param context
	 *            当前项目的context
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public static String checkNFC2Code(Context context) {		
		NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
		if (nfcAdapter == null) {
			return NFC_NO_SUPPORT;
		} else if (!nfcAdapter.isEnabled()) {
			return NFC_NO_OPEN;
		} else {
			return NFC_SUPPORT;
		}
	}

	/**
	 * 
	 * @param context
	 *            当前项目的context
	 * @return 返回一个boolean值，
	 */
	public static boolean checkNFC2Flag(Context context) {
		if (checkNFC2Code(context).equals(NFC_SUPPORT)) {
			return true;
		} else if (checkNFC2Code(context).equals(
				NFC_NO_SUPPORT)) {
			return false;
		} else if (checkNFC2Code(context).equals(NFC_NO_OPEN)) {
			return false;
		}
		return false;
	}
}
