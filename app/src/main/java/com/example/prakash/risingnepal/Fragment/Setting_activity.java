package com.example.prakash.risingnepal.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prakash.risingnepal.R;

public class Setting_activity extends android.support.v4.app.Fragment {
   View mainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.setting_layout,container,false);



        return mainView;
    }
}
