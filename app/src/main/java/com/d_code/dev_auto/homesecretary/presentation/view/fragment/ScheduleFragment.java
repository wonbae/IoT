package com.d_code.dev_auto.homesecretary.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.d_code.dev_auto.homesecretary.R;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class ScheduleFragment extends Fragment {
    private static ScheduleFragment scheduleFragment = new ScheduleFragment();

    public ScheduleFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return view;
    }

    public static ScheduleFragment newInstance() {
        return scheduleFragment;
    }

}