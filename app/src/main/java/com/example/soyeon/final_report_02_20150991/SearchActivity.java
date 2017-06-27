package com.example.soyeon.final_report_02_20150991;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<MovieData> movieDataList;
    private MyDBHelper myDBHelper;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        myDBHelper = new MyDBHelper(this);//helper객체 생성
        movieDataList = new ArrayList<MovieData>();
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, movieDataList);
    }

    protected void onResume() {
        super.onResume();
        viewTable();    // 테이블 전체의 내용을 Log에 출력하는 메소드 호출
        //저장된 DB 읽기위해 필요
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_save:
                EditText searchTitle = (EditText) findViewById(R.id.search_etTitle);
                String sTitle = searchTitle.getText().toString();
                for (int i = 0; i < movieDataList.size(); i++) {
                    if (movieDataList.get(i).getTitle().equals(sTitle)) {
                        MovieData selectedData = movieDataList.get(i);
                        Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                        intent.putExtra("movie_data", selectedData);
                        startActivity(intent);
                    }
                }

                break;
            case R.id.search_cancle:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void viewTable() {
        movieDataList.clear();
//        읽기 가능 데이터베이스 가져오기
        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        String[] cols = null;
        String whereClause = null;
        String[] whereArgs = null;


        Cursor cursor = db.query(MyDBHelper.TABLE_NAME, cols, whereClause, whereArgs, null, null, null, null);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String title = cursor.getString(1);
            String director = cursor.getString(2);
            float rating = cursor.getFloat(3);
            String releaseDay = cursor.getString(4);
            String genre = cursor.getString(5);
            String actor = cursor.getString(6);
            String story = cursor.getString(7);
//            레코드에서 읽어들인 값을 movieDataList 객체에 저장
            MovieData newItem = new MovieData(_id, title, director, rating, releaseDay, genre, actor, story);

//            movieDataList 객체를 리스트에 저장
            movieDataList.add(newItem);
        }


//        마무리 작업
        cursor.close();
        myDBHelper.close();

//        원본 리스트(itemList)를 새로 읽어왔으므로 어댑터에게 변경 통지
        myAdapter.notifyDataSetChanged();
    }

}
