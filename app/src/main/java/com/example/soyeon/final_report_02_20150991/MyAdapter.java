package com.example.soyeon.final_report_02_20150991;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by soyeon on 2017. 6. 23..
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MovieData> movieDataList;
    private LayoutInflater layoutInflater;


    public MyAdapter(Context context, int layout, ArrayList<MovieData> movieDataList) {
        this.context = context;
        this.layout = layout;
        this.movieDataList = movieDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return movieDataList.size();
    }

    @Override
    public Object getItem(int pos) {
        return movieDataList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return movieDataList.get(pos).get_id();
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        final int position = pos;

        if (view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
        }
        TextView textTitle = (TextView) view.findViewById(R.id.cus_mvTitle);
        TextView textActor = (TextView) view.findViewById(R.id.cus_mvActor);
        // TextView textGenre = (TextView)view.findViewById(R.id.mvGenre);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.cus_mvRating);
        //View가 null인 상태일 때 layout viewr객체 생성해서 custom_adapter_view layout에 배치한객체준비

        //요청 뷰 반환
        textTitle.setText(movieDataList.get(position).getTitle());
        textActor.setText("출연:" + movieDataList.get(position).getActor());
        //textGenre.setText(movieDataList.get(position).getGenre());
        ratingBar.setRating(movieDataList.get(position).getRating());
        return view;
    }

}

