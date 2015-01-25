package com.adobe.apphub.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.adobe.apphub.Application.AppHubApplication;

/**
 * Created by vaisinha on 25/01/15.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppHubApplication)getActivity().getApplication()).inject(this);
    }

}
