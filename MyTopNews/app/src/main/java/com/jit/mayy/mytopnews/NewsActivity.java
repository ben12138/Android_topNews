package com.jit.mayy.mytopnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        WebView show = (WebView) findViewById(R.id.webView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar) ;

        String url = this.getIntent().getStringExtra("url");
        String title = this.getIntent().getStringExtra("title");
        String picUrl = this.getIntent().getStringExtra("picUrl");
        //ImageView image = (ImageView) findViewById(R.id.news_image_view);
        //Picasso.with(this).load(picUrl).into(image);
        toolbar.setTitle(title);
        show.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        show.loadUrl(url);
    }
}
