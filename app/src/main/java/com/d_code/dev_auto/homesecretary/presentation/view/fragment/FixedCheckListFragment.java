package com.d_code.dev_auto.homesecretary.presentation.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.d_code.dev_auto.homesecretary.HomeSecretaryApplication;
import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.data.entity.Event;
import com.d_code.dev_auto.homesecretary.data.entity.EventDao;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEvent;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEventDao;
import com.d_code.dev_auto.homesecretary.presentation.view.activity.RoadSearchActivity;
import com.d_code.dev_auto.homesecretary.presentation.view.component.adapter.FixedEventAdapter;
import com.d_code.dev_auto.homesecretary.presentation.view.component.adapter.NotifyViewAdapter;
import com.d_code.dev_auto.homesecretary.presentation.view.component.listener.FixedEventClickListener;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class FixedCheckListFragment extends Fragment {
    private static FixedCheckListFragment fixedCheckListFragment = new FixedCheckListFragment();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FixedEventAdapter adapter;

    private HomeSecretaryApplication hsApp;
    private FixedEventDao fixedEventDao;

    public FixedCheckListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixed_checklist, container, false);
        initGreenDAO();
        init(view);
        return view;
    }
    private void initGreenDAO(){
        hsApp = (HomeSecretaryApplication) (getActivity().getApplication());
        fixedEventDao = hsApp.getDaoSession().getFixedEventDao();
    }
    private void init(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.fixed_item_list);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.canScrollVertically();
        FixedEventClickListener listener = new FixedEventClickListener(recyclerView,getActivity(),getActivity());
        adapter = new FixedEventAdapter();
        adapter.setClickListener(listener);

        recyclerView.setAdapter(adapter);


        ArrayList<FixedEvent> fixedEvents = (ArrayList<FixedEvent>) fixedEventDao.loadAll();
        fixedEvents.add(new FixedEvent().getEmpty());

        adapter.addFixedEventAll(fixedEvents);

    }


    public static FixedCheckListFragment newInstance() {
        return fixedCheckListFragment;
    }


}