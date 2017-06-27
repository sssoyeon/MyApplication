package com.example.soyeon.final_report_02_20150991;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //    Log 를 위한 TAG
    public final static String TAG = "MainActivity";

    public final static int AddActivity = 100;//액티비티 구분 코드
    private ArrayList<MovieData> movieDataList;
    private MyAdapter myAdapter;
    private ListView listView;
    private Button btn;
    private MyDBHelper myDBHelper;
    int select = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //id, 제목, 배우, 장르,감독,평점 내용, 개봉일
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDBHelper = new MyDBHelper(this);//helper객체 생성

        listView = (ListView) findViewById((R.id.customListView));


        movieDataList = new ArrayList<MovieData>();//데이터리스트 객체 생성
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, movieDataList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                MovieData selectedData = movieDataList.get(i);
                                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                                                intent.putExtra("movie_data", selectedData);

                                                startActivity(intent);
                                                Toast.makeText(MainActivity.this, "상세 액티비티 호출", Toast.LENGTH_SHORT).show();
                                            }
                                        }
        );
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                                    Toast.makeText(MainActivity.this, "아이템 롱클릭시 삭제", Toast.LENGTH_SHORT).show();
                                                    final int selectedpos = position;
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                    builder.setMessage("정말 삭제하시겠습니까?");
                                                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            //                쓰기 가능 데이터베이스 가져오기
                                                            SQLiteDatabase db = myDBHelper.getWritableDatabase();

//                삭제를 위한 where 절 구성
                                                            String whereClause = MyDBHelper.COL_ID + "=?";
                                                            String[] whereArgs = {Integer.toString(movieDataList.get(selectedpos).get_id())};

//                삭제 수행 - 성공 시 삭제된 레코드 개수를 반환
                                                            long result = db.delete(MyDBHelper.TABLE_NAME, whereClause, whereArgs);

                                                            if (result > 0) {
                                                                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                                            } else
                                                                Toast.makeText(MainActivity.this, "Failure!", Toast.LENGTH_SHORT).show();

//               마무리 작업
                                                            myDBHelper.close();

                                                            viewTable();    // 테이블 전체의 내용을 Log에 출력하는 메소드 호
                                                        }
                                                    });
                                                    builder.setNegativeButton("취소", null);
                                                    builder.show();
                                                    return true;
                                                }
                                            }
        );

        listView.setAdapter(myAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewTable();    // 테이블 전체의 내용을 Log에 출력하는 메소드 호출
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivity(new Intent(this, AddActivity.class));
                Toast.makeText(MainActivity.this, "추가 액티비티 호출", Toast.LENGTH_SHORT).show();
                break;
            case R.id.introduce:
                Toast.makeText(MainActivity.this, "개발자 소개 액티비티 호출", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, IntroduceActivity.class));
                break;
            case R.id.search:
                Toast.makeText(MainActivity.this, "검색 액티비티 호출", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.bookMark:
                Toast.makeText(MainActivity.this, "즐겨찾기 다이얼로그 호출", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("즐겨찾기에 추가할 영화를 선택하세요");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setSingleChoiceItems(R.array.movies, select,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                select = i;
                            }
                        });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] movie = getResources().getStringArray(R.array.movies);
                        String selectedMovie = movie[select];
                        Intent intent = new Intent(MainActivity.this, BookMarkActivity.class);
                        intent.putExtra("selected", selectedMovie);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
                break;
            case R.id.finish:
                finish();
                break;
        }
        return false;
    }


    //    테이블 전체의 내용을 Log에 출력하는 메소드
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

//        리스트의 내용을 로그에 출력
        for (MovieData item : movieDataList) {
            Log.i(TAG, item.toString());
        }

//        마무리 작업
        cursor.close();
        myDBHelper.close();

//        원본 리스트(itemList)를 새로 읽어왔으므로 어댑터에게 변경 통지
        myAdapter.notifyDataSetChanged();
    }

}
