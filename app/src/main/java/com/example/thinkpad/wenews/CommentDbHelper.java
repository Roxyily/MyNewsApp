package com.example.thinkpad.wenews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CommentDbHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1; //数据库版本号
    public static final String DBNAME = "Comments.db"; //数据库名
    public  static final String TABLE_NAME = "comment_tb"; //表名
    public static final String COLUMN_ID = "_id"; //评论ID
    public static final String COLUMN_NEWS_ID = "news_id"; //新闻ID
    public static final String COLUMN_USER_ID = "user_id"; //用户ID
    public static final String COLUMN_CONTENT = "content"; //评论内容
    public static final String COLUMN_TIME = "time"; //评论时间

    public CommentDbHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    //创建数据库
    public void onCreate(SQLiteDatabase db) {
        //创建评论表
        db.execSQL("create table " + TABLE_NAME + " (" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_NEWS_ID + " integer not null, " +
                COLUMN_USER_ID + " integer not null, " +
                COLUMN_CONTENT + " text not null, " +
                COLUMN_TIME + " text not null)");
    }

    //数据库版本更新
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除旧的评论表
        db.execSQL("drop table if exists " + TABLE_NAME);
        //重新创建新的评论表
        onCreate(db);
    }
}
