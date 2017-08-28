package com.d_code.dev_auto.homesecretary.presentation.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.data.entity.CheckList;
import com.d_code.dev_auto.homesecretary.data.entity.Event;
import com.d_code.dev_auto.homesecretary.presentation.view.component.decorator.EventDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);
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

        loadExistContents();

        return view;
    }
    private void loadExistContents(){
//        Event event = new Event();
//        event.title = "hello world!";
//        event.date = Calendar.getInstance().getTime();
//        event.save();

        List<Event> events = Event.getAll();
        Event.deleteAll(events);
       /* Student user = PocketApplication.information.getUserInfo();
        Callable callable = ContentCallable.getInstance()
                                            .setOwner(user.getId());

        task = new AsyncExecutor<>();
        task.setCallable(callable)
                .setCallback(callback)
                .setActivity(getActivity())
                .execute();*/
    }
    /*private AsyncCallback<String> callback = new AsyncCallback<String>() {

        @Override
        public void progressRate() {
        }

        @Override
        public void onResult(String result) {
            try {
                Gson gson = new Gson();
                ArrayList<Content> contents;
                Collection<CalendarDay> exist_contents = new ArrayList<>();
                Type collectionType = new TypeToken<ArrayList<Content>>(){}.getType();

                contents = gson.fromJson(result,collectionType);
                if(contents!=null) {
                    for (int i = 0; i < contents.size(); i++) {
                        Date created = Date.valueOf(contents.get(i).getCreated());
                        CalendarDay day = CalendarDay.from(created);
                        exist_contents.add(day);
                    }
                    int prevColor = getResources().getColor(R.color.prev_color);
                    EventDecorator deco2 = new EventDecorator(prevColor, exist_contents);
                    widget.addDecorator(deco2);
                    widget.addDecorator(decorator);
                }
            }
            catch(Exception e)
            {
                Log.e(this.getClass().toString(), "onResult: ",e);
            }
        }

        @Override
        public void exceptionOccured(Exception e) {

        }

        @Override
        public void cancelled() {

        }
    };*/


    // Interaction Calendar
    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

       /* CalendarDay today = CalendarDay.today();
        // 내가 시작한 날
        String created = PocketApplication.information.getUserInfo().getCreated();
        Date created_date = Date.valueOf(created);
        CalendarDay cd = CalendarDay.from(created_date);

        CalendarDay firstDay = CalendarDay.from(cd.getYear(),cd.getMonth(),cd.getDay());

        if(date.isInRange(firstDay, today)) // 이번달 내에서만 클릭 가능
            startDraggerActivity(date);
        else
            widget.clearSelection();*/

    }

    /*// DraggerAcitivty
    private void startDraggerActivity(CalendarDay date) {
        Intent intent = new Intent(getActivity(), EduSlideAlbumListActivity.class);

        java.util.Date d = date.getDate();
        String select_date = FORMATTER.format(d);
        intent.putExtra(EduSlideAlbumListActivity.DATE, select_date);
        startActivityNoAnimation(intent);
    }*/
    /*private void startActivityNoAnimation(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }*/
}
