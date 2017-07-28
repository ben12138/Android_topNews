package com.jit.mayy.mytopnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jit.mayy.mytopnews.AppApplication;
import com.jit.mayy.mytopnews.Utils;
import com.jit.mayy.mytopnews.dao.ChannelItem;
import com.jit.mayy.mytopnews.R;
import com.jit.mayy.mytopnews.dao.ChannelManage;
import com.jit.mayy.mytopnews.edit.ChannelActivity;
import com.jit.mayy.mytopnews.view.ColumnHorizontalScrollView;

import java.util.ArrayList;

public class Page1Fragment extends Fragment{

    View page1;
    FragmentManager fm;

    private ColumnHorizontalScrollView mColumnHorizontalScrollView; // 自定义HorizontalScrollView
    private LinearLayout mRadioGroup_content; // 每个标题

    private LinearLayout ll_more_columns; // 右边+号的父布局
    private ImageView button_more_columns; // 标题右边的+号

    private RelativeLayout rl_column; // +号左边的布局：包括HorizontalScrollView和左右阴影部分
    public ImageView shade_left; // 左阴影部分
    public ImageView shade_right; // 右阴影部分

    private int columnSelectIndex = 0; // 当前选中的栏目索引
    private int mItemWidth = 0; // Item宽度：每个标题的宽度

    private int mScreenWidth = 0; // 屏幕宽度

    public final static int CHANNELREQUEST = 1; // 请求码
    public final static int CHANNELRESULT = 10; // 返回码

    // tab集合：HorizontalScrollView的数据源
    private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();

    private ViewPager mViewPager;
    private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();

    public Page1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        page1 = inflater.inflate(R.layout.fragment_page1, container, false);
        fm = getFragmentManager();

        mScreenWidth = Utils.getWindowsWidth(getActivity());
        mItemWidth = mScreenWidth / 6; // 一个Item宽度为屏幕的1/6

        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) page1.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) page1.findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) page1.findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) page1.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) page1.findViewById(R.id.button_more_columns);
        shade_left = (ImageView) page1.findViewById(R.id.shade_left);
        shade_right = (ImageView) page1.findViewById(R.id.shade_right);
        mViewPager = (ViewPager) page1.findViewById(R.id.mViewPager);

        setChangelView();

        return page1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*Bundle bundle = new Bundle();
        bundle.putString("key", "11");

        KejiFra newsFragment = new KejiFra();
        newsFragment.setArguments(bundle);

        FragmentTransaction ft = fm.beginTransaction();
        ft.commit();*/
        // + 号监听
        button_more_columns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_channel = new Intent(page1.getContext(), ChannelActivity.class);
                startActivityForResult(intent_channel, CHANNELREQUEST);
            }
        });

    }

    /**
     * 当栏目项发生变化时候调用
     */
    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData() {
        userChannelList = ((ArrayList<ChannelItem>) ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).getUserChannel());
    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = userChannelList.size();
        mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            TextView columnTextView = new TextView(getActivity());
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(userChannelList.get(i).getName());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }

            // 单击监听
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                        }else{
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i);
                        }
                    }
                    Toast.makeText(getActivity(), userChannelList.get(v.getId()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragmentsList.clear();//清空
        int count = userChannelList.size();
        for (int i = 0; i < count; i++) {
            switch (i){
                case 0:
                    AppleFra appleFra = new AppleFra();
                    fragmentsList.add(appleFra);
                    break;
                case 1:
                    MobileFra mobileFra = new MobileFra();
                    fragmentsList.add(mobileFra);
                    break;
                case 2:
                    TravelFra travelFra = new TravelFra();
                    fragmentsList.add(travelFra);
                    break;
                case 3:
                    HealthFra healthFra = new HealthFra();
                    fragmentsList.add(healthFra);
                    break;
                case 4:
                    MilitaryFra militaryFra = new MilitaryFra();
                    fragmentsList.add(militaryFra);
                    break;
                default:
                    KejiFra kejiFra = new KejiFra();
                    fragmentsList.add(kejiFra);
                    break;
            }

        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getFragmentManager(), fragmentsList);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.addOnPageChangeListener(pageListener);
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mViewPager.setCurrentItem(position);
            //传tab设置新闻内容
            /*Bundle bundle = new Bundle();
            bundle.putString("key", "11");

            KejiFra newsFragment = new KejiFra();
            newsFragment.setArguments(bundle);

            FragmentTransaction ft = fm.beginTransaction();
            ft.commit();*/

            selectTab(position);
        }
    };

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;
        for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
            View checkView = mRadioGroup_content.getChildAt(tab_postion);
            int k = checkView.getMeasuredWidth();
            int l = checkView.getLeft();
            int i2 = l + k / 2 - mScreenWidth / 2;
            mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        }
        //判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkView = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkView.setSelected(ischeck);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHANNELREQUEST:
                if (resultCode == CHANNELRESULT) {
                    setChangelView();
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
