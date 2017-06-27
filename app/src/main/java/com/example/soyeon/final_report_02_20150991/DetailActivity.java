package com.example.soyeon.final_report_02_20150991;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    int mYear, mMonth, mDay;
    private MyDBHelper myDBHelper;
    private EditText title;
    private EditText genre;
    private EditText director;
    private EditText actor;
    private RatingBar rating;
    private EditText story;
    private TextView de_tvReleaseDay;
    private MovieData mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        myDBHelper = new MyDBHelper(this);
        Intent recievedIntent = getIntent();

        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        mv = (MovieData) getIntent().getExtras().get("movie_data");

        ImageView img = (ImageView) findViewById(R.id.de_mvImg);
        de_tvReleaseDay = (TextView) findViewById(R.id.de_tvReleaseDay);
        img.setAdjustViewBounds(true);

        //조건문을 사용하여 각 영화에 맞는 포스터와 개봉일을 보여준다.
        //항목들 롱클릭시 수정할 수 있도록 Text를 비워줌
        //개봉일에 해당하는 항목 롱클릭시 DatePickerDialog 띄움
        if (mv.getTitle().equals("TransFormer")) {
            img.setImageResource(R.drawable.img1);
            de_tvReleaseDay.setText(mv.getReleaseDay());
            de_tvReleaseDay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new DatePickerDialog(DetailActivity.this, mDateSetListner, mYear, mMonth, mDay).show();
                    return true;
                }
            });
        } else if (mv.getTitle().equals("WonderWoman")) {
            img.setImageResource(R.drawable.img2);
            de_tvReleaseDay.setText(mv.getReleaseDay());
            de_tvReleaseDay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new DatePickerDialog(DetailActivity.this, mDateSetListner, mYear, mMonth, mDay).show();
                    return true;
                }
            });
        } else if (mv.getTitle().equals("미이라")) {
            img.setImageResource(R.drawable.img3);
            de_tvReleaseDay.setText(mv.getReleaseDay());
            de_tvReleaseDay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new DatePickerDialog(DetailActivity.this, mDateSetListner, mYear, mMonth, mDay).show();
                    return true;
                }
            });
        } else if (mv.getTitle().equals("심야식당")) {
            img.setImageResource(R.drawable.img4);
            de_tvReleaseDay.setText(mv.getReleaseDay());
            de_tvReleaseDay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new DatePickerDialog(DetailActivity.this, mDateSetListner, mYear, mMonth, mDay).show();
                    return true;
                }
            });
        } else if (mv.getTitle().equals("DarkHouse")) {
            img.setImageResource(R.drawable.img5);
            de_tvReleaseDay.setText(mv.getReleaseDay());
            de_tvReleaseDay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new DatePickerDialog(DetailActivity.this, mDateSetListner, mYear, mMonth, mDay).show();
                    return true;
                }
            });
        }
        //새로 추가한 항목의 이미지와 날짜를 따로 받아오도록 줘야 상세항목에 반영
        else {
            img.setImageResource(R.mipmap.ic_launcher);
            de_tvReleaseDay.setText(mv.getReleaseDay());
            de_tvReleaseDay.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new DatePickerDialog(DetailActivity.this, mDateSetListner, mYear, mMonth, mDay).show();
                    return true;
                }
            });

        }

        title = (EditText) findViewById(R.id.cus_mvTitle);
        title.setText(mv.getTitle());
        title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                title.setText("");
                return true;
            }
        });

        genre = (EditText) findViewById(R.id.de_mvGenre);
        genre.setText(mv.getGenre());
        genre.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                genre.setText("");
                return true;
            }
        });

        director = (EditText) findViewById(R.id.de_mvDirector);
        director.setText(mv.getDirector());
        director.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                director.setText("");
                return true;
            }
        });

        actor = (EditText) findViewById(R.id.de_mvActor);
        actor.setText(mv.getActor());
        actor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                actor.setText("");
                return true;
            }
        });

        rating = (RatingBar) findViewById(R.id.de_mvRating);
        rating.setRating(mv.getRating());
        rating.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                rating.setRating(0);
                return true;
            }
        });

        story = (EditText) findViewById(R.id.de_mvStory);
        story.setText(mv.getStory());
        story.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                story.setText("");
                return true;
            }
        });
    }

    DatePickerDialog.OnDateSetListener mDateSetListner =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    mYear = i;
                    mMonth = i1;
                    mDay = i2;
                    UpdateNow();
                }
            };

    void UpdateNow() {
        de_tvReleaseDay.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updateBtn:
                SQLiteDatabase db = myDBHelper.getWritableDatabase();

                ContentValues row = new ContentValues();
                row.put(MyDBHelper.COL_Title, title.getText().toString());
                row.put(MyDBHelper.COL_ReleaseDay, de_tvReleaseDay.getText().toString());
                row.put(MyDBHelper.COL_Genre, genre.getText().toString());
                row.put(MyDBHelper.COL_Director, director.getText().toString());
                row.put(MyDBHelper.COL_Actor, actor.getText().toString());
                row.put(MyDBHelper.COL_Rating, rating.getRating());
                row.put(MyDBHelper.COL_Story, story.getText().toString());

                //                수정을 위한 where 절 구성
                String whereClause = MyDBHelper.COL_ID + "=?";
                String[] whereArgs = {Integer.toString(mv.get_id())};

//                레코드 수정 수행 - 수정 성공 시 수정한 레코드 개수 반환
                long result = db.update(MyDBHelper.TABLE_NAME, row, whereClause, whereArgs);

                if (result > 0) Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "Failure!", Toast.LENGTH_SHORT).show();

//                마무리 작업
                myDBHelper.close();

                break;

        }
    }
}
