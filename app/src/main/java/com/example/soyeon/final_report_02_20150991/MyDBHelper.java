package com.example.soyeon.final_report_02_20150991;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by soyeon on 2017. 6. 23..
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "my_db";

    //    테이블 명 및 컬럼 명은 외부에서 많이 참조하므로 상수로 선언
    public static final String TABLE_NAME = "my_table";

    public static final String COL_ID = "_id";
    public static final String COL_Title = "title";
    public static final String COL_Director = "director";
    public static final String COL_Rating = "rating";
    public static final String COL_ReleaseDay = "releaseDay";
    public static final String COL_Genre = "genre";
    public static final String COL_Actor = "actor";
    public static final String COL_Story = "story";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_Title + " TEXT, " + COL_Director + " TEXT, " + COL_Rating + " TEXT, "
                + COL_ReleaseDay + " TEXT, " + COL_Genre + " TEXT, " + COL_Actor + " TEXT, " + COL_Story + " TEXT)";
        db.execSQL(createTable);

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, 'TransFormer', '마이클 베이', '3.5','2017/6/1', '액션', '마크 월버그','두 세상의 충돌, 하나만 살아남는다!\n" +
                "옵티머스 프라임은 더 이상 인간의 편이 아니다. \n" +
                " 트랜스포머의 고향 사이버트론의 재건을 위해 지구에 있는 고대 유물을 찾아나선 옵티머스 프라임은 인류와 피할 수 없는 갈등을 빚고, 오랜 동료 범블비와도 치명적인 대결을 해야만 하는데… ');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, 'WonderWoman', '패티 젠킨스', '3.0','2017/5/31', '판타지', '갤 가돗','아마존 데미스키라 왕국의 공주 ‘다이애나 프린스’(갤 가돗)는 \n" +
                " 전사로서 훈련을 받던 중 최강 전사로서의 운명을 직감한다. \n" +
                " 때마침 섬에 불시착한 조종사 ‘트레버 대위’(크리스 파인)를 통해 \n" +
                " 인간 세상의 존재와 그 곳에서 전쟁이 일어나고 있음을 알게 된다. ');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '미이라', '알렉스 커츠만', '2.5','2017/6/6', '액션', '톰 크루즈', '수천 년 만에 잠에서 깨어난 아마네트는 분노와 파괴의 강력한 힘으로 전 세상을 자신의 것으로 만들려 하고, 지킬 박사(러셀 크로우)는 닉에게 의미심장한 이야기를 전하게 되는데... \n" +
                "  \n" +
                " 건드려선 안 될 강력한 존재와 이에 맞선 무한의 힘 \n" +
                " 마침내 세상을 구할 숙명적인 전쟁이 시작된다!');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, '심야식당', '마츠오카 조지', '4.5','2017/6/8', '드라마', '코바야시 카오루', '첫 번째 요리, 불고기 정식\n" +
                "\"+\"두 번째 요리, 볶음 우동과 메밀 국수\\n\"+\"세 번째 요리, 돼지고기 된장국 정식 \n');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, 'DarkHouse', '대런 린 보우즈만', '4.0','2017/6/22', '공포', '제시카 론디스', '이유를 알 수 없는 연쇄 살인 사건으로 가족을 모두 잃은 ‘줄리아’ \n" +
                " 증거 부족으로 범인을 찾지 못한 채 수사가 종결되자 \n" +

                " 혼자 사건을 해결하기로 결심한다');");


        //리스트에 데이터 넣기

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        테이블 업그레이드가 필요할 경우 업그레이드 내용 작성
    }
}
