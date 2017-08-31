package com.d_code.dev_auto.homesecretary.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
import java.util.Date;

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
    private ArrayList<Event> eventsOfDay;
    private boolean positive = false;

    private MaterialDialog pDialog;

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
        loadExistContentsAndDraw();

        return view;
    }
    private void initGreenDAO(){
        hsApp = (HomeSecretaryApplication) (getActivity().getApplication());
        eventDao = hsApp.getDaoSession().getEventDao();
        Event event = new Event();
    }
    private void loadExistContentsAndDraw(){
        //eventDao.deleteAll();
        events = (ArrayList<Event>) eventDao.loadAll();
        drawEvents(events);
    }
    private void drawEvents(ArrayList<Event> events){
        Collection<CalendarDay> storedEvents = new ArrayList<>();
        if(events.size()>0){
            for(int i=0;i<events.size();i++)
            {
                Date created = events.get(i).getDate();
                CalendarDay day = CalendarDay.from(created);
                storedEvents.add(day);
            }
            int prevColor = getResources().getColor(R.color.prev_color);
            EventDecorator storedEventDeco  = new EventDecorator(prevColor, storedEvents);
            widget.addDecorator(storedEventDeco);
        }
    }

    // Interaction Calendar
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        // load month CheckList
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        Date select_date = date.getDate();
        eventsOfDay = (ArrayList<Event>) eventDao.queryBuilder()
                .where(EventDao.Properties.Date.eq(select_date))
                .orderAsc(EventDao.Properties.Date)
                .list();
        Event empty = new Event().getEmpty(select_date);
        eventsOfDay.add(empty);
        showCheckListDialog(select_date, eventsOfDay.indexOf(empty));
        positive = false;
        loadExistContentsAndDraw();
    }
    private void showCheckListDialog(final Date date, final int position) {
        String select_date = FORMATTER.format(date);
        new MaterialDialog.Builder(getActivity())
                .title(select_date)
                .items(eventsOfDay)
                .negativeText("취소")
                .positiveText("확인")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if(dialog.getItems().get(which).toString().equals("+ 새 항목 추가"))
                            showCreateDialog(dialog, which, date); // create Event
                        else
                            showUpdateDialog(dialog, which, date); // update Event
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        // 확인을 눌렀을 때 현재 변경된 내용 저장
                        Event targetEvent = eventsOfDay.get(position);

                        if(targetEvent.title.equals("+ 새 항목 추가"))
                            eventsOfDay.remove(targetEvent);

                        dialog.dismiss();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Event targetEvent = eventsOfDay.get(position);
                        eventsOfDay.remove(targetEvent);
                        dialog.dismiss();
                    }
                })
                .autoDismiss(false)
                .show();
    }

    private void showCreateDialog(MaterialDialog dialog, final int position,final Date date){
        pDialog = dialog;
        new MaterialDialog.Builder(getActivity())
                .title("추가") // EditText를 이용해서 제목, 설명 추가하도록 개발
                .inputType(InputType.TYPE_CLASS_TEXT)
                .positiveText("확인")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        positive = true;
                    }
                })
                .input("내용", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if(positive) {
                            Event targetEvent = eventsOfDay.get(position);
                            targetEvent.setTitle(input.toString());
                            targetEvent.setDate(date);
                            eventDao.insert(targetEvent);

                            pDialog.getItems().set(position, targetEvent.toString());
                            pDialog.notifyItemChanged(position);

                        }
                    }
                })
                .negativeText("취소")
                .show();
    }
    private void showUpdateDialog(MaterialDialog dialog, final int position, final Date date) {
        String eventTitle = pDialog.getItems().get(position).toString();
        pDialog = dialog;
        new MaterialDialog.Builder(getActivity())
                .title(eventTitle+ " 수정") // EditText를 이용해서 제목, 설명 추가하도록 개발
                .inputType(InputType.TYPE_CLASS_TEXT)
                .positiveText("확인")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        positive = true;
                    }
                })
                .input(eventTitle, "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if(positive) {
                            // Do something
                            Event updateEvent = eventsOfDay.get(position);
                            updateEvent.setTitle(input.toString());
                            updateEvent.setDate(date);
                            eventsOfDay.set(position, updateEvent);

                            EditText et = dialog.getInputEditText();
                            et.setHint(updateEvent.title);
                            eventDao.update(updateEvent);

                            pDialog.getItems().set(position, updateEvent.toString());
                            pDialog.getRecyclerView().getAdapter();
                            pDialog.notifyItemChanged(position);
                        }
                    }
                })
                .negativeText("취소")
                .show();
    }
}
