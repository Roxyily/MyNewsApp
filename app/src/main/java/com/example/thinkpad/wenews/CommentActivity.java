package com.example.thinkpad.wenews;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    private ListView commentList; //评论列表
    private EditText commentInput; //评论输入框
    private Button commentSend; //评论发送按钮
    private CommentAdapter commentAdapter; //评论适配器
    private List<Comment> comments; //评论数据
    private SQLiteDatabase db; //数据库

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);
        initView(); //初始化视图
        initData(); //初始化数据
        initEvent(); //初始化事件
    }

    //初始化视图
    private void initView() {
        commentList = findViewById(R.id.comment_list);
        commentInput = findViewById(R.id.comment_input);
        commentSend = findViewById(R.id.comment_send);
    }

    //初始化数据
    private void initData() {
        comments = new ArrayList<>(); //创建评论数据集合
        commentAdapter = new CommentAdapter(this, comments); //创建评论适配器
        commentList.setAdapter(commentAdapter); //设置评论列表的适配器
        db = openOrCreateDatabase("comment.db", MODE_PRIVATE, null); //打开或创建数据库
        db.execSQL("create table if not exists comment (content text, time text)"); //创建评论表
        Cursor cursor = db.rawQuery("select * from comment", null); //查询评论表
        while (cursor.moveToNext()) {
            String content = cursor.getString(0); //获取评论内容
            String time = cursor.getString(1); //获取评论时间
            Comment comment = new Comment(content, time); //创建评论对象
            comments.add(comment); //添加到评论数据集合
        }
        cursor.close(); //关闭游标
        commentAdapter.notifyDataSetChanged(); //更新评论列表
    }

    //初始化事件
    private void initEvent() {
        //设置评论发送按钮的点击事件
        commentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = commentInput.getText().toString(); //获取评论输入框的内容
                if (!TextUtils.isEmpty(content)) { //判断评论内容是否为空
                    Date date = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                    String time=dateFormat.format(date);
                    Comment comment = new Comment(content, time); //创建评论对象
                    comments.add(comment); //添加到评论数据集合
                    commentAdapter.notifyDataSetChanged(); //更新评论列表
                    commentInput.setText(""); //清空评论输入框
                    db.execSQL("insert into comment (content, time) values (?, ?)", new Object[]{content, time}); //插入评论数据到数据库
                    Toast.makeText(CommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show(); //提示评论成功
                } else {
                    Toast.makeText(CommentActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show(); //提示评论内容不能为空
                }
            }
        });
    }
}
