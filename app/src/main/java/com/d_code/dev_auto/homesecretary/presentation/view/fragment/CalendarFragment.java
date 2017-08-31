package com.d_code.dev_auto.homesecretary.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.d_code.dev_auto.homesecretary.HomeSecretaryApplication;
import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.data.entity.Event;
import com.d_code.dev_auto.homesecretary.data.entity.EventDao;
import com.d_code.dev_auto.homesecretary.presentation.view.component.decorator.EventDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mcauto on 2017. 2. 22..
 */

public class CalendarFragment extends Fragment implements OnDateSelectedListener, OnMonthChangedListener {
    private static CalendarFragment calendarFragment = new CalendarFragment();
    private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public static final String DRAG_POSITION = "drag_position";

    @BindView(R.id.calendarView)
    MaterialCalendarView widget;

    private HomeSecretaryApplication hsApp;



    private EventDao eventDao;

    private ArrayList<Event> events;
    private Event empty = new Event().getEmpty();

    private EventDecorator decorator;

    public CalendarFragment() {

    }
    public static CalendarFragment newInstance()
    {
        return calendarFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);

        initGreenDAO();

        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);
        widget.setPagingEnabled(false);
        int activeColor = getResources().getColor(R.color.tab_active);
        widget.setSelectionColor(activeColor);

        // decorator 방식으로 calendar을 관리
        Collection<CalendarDay> today = new ArrayList<>();

        today.add(CalendarDay.today());

        // color는 8비트 색상값
        int todayColor = getResources().getColor(R.color.today_color);
        decorator = new EventDecorator(todayColor, today);
        widget.addDecorator(decorator);
        loadExistContents();

        return view;
    }
    private void initGreenDAO(){
        hsApp = (HomeSecretaryApplication) (getActivity().getApplication());
        eventDao = hsApp.getDaoSession().getEventDao();
    }
    private void loadExistContents(){
//        Event event = new Event();
//        event.title = "hello world!";
//        //event.date = Calendar.getInstance().getTime();
//        event.save();
        events = (ArrayList<Event>) eventDao.loadAll();

//        List<Event> events = Event.getAll();
//        Event.deleteAll(events);


//        events = (ArrayList<Event>) Event.getAll();

    }

    // Interaction Calendar
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        // load month CheckList
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        java.util.Date d = date.getDate();
        String select_date = FORMATTER.format(d);

        events.add(empty);
        showCheckListDialog(select_date, empty);
    }
    private void showCheckListDialog(String date, final Event event) {
        new MaterialDialog.Builder(getActivity())
                .title(date)
                .items(events)
                .negativeText("취소")
                .positiveText("확인")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if(dialog.getItems().get(which).toString().equals("+ 새 항목 추가"))
                            showCreateDialog(dialog, which); // create Event
                        else
                            showUpdateDialog(dialog, which); // update Event
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(empty.title.equals("+ 새 항목 추가"))
                            events.remove(empty);
                        else
                            empty = new Event().getEmpty();
                        dialog.dismiss();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        events.remove(empty);
                        dialog.dismiss();
                    }
                })
                .autoDismiss(false)
                .show();
    }

    private void showCreateDialog(MaterialDialog dialog, int which){

        new MaterialDialog.Builder(getActivity())
                .title("추가") // EditText를 이용해서 제목, 설명 추가하도록 개발
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("내용", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                        empty.setTitle(input.toString());

                    }
                })
                .positiveText("확인")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        eventDao.insert(empty);
                      //  dialog.notifyItemsChanged();
                    }
                })
                .negativeText("취소")
                .show();
    }
    private void showUpdateDialog(MaterialDialog dialog, int which) {
        String eventTitle = dialog.getItems().get(which).toString();

        new MaterialDialog.Builder(getActivity())
                .title(eventTitle+ " 수정") // EditText를 이용해서 제목, 설명 추가하도록 개발
                .content("내용은 없어요")
                .positiveText("확인")
                .negativeText("취소")
                .show();
    }
}
