package Comment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private String content; //评论内容
    private String time; //评论时间

    public Comment(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        this.time=dateFormat.format(date);
        System.out.println(dateFormat.format(date));
        return time;
    }

    //省略getter和setter方法
}
