package com.expopay.android.fragment;

import com.android.kechong.lib.AbsFragment;
import com.android.kechong.lib.http.Request;

public abstract class BaseFragment extends AbsFragment {
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected void cancelRequest(Request request) {
        getAbsActivity().cancelRequest(request);
    }
}
