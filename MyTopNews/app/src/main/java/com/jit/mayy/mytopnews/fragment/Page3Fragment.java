package com.jit.mayy.mytopnews.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jit.mayy.mytopnews.NewsActivity;
import com.jit.mayy.mytopnews.R;
import com.jit.mayy.mytopnews.ResultItem;
import com.jit.mayy.mytopnews.ResultMain;
import com.jit.mayy.mytopnews.ResultNews;
import com.jit.mayy.mytopnews.WriteNews;
import com.jit.mayy.mytopnews.domain.News;
import com.jit.mayy.mytopnews.myNew;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.R.id.list;

public class Page3Fragment extends Fragment {

    ListView listView_baoliao;
    TextView textView_baoliao;
    String url_pic;
    String url_baoliao="http://192.168.191.1:8080/newsapp_listNews";
    public Page3Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page3, container, false);
        listView_baoliao = (ListView)view.findViewById(R.id.listView_baoliao);
        textView_baoliao = (TextView) view.findViewById(R.id.writeNews);

        //单击跳转编辑新闻界面
        textView_baoliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent write_Intent = new Intent(getActivity(), WriteNews.class);
                startActivity(write_Intent);
            }
        });
        getNetData(url_baoliao);
        return view;
    }

    public void getNetData(String url) {
        OkGo.get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(String arg0, Call arg1, Response arg2) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<List<News>>() {}.getType();
                final List<News> list1 = gson.fromJson(arg0,type);
                Page3Fragment.MyBaseAdapter adapter = new Page3Fragment.MyBaseAdapter(list1);
                listView_baoliao.setAdapter(adapter);

                listView_baoliao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), myNew.class);
                        intent.putExtra("title",list1.get(position).getTitle());
                        intent.putExtra("content",list1.get(position).getContent());
                        if(url_pic != null){
                            intent.putExtra("picUrl",url_pic);
                        }
                        startActivity(intent);
                    }
                });
            }
        });
    }
    class MyBaseAdapter extends BaseAdapter {

        private List<News> data;
        public MyBaseAdapter(List<News> list) {
            this.data=list;
            //System.out.println("----"+data.get(0).getTitle());
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            System.out.println(arg0);
            return data.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            //System.out.println("--------------执行到了");
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.simple_item, null);

            ImageView img=(ImageView)view.findViewById(R.id.img);
            TextView title=(TextView)view.findViewById(R.id.title);
            TextView subtitle=(TextView)view.findViewById(R.id.subtitle);

            News item=data.get(arg0);
            title.setText(item.getTitle());
            subtitle.setText(item.getDescription());
            url_pic = item.getPicUrl();
            //System.out.println("==================="+item.getPicUrl());
            if(item.getPicUrl() != null){
                Bitmap bm = BitmapFactory.decodeFile(item.getPicUrl());
                img.setImageBitmap(bm);
                //Picasso.with(getActivity()).load(item.getPicUrl()).into(img);
            }
            return view;
        }
    }

}
