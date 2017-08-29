package com.d_code.dev_auto.homesecretary.presentation.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.d_code.dev_auto.homesecretary.data.entity.CheckList;
import com.google.gson.Gson;


/**
 * Created by mcauto on 2016-08-22.
 */
public class VariableItemListHandler {

    private Toast toast;

    private Activity activity;
    private Context context;
    private ItemModifyHandler itemModifyHandler;
    private ArrayAdapter adapter;
    private static final int FIXED_ITEM = 1;
    private static final int VARIABLE_ITEM = 0;

    public VariableItemListHandler(Activity context) {
        this.activity = context;
        this.context = context;
    }

    public void show(final String date){
        initDialogUI(date);
        loadCheckList(date);
        Gson gson = new Gson();

//        for(ListItem li : list)
//        {
//            Global.variableListName.add(li.getName());
//        }


        // ListView - DB에서 가져온 유동 항목의 리스트를 보여줌
//        adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1 , Global.variableListName);
//        ListView listView = (ListView) dialog.findViewById(R.id.variable_item_list);
//        listView.setAdapter(adapter);
//        itemModifyHandler = new ItemModifyHandler(activity);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                /**
//                 * CheckListFragment
//                 * ListView click index
//                 * TextView in ListView
//                 * Adapter
//                 */
//                itemModifyHandler.show(VARIABLE_ITEM, date, position, view, adapter);
//            }
//        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                Log.d("ItemModifyHandler", "onDismiss: ");
//            }
//        });
//
//        dialog.show();
    }
    private void initDialogUI(String date){
        VariableItemListDialog dialog = new VariableItemListDialog(context, date);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(params);
    }
    private CheckList loadCheckList(String date){
        return null;
    }
}
