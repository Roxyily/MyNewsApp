package com.example.thinkpad.wenews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Comment.CommentActivity;

public class ContentWebview extends AppCompatActivity {
    private TextView text_title,text_info,text_content;
    String title=new String();
    String author=new String();
    String time=new String() ;
    String imgAdress=new String();
    String address=new String();
    ProgressDialog progressDialog ;
    StringBuffer buffer=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_webview);
        Intent intent=getIntent();
        address=intent.getStringExtra("address");

        WebView webView=(WebView) findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //支持缩放

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(address);
       /* text_title=(TextView)findViewById(R.id.title);
        text_info=(TextView)findViewById(R.id.author_time);
        text_content=(TextView)findViewById(R.id.content);
       crawler(address);*/
        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null)
//            actionBar.hide();
//        ImageButton button=(ImageButton)findViewById(R.id.back);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
    public void crawler( final String href ){
        //   String text=null;
        new Thread() {
            @Override
            public void run() {

                super.run();
                try {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.show();
                        }
                    });

                    Document doc = Jsoup.connect(href).get();
                    Log.d("12345",doc.html());
                    Log.d("kh1",""+href);


                    Element head=doc.select("p").first();
                    Log.d("head",""+head.text());
                    title=head.select("h1.title").first().text();
                    author =head.select("span.author").first().text();
                    time =head.select("span.time.js-time").first().text();
                    imgAdress=doc.getElementsByTag("a[href]").first().attr("href");
                    Log.d("bhk123",title);
                    Log.d("img",imgAdress);
                    Element content=doc.select("div.content.fontsmall").first();
                    Log.d("bhk456",content.text());
                    Elements paragraphs=content.select("p");
                    buffer.append("\n");
                    for(Element para:paragraphs){
                        buffer.append("     "+para.text()+"\n\n");
                        Log.d("p_p",para.text());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text_title.setText("    "+title);
                            text_info.setText("  来源："+author+" 时间："+time);
                            text_content.setText(buffer);
                            progressDialog.dismiss();
                        }
                    });


                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }


    //重写顶部菜单栏构造方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.article_open_browser,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //设置返回按钮事件
                finish();
                return true;
            case R.id.article_open_browser:
                //设置浏览器打开事件
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(address));//跳转到网页
                startActivity(intent);
                return true;
            case R.id.comment:
                //设置打开评论页面事件
                Intent intent_com = new Intent(ContentWebview.this, CommentActivity.class);
                //使用putExtra方法，传递新闻地址
                intent_com.putExtra("news_address", address);
                //调用startActivity方法，启动评论页面
                startActivity(intent_com);

                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
