package com.d_code.dev_auto.homesecretary.presentation.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.d_code.dev_auto.homesecretary.R;


/**
 * Created by mcauto on 2016-08-19.
 */
public class ItemModifyDialog extends Dialog implements View.OnClickListener{

    private static final int FIXED_ITEM = 1;
    private static final int VARIABLE_ITEM = 0;

    Context context;
    Button ok,cancel,delete;
    TextView close;
    EditText editText;
    TextView originView;
    String date;

    private int type;
    private String preText;
    private int position;
    private ArrayAdapter adapter;


    public ItemModifyDialog(Context context) {
        super(context);
        this.context = context;
        this.setContentView(R.layout.item_modify_dialog);

        setLayout();
    }
    public void setPreText(int position, View view, ArrayAdapter adapter){
        // this.preText = Global.myCheckList.get(position).getName();
        this.preText = ((TextView)view).getText().toString();
        this.position = position;
        this.adapter = adapter;
        originView = (TextView) view;
        editText.setHint(preText);
    }

    private void setLayout()
    {
        ok = (Button)findViewById(R.id.modify_ok);
        cancel = (Button)findViewById(R.id.modify_cancel);
        delete = (Button)findViewById(R.id.modify_delete);
        close = (TextView)findViewById(R.id.modify_close);
        editText = (EditText)findViewById(R.id.modify_item);

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        close.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//       if(v.getId() == R.id.modify_ok)
//        {
//            // 변경된 내용이 리스트에 저장이 되어야 함
//            preText = editText.getText().toString();            // 수정된 텍스트 or 등록된 텍스트
//            originView.setText(preText);
//
//            if(type == FIXED_ITEM)          // 고정 항목일 경우
//            {
//                if(!Global.checkListName.contains(preText))         // 없으면 추가
//                {
//                    ListItem newItem = new ListItem();
//                    newItem.setUser_id(Global.user_id);
//                    newItem.setFixed(type);                            // 유동항목(0), 고정항목(1)
//                    newItem.setCall_date("always");
//                    newItem.setName(preText);
//
//                    Global.myCheckList.set(position,newItem);       // ListItem으로서 저장
//                    Global.checkListName.set(position,preText);     // String으로 저장 (arrayadaptor) << 커스터마이징 해야됨..
//                    ListItem l = new ListItem();
//                    Global.myCheckList.add(l);
//                    Global.checkListName.add(l.getName());
//                    Global.db.addItem(newItem);
//
//                    Toast.makeText(context, "추가되었습니다.", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(context, "이미 존재합니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else if(type == VARIABLE_ITEM)  // 유동 항목일 경우
//            {
//                if(!Global.variableListName.contains(preText))         // 없으면 추가
//                {
//                    ListItem newItem = new ListItem();
//                    newItem.setUser_id(Global.user_id);
//                    newItem.setFixed(type);                            // 유동항목(0), 고정항목(1)
//                    newItem.setCall_date(date);
//                    newItem.setName(preText);
//
//                    Global.variableList.set(position, newItem);       // ListItem으로서 저장
//                    Global.variableListName.set(position, preText);     // String으로 저장 (arrayadaptor) << 커스터마이징 해야됨..
//                    ListItem l = new ListItem();
//                    Global.variableList.add(l);
//                    Global.variableListName.add(l.getName());
//
//                    Global.db.addItem(newItem);
//                    Toast.makeText(context, "추가되었습니다.", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(context, "이미 존재합니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
//            dismiss();
//        }
//        else if(v.getId() == R.id.modify_cancel)
//        {
//            dismiss();
//        }
//        else if(v.getId()==R.id.modify_delete)
//        {
//            if(type == FIXED_ITEM) {
//                Global.db.deleteListItem(Global.myCheckList.get(position));
//                Global.myCheckList.remove(position);
//                Global.checkListName.remove(position);
//            }
//            else if(type == VARIABLE_ITEM){
//                Global.db.deleteListItem(Global.variableList.get(position));
//                Global.variableList.remove(position);
//                Global.variableListName.remove(position);
//            }
//            dismiss();
//        }
//        else if(v.getId() == R.id.modify_close)
//        {
//            dismiss();
//        }
//        adapter.notifyDataSetChanged();
    }

    private void gotoGoogleMarket(String data)
    {
        Toast.makeText(context,
                "준비중입니다!", Toast.LENGTH_SHORT)
                .show();
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
