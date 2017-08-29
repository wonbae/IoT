package com.d_code.dev_auto.homesecretary.presentation.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.presentation.view.activity.MainActivity;


/**
 * Created by mcauto on 2016-08-22.
 */
public class VariableItemListDialog extends Dialog implements View.OnClickListener{
    Context context;
    Button ok;
    TextView close;
    TextView title;
    String date;
    FragmentTransaction t;

    public VariableItemListDialog(Context context, String date) {
        super(context);
        this.context = context;
        this.date = date;
        this.setContentView(R.layout.variable_item_list_dialog);
        setLayout();
    }
    private void setLayout(){
        ok = (Button)findViewById(R.id.variable_item_ok);
        close = (TextView)findViewById(R.id.variable_dialog_close);
        title = (TextView)findViewById(R.id.variable_date_title);
        title.setText(date+" 체크항목");

        ok.setOnClickListener(this);
        close.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.variable_item_ok)
        {

            dismiss();
        }
        else if(v.getId() == R.id.variable_dialog_close)
        {
            dismiss();
        }
    }
}
