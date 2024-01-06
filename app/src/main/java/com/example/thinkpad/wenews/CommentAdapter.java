package com.example.thinkpad.wenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Context context; //上下文
    private List<Comment> commentList; //评论列表

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
            holder = new ViewHolder();
            holder.content = convertView.findViewById(R.id.comment_content);
            holder.time = convertView.findViewById(R.id.comment_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Comment comment = commentList.get(position);
        holder.content.setText(comment.getContent());
        holder.time.setText(comment.getTime());
        return convertView;
    }

    static class ViewHolder {
        TextView content; //评论内容
        TextView time; //评论时间
    }
}
