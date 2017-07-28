package com.jit.mayy.mytopnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class myNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1) ;
        TextView textView = (TextView) findViewById(R.id.text111);
        ImageView imageView = (ImageView) findViewById(R.id.img111);
        String title = this.getIntent().getStringExtra("title");
        String content = this.getIntent().getStringExtra("content");
        String picUrl = this.getIntent().getStringExtra("picUrl");

        toolbar.setTitle(title);
        textView.setText(content);
        Bitmap bm = BitmapFactory.decodeFile(picUrl);
        imageView.setImageBitmap(bm);
    }
}
