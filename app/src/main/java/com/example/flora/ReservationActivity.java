package com.example.flora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.flora.databinding.*;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.messaging.Constants;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ReservationActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private MaterialCalendarView calendarView;
    public ReservationRecyclerAdapter mRecyclerAdapter;
    public ArrayList<ReservationItem> mReservationItems = new ArrayList<ReservationItem>();
    private ImageView next_btn;
    private ImageView arrowButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_reservation);

        RecyclerView mRecyclerView = findViewById(R.id.recycler);

        next_btn = (ImageView) findViewById(R.id.next_btn);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationActivity.this, AfterReservationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        arrowButton = (ImageView) findViewById(R.id.arrowButton);

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        calendarView = findViewById(R.id.calendarview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        /* initiate adapter */
        mRecyclerAdapter = new ReservationRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);

        /* adapt data */
        mReservationItems = new ArrayList<>();
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "09:00"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "09:30"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "10:00"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "10:30"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "11:00"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "11:30"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "12:00"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "12:30"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "13:00"));
        mReservationItems.add(new ReservationItem(R.drawable.time_img, "13:30"));
        mRecyclerAdapter.setReservationListList(mReservationItems);

        // ???, ????????? ????????? ????????? ?????? (MonthArrayTitleFormatter??? ????????? ??????????????? ?????? setTitleFormatter()??? ?????????)
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));

        // ?????? ????????? ?????? ???, ?????? ?????? ????????? ??????
        calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader);

        // ?????? ?????? ??? ?????? ????????? ??????????????? ??????????????? ???
        calendarView.setOnRangeSelectedListener(new OnRangeSelectedListener() {
            @Override
            public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
                // ?????? ????????? ?????? ?????????, ???????????? ????????? ???????????? ???????????? ????????? ????????? ????????? ?????? ?????? ????????????
                // UTC ????????? ???????????? ?????? ??? ????????????????????? ???????????? ????????? ????????? ????????? ?????? ???????????? ?????? ??????
                String startDay = dates.get(0).getDate().toString();
                String endDay = dates.get(dates.size() - 1).getDate().toString();
                Log.e(TAG, "????????? : " + startDay + ", ????????? : " + endDay);
            }
        });

        // ?????? ?????? ??? ?????? ????????? ??????????????? ??????????????? ??????
        calendarView.addDecorators(new DayDecorator(this));

    }

    /* ????????? ????????? background??? ???????????? Decorator ????????? */
    private static class DayDecorator implements DayViewDecorator {

        private final Drawable drawable;

        public DayDecorator(Context context) {
            drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector);
        }

        // true??? ?????? ??? ?????? ????????? ?????? ????????? ??????????????? ????????????
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        // ?????? ?????? ??? ?????? ????????? ??????????????? ??????????????? ??????
        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
//            view.addSpan(new StyleSpan(Typeface.BOLD));   // ?????? ?????? ?????? ???????????? ?????? ?????????
        }
    }

}