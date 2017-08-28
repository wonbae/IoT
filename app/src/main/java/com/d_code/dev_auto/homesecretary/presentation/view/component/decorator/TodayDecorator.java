package com.d_code.dev_auto.homesecretary.presentation.view.component.decorator;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Created by mcauto on 2017. 3. 13..
 */

public class TodayDecorator implements DayViewDecorator {
    private final Drawable highlightDrawable;
    private final int color = Color.parseColor("#009000");
    private CalendarDay date;

    public TodayDecorator() {
        highlightDrawable = new ColorDrawable(color);
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {

        //view.setBackgroundDrawable(highlightDrawable);
        view.addSpan(highlightDrawable);
        //view.addSpan(new ForegroundColorSpan(Color.WHITE));

    }
}
