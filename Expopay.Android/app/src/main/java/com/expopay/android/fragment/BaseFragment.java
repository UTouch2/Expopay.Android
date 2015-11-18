package com.expopay.android.fragment;

import com.android.kechong.lib.AbsFragment;
import com.android.kechong.lib.http.Request;
import com.expopay.android.activity.BaseActivity;
import com.expopay.android.model.UserEntity;

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

    public UserEntity getUser() {
        if(getActivity() instanceof BaseActivity){
            return ((BaseActivity)getActivity()).getUser();
        }
        return  new UserEntity();
    }
}
