package com.d_code.dev_auto.homesecretary.presentation.view.component.listener;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.d_code.dev_auto.homesecretary.HomeSecretaryApplication;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEvent;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEventDao;
import com.d_code.dev_auto.homesecretary.presentation.view.component.adapter.FixedEventAdapter;

/**
 * Created by mcauto on 2017. 9. 2..
 */

public class FixedEventClickListener implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private Activity activity;
    private Context context;
    private HomeSecretaryApplication hsApp;
    private FixedEventDao fixedEventDao;

    public FixedEventClickListener(RecyclerView mRecyclerView, Context context, Activity activity){
        this.mRecyclerView = mRecyclerView;
        this.context = context;
        this.activity = activity;
        initGreenDAO();

    }

    @Override
    public void onClick(View v) {
        TextView t = (TextView)v;

        final int position = Integer.parseInt(t.getHint().toString());
        if(t.getText().toString().equals("+ 새 항목 추가"))
            showCreateDialog(position, t);
        else
            showUpdateDialog(position, t);

    }
    private void initGreenDAO(){
        hsApp = (HomeSecretaryApplication) (activity.getApplication());
        fixedEventDao = hsApp.getDaoSession().getFixedEventDao();
    }
    private void showCreateDialog(final int position, TextView textView)
    {
        // FixedEvent 추가
        new MaterialDialog.Builder(context)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(textView.getText(), "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                        FixedEvent empty = new FixedEvent().getEmpty();
                        empty.setTitle(input.toString());
                        fixedEventDao.insert(empty);

                        FixedEventAdapter adapter = (FixedEventAdapter)(mRecyclerView.getAdapter());
                        adapter.setFixedEvent(position, empty);
                        adapter.addFixedEvent(new FixedEvent().getEmpty());
                        adapter.notifyDataSetChanged();
                    }
                }).show();

    }
    private void showUpdateDialog(final int position, TextView textView)
    {
        // FixedEvent 추가
        new MaterialDialog.Builder(context)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(textView.getText(), "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        FixedEventAdapter adapter = (FixedEventAdapter)(mRecyclerView.getAdapter());
                        FixedEvent event = adapter.getFixedEventAll().get(position);
                        event.setTitle(input.toString());

                        fixedEventDao.update(event);
                        adapter.setFixedEvent(position, event);
                        adapter.notifyDataSetChanged();
                    }
                }).show();

    }
}
