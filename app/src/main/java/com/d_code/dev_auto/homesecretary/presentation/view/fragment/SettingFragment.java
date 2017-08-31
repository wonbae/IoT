package com.d_code.dev_auto.homesecretary.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.d_code.dev_auto.homesecretary.R;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class SettingFragment extends Fragment{
    private static SettingFragment settingFragment = new SettingFragment();

    public SettingFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        return view;
    }

    public static SettingFragment newInstance() {
        return settingFragment;
    }

}
