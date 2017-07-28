package com.jit.mayy.mytopnews;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jit.mayy.mytopnews.fragment.Page1Fragment;
import com.jit.mayy.mytopnews.fragment.Page2Fragment;
import com.jit.mayy.mytopnews.fragment.Page3Fragment;
import com.jit.mayy.mytopnews.fragment.Page4Fragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    String url_baoliao="http://192.168.191.1:8080/newsapp_listNews";

    Page1Fragment page1;
    Page2Fragment page2;
    Page3Fragment page3;
    Page4Fragment page4;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        View layout1 = findViewById(R.id.layout1);
        View layout2 = findViewById(R.id.layout2);
        View layout3 = findViewById(R.id.layout3);
        View layout4 = findViewById(R.id.layout4);
        img1 = (ImageView)findViewById(R.id.image1);
        img2 = (ImageView)findViewById(R.id.image2);
        img3 = (ImageView)findViewById(R.id.image3);
        img4 = (ImageView)findViewById(R.id.image4);
        //初始化第一个fragment
        setTabSelection(1);
        /*Bundle bundle = new Bundle();
        bundle.putString("key", "11");

        KejiFra newsFragment = new KejiFra();
        newsFragment.setArguments(bundle);

        FragmentTransaction ft = fm.beginTransaction();
        ft.commit();*/
        img1.setImageResource(R.drawable.b_newhome_tabbar_press);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(1);
                img1.setImageResource(R.drawable.b_newhome_tabbar_press);
                img2.setImageResource(R.drawable.b_newvideo_tabbar);
                img3.setImageResource(R.drawable.b_newcare_tabbar);
                img4.setImageResource(R.drawable.b_newmine_tabbar);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(2);
                img1.setImageResource(R.drawable.b_newhome_tabbar);
                img2.setImageResource(R.drawable.b_newvideo_tabbar_press);
                img3.setImageResource(R.drawable.b_newcare_tabbar);
                img4.setImageResource(R.drawable.b_newmine_tabbar);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(3);
                page3.getNetData(url_baoliao);
                img1.setImageResource(R.drawable.b_newhome_tabbar);
                img2.setImageResource(R.drawable.b_newvideo_tabbar);
                img3.setImageResource(R.drawable.b_newcare_tabbar_press);
                img4.setImageResource(R.drawable.b_newmine_tabbar);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(4);
                img1.setImageResource(R.drawable.b_newhome_tabbar);
                img2.setImageResource(R.drawable.b_newvideo_tabbar);
                img3.setImageResource(R.drawable.b_newcare_tabbar);
                img4.setImageResource(R.drawable.b_newmine_tabbar_press);
            }
        });

        String username = getIntent().getStringExtra("username");
        String flag = getIntent().getStringExtra("flag");
        if(username != null){
            FragmentTransaction ft2 = fm.beginTransaction();
            hideFragments(ft2);
            if (page4 == null) {
                page4 = new Page4Fragment();
                ft2.add(R.id.content, page4);
            } else {
                ft2.show(page4);
            }
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            page4.setArguments(bundle);
            ft2.commit();

            img1.setImageResource(R.drawable.b_newhome_tabbar);
            img2.setImageResource(R.drawable.b_newvideo_tabbar);
            img3.setImageResource(R.drawable.b_newcare_tabbar);
            img4.setImageResource(R.drawable.b_newmine_tabbar_press);
        }else{
            setTabSelection(1);
        }
        if(flag != null){
            FragmentTransaction ft3 = fm.beginTransaction();
            hideFragments(ft3);
            if (page3 == null) {
                page3 = new Page3Fragment();
                ft3.add(R.id.content, page3);
            } else {
                ft3.show(page3);
            }
            ft3.commit();
            page3.getNetData(url_baoliao);

            img1.setImageResource(R.drawable.b_newhome_tabbar);
            img2.setImageResource(R.drawable.b_newvideo_tabbar);
            img3.setImageResource(R.drawable.b_newcare_tabbar_press);
            img4.setImageResource(R.drawable.b_newmine_tabbar);
        }

    }

    /**
     * 选择fragment
     * @param index
     */
    private void setTabSelection(int index) {
        FragmentTransaction transaction = fm.beginTransaction();

        hideFragments(transaction);
        switch (index) {
            case 1:
                if (page1 == null) {
                    page1 = new Page1Fragment();
                    transaction.add(R.id.content, page1);
                } else {
                    transaction.show(page1);
                }
                break;
            case 2:
                if (page2 == null) {
                    page2 = new Page2Fragment();
                    transaction.add(R.id.content, page2);
                } else {
                    transaction.show(page2);
                }
                break;
            case 3:
                if (page3 == null) {
                    page3 = new Page3Fragment();
                    transaction.add(R.id.content, page3);
                } else {
                    transaction.show(page3);
                }
                break;
            case 4:
                if (page4 == null) {
                    page4 = new Page4Fragment();
                    transaction.add(R.id.content, page4);
                } else {
                    transaction.show(page4);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏fragment
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (page1 != null) {
            transaction.hide(page1);
        }
        if (page2 != null) {
            transaction.hide(page2);
        }
        if (page3 != null) {
            transaction.hide(page3);
        }
        if (page4 != null) {
            transaction.hide(page4);
        }
    }

}
