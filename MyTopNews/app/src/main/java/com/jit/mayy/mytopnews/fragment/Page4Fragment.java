package com.jit.mayy.mytopnews.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jit.mayy.mytopnews.Login;
import com.jit.mayy.mytopnews.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class Page4Fragment extends Fragment {

    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    ImageView img_avatar,img_header_click_tips;
    TextView user_name;
    View page4View;

    public Page4Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        page4View = inflater.inflate(R.layout.fragment_page4, container, false);
        user_name = (TextView) page4View.findViewById(R.id.txt_user_name);
        img_avatar = (ImageView) page4View.findViewById(R.id.img_avatar);
        img_header_click_tips = (ImageView) page4View.findViewById(R.id.img_header_click_tips);
        //登录界面载入
        img_header_click_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        //选择头像
        img_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img_intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                img_intent.setType(IMAGE_TYPE);
                startActivityForResult(img_intent,IMAGE_CODE);
            }
        });

        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            user_name.setText(bundle.getString("username"));
            System.out.println("------------------------------------"+bundle.getString("username"));
        }

        return page4View;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            //此处的 RESULT_OK 是系统自定义得一个常量
            //Log.e(TAG,"ActivityResult resultCode error");
            return;
        }
        Bitmap bm = null;
        ContentResolver resolver = getActivity().getContentResolver();
        if (requestCode == IMAGE_CODE) {
            try {
                //获得图片的uri
                Uri originalUri = data.getData();
                //显得到bitmap图片
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                //这里开始的第二部分，获取图片的路径：
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().managedQuery(originalUri, proj, null, null, null);
                //按我个人理解 这个是获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                //最后根据索引值获取图片路径
                String path = cursor.getString(column_index);
                Picasso.with(getActivity()).load(new File(path)).into(img_avatar);
                //Log.e("Lostinai",path);
            } catch (IOException e) {
                Log.e("Lostinai", e.toString());
            }

        }

    }
}
