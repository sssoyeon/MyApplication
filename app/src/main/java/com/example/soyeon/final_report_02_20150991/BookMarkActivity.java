package com.example.soyeon.final_report_02_20150991;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BookMarkActivity extends AppCompatActivity {
    private ArrayList<MovieData> movieDataList;
    private ArrayList<MovieData> selectedDataList;
    private MyAdapter myAdapter;
    private ListView listView;
    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark);

        myDBHelper = new MyDBHelper(this);//helper객체 생성
        movieDataList = new ArrayList<MovieData>();
        selectedDataList = new ArrayList<MovieData>();

        listView = (ListView) findViewById((R.id.bm_listView));
        viewTable();


        Intent intent = getIntent();
        String data = intent.getStringExtra("selected");

//            movieDataList 객체를 리스트에 저장

        for (int i = 0; i < movieDataList.size(); i++) {
            if (movieDataList.get(i).getTitle().equals(data)) {
                selectedDataList.add(movieDataList.get(i));

            }
        }
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, selectedDataList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                MovieData selectedData = movieDataList.get(i);
                                                Intent intent = new Intent(BookMarkActivity.this, DetailActivity.class);

                                                intent.putExtra("movie_data", selectedData);

                                                startActivity(intent);

                                            }
                                        }
        );
        listView.setAdapter(myAdapter);
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

    }

}
//
//    protected void onResume() {
//        super.onResume();
//        viewTable();    // 테이블 전체의 내용을 Log에 출력하는 메소드 호출
//        //저장된 DB 읽기위해 필요
//    }
//}