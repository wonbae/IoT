package com.d_code.dev_auto.homesecretary.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d_code.dev_auto.homesecretary.R;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class FixedCheckListFragment extends Fragment {
    private static FixedCheckListFragment fixedCheckListFragment = new FixedCheckListFragment();

    public FixedCheckListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixed_checklist, container, false);

        return view;
    }

    public static FixedCheckListFragment newInstance() {
        return fixedCheckListFragment;
    }

}