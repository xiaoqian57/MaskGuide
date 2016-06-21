package com.chenqq.mask;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
/*
* 方法二：一个蒙版，自定义view的background，点击图片或背景都消失，使用简单
* */
public class ImageViewMask extends AppCompatActivity implements View.OnClickListener{
    /*初次进入时的蒙版背景*/
    private LinearLayout mask_ll;
    /*初次进入时的蒙版图片*/
    private ImageView mask_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mask);
        mask_ll = (LinearLayout) findViewById(R.id.mask_ll);
        mask_iv = (ImageView) findViewById(R.id.mask_iv);
        /*设置监听*/
        mask_ll.setOnClickListener(this);
        mask_iv.setOnClickListener(this);
        /*设置蒙版*/
        setMask();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mask_ll:
                mask_ll.setVisibility(View.GONE);
                break;
            case R.id.mask_iv:
                mask_ll.setVisibility(View.GONE);
                this.getSharedPreferences("read",Context.MODE_PRIVATE).getBoolean("read",true);
                break;
            default:
                break;
        }
    }

    private void setMask() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("setting", Context.MODE_PRIVATE);
        boolean isRead = sharedPreferences.getBoolean("read",false);
        if (!isRead) {
           /* //调整图片大小以适应不同分辨率的屏幕
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            int height = dm.heightPixels;*/
            mask_iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            mask_ll.setVisibility(View.VISIBLE);
        } else {
            mask_ll.setVisibility(View.GONE);
        }
    }

}
