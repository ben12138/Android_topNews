package com.jit.mayy.mytopnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jit.mayy.mytopnews.R;
import com.jit.mayy.mytopnews.ResultItem;
import com.jit.mayy.mytopnews.ResultMain;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Description：ViewPager切换的Fragment
 */
public class MilitaryFra extends Fragment {

    ListView listview;

    String url_military="https://api.tianapi.com/military/?key=d72674b671da40526f97fc9ebb7721b3&num=10";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frView = inflater.inflate(R.layout.fragment_military, null);
        listview=(ListView)frView.findViewById(R.id.lv_military);
        getNetData(url_military);
        return frView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 根据url获取新闻内容
     * @param url
     */
    private void getNetData(String url) {
        OkGo.get(url).tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(String arg0, Call arg1, Response arg2) {
                Gson gson = new Gson();
                ResultMain main = gson.fromJson(arg0, ResultMain.class);
                MilitaryFra.MyBaseAdapter adapter = new MilitaryFra.MyBaseAdapter(main.getNewslist());
                listview.setAdapter(adapter);
            }
        });
    }

    /**
     * 适配器加载新闻内容
     */
    class MyBaseAdapter extends BaseAdapter {

        private List<ResultItem> data;
        public MyBaseAdapter(List<ResultItem> list) {
            this.data=list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return data.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.simple_item, null);

            ImageView img=(ImageView)view.findViewById(R.id.img);
            TextView title=(TextView)view.findViewById(R.id.title);
            TextView subtitle=(TextView)view.findViewById(R.id.subtitle);

            ResultItem item=data.get(arg0);

            title.setText(item.getTitle());
            subtitle.setText(item.getDescription());
            //加载图片
            if(!TextUtils.isEmpty(item.getPicUrl())){
                Picasso.with(getActivity()).load(item.getPicUrl()).into(img);
            }
            return view;
        }
    }
}
