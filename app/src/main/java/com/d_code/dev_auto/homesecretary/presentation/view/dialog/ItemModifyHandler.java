package com.d_code.dev_auto.homesecretary.presentation.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mcauto on 2016-08-19.
 */
public class ItemModifyHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;
    private Context cotext;


    public ItemModifyHandler(Activity context) {
        this.activity = context;
        this.cotext = context;
    }

    public void show(int type, String date, int position, View v, ArrayAdapter adapter)
    {

        ItemModifyDialog dialog = new ItemModifyDialog(cotext);
        dialog.setType(type);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.date = date;
        dialog.getWindow().setAttributes(params);

        // 내용 변경
        dialog.setPreText(position,v,adapter);
        TextView tv = (TextView)v;
        if(tv.getText().equals("+ 새 항목 추가")) {
            dialog.delete.setVisibility(View.GONE);
        }
        else{
            dialog.delete.setVisibility(View.VISIBLE);
        }

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.d("ItemModifyHandler", "onDismiss: ");
            }
        });

        dialog.show();
    }
}
