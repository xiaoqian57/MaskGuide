package com.chenqq.mask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/*
* 方法三：需要添加一个方法，有一张功能引导的图片即可（就是感觉很假...）
* */
public class BackgroundActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backmask);
        addGuideImage();
    }

    /*添加引导图片*/
    public void addGuideImage(){
        //查找根布局ID
        View view = this.getWindow().getDecorView().findViewById(R.id.my_root_view);
        if (view == null) return;
        //引导过了
        if (!TextUtils.isEmpty(AppContext.getInstance().getProperty("guideTips"))) {return;}
        int guideResourceId = R.drawable.mask_background;
        ViewParent parent = view.getParent();
        if (parent instanceof FrameLayout) {
            final FrameLayout frameLayout = (FrameLayout) parent;
            if (guideResourceId != 0) {//设置了引导图片
                final ImageView guideImage = new ImageView(this);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                guideImage.setLayoutParams(params);
                guideImage.setScaleType(ImageView.ScaleType.FIT_XY);
                guideImage.setImageResource(guideResourceId);
                guideImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frameLayout.removeView(guideImage);
                        AppContext.getInstance().setProperty("guideTips","0");
                    }
                });
                frameLayout.addView(guideImage);
            }
        }
    }
}
