package com.jit.mayy.mytopnews;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jit.mayy.mytopnews.fragment.Page3Fragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class WriteNews extends AppCompatActivity {

    private static final String ACTIVITY_TAG="writeNews";

    ImageView img;
    EditText edit_title;
    EditText edit_content;
    EditText edit_description;
    Button edit_btn;
    String pic_path;
    //StringBuffer url_submit=new StringBuffer("http://localhost:8080/newsapp_addnews");
    String url_submit="http://192.168.191.1:8080/newsapp_addnews";

    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_news);

        img = (ImageView) findViewById(R.id.edit_pic);
        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_content = (EditText) findViewById(R.id.edit_content);
        edit_description = (EditText) findViewById(R.id.edit_description);
        edit_btn = (Button) findViewById(R.id.edit_button);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img_intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                img_intent.setType(IMAGE_TYPE);
                startActivityForResult(img_intent,IMAGE_CODE);
            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*url_submit.append("?title=")
                        .append(edit_title.getText().toString())
                        .append("&imgUrl=")
                        .append(pic_path)
                        .append("&content=")
                        .append(edit_content.getText());
                String url_submit1 = url_submit.toString();
                System.out.println(url_submit1);*/
                getNetData(url_submit);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        Bitmap bm = null;
        ContentResolver resolver = WriteNews.this.getContentResolver();
        if (requestCode == IMAGE_CODE) {
            try {
                Uri originalUri = data.getData();
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = WriteNews.this.managedQuery(originalUri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);
                pic_path = path;
                Picasso.with(WriteNews.this).load(new File(path)).into(img);
            } catch (IOException e) {
                Log.e("Lost img", e.toString());
            }
        }
    }

    private void getNetData(String url) {
        //System.out.println("----------请求");
        OkGo.post(url)
                .params("title",edit_title.getText().toString())
                .params("description",edit_description.getText().toString())
                .params("imgUrl",pic_path)
                .params("content",edit_content.getText().toString())
                .execute(new StringCallback() {
            @Override
            public void onSuccess(String arg0, Call arg1, Response arg2){
                if(arg0.equals("1")){
                    Log.i(WriteNews.ACTIVITY_TAG,"add myNews success");
                    Toast.makeText(WriteNews.this,"添加成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(WriteNews.this, MainActivity.class);
                    intent.putExtra("flag","success");
                    startActivity(intent);
                }else {
                    Toast.makeText(WriteNews.this,"添加失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
