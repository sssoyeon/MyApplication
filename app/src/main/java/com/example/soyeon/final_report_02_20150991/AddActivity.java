package com.example.soyeon.final_report_02_20150991;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by soyeon on 2017. 6. 23..
 */

public class AddActivity extends AppCompatActivity {
    private MyDBHelper myDBHelper;

    //private EditText etId;
    private EditText etTitle;
    private EditText etDirector;
    private RatingBar rating;
    private DatePicker etReleaseDay;
    private TextView tvReleaseDay;
    private EditText etGenre;
    private EditText etActor;
    private EditText etStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDBHelper = new MyDBHelper(this);
        //etId = (EditText)findViewById(R.id.etId);
        etTitle = (EditText) findViewById(R.id.add_Title);
        etDirector = (EditText) findViewById(R.id.add_Director);
        rating = (RatingBar) findViewById(R.id.add_rating);
        etReleaseDay = (DatePicker) findViewById(R.id.add_releaseDay);
        tvReleaseDay = (TextView) findViewById(R.id.add_tvReleaseDay);

        etReleaseDay.init(etReleaseDay.getYear(), etReleaseDay.getMonth(), etReleaseDay.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        tvReleaseDay.setText(String.format("%d/%d/%d", i, i1 + 1, i2));
                    }
                });
        etGenre = (EditText) findViewById(R.id.add_Genre);
        etActor = (EditText) findViewById(R.id.add_Actor);
        etStory = (EditText) findViewById(R.id.add_Story);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btnSave:
//                쓰기 가능 데이터베이스 가져오기
                SQLiteDatabase db = myDBHelper.getWritableDatabase();

                ContentValues row = new ContentValues();
                //  row.put(MyDBHelper.COL_ID, etId.getText().toString());
                row.put(MyDBHelper.COL_Title, etTitle.getText().toString());
                row.put(MyDBHelper.COL_Director, etDirector.getText().toString());
                row.put(MyDBHelper.COL_Rating, rating.getRating());
                row.put(MyDBHelper.COL_ReleaseDay, tvReleaseDay.getText().toString());
                row.put(MyDBHelper.COL_Genre, etGenre.getText().toString());
                row.put(MyDBHelper.COL_Actor, etActor.getText().toString());
                row.put(MyDBHelper.COL_Story, etStory.getText().toString());

                //레코드 추가 - 레코드 추가를 성공할 경우 추가한 레코드 개수 반환
                long result = db.insert(MyDBHelper.TABLE_NAME, null, row);

                if (result > 0) Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "Failure!", Toast.LENGTH_SHORT).show();

//                마무리 작업
                myDBHelper.close();

                break;
            case R.id.add_btnCancle:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
