package com.chenqq.mask;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/*
* 方法一：三个新功能的引导效果，重点是GuideView的使用，适合多个功能
* */
public class MainActivity extends AppCompatActivity {

    private ImageButton menu;
    private Button btn1,btn2;
    private GuideView guideView,guideView2,guideView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (ImageButton) findViewById(R.id.ib_menu);
        btn1 = (Button) findViewById(R.id.btn_test);
        btn2 = (Button) findViewById(R.id.btn_test2);
    }
    private void setGuideView() {
        //使用图片的效果
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.img_new_task_guide);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(params);

        //使用文字的效果
        TextView tv = new TextView(this);
        tv.setText("欢迎使用");
        tv.setTextColor(ContextCompat.getColor(this,R.color.white));
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);

        TextView tv2 = new TextView(this);
        tv2.setText("welcome back");
        tv2.setTextColor(ContextCompat.getColor(this,R.color.white));
        tv2.setTextSize(30);
        tv2.setGravity(Gravity.CENTER);

        guideView = GuideView.Builder
                .newInstance(this)
                .setTargetView(menu)
                .setCustomGuideView(iv)//使用图片的imageView
                .setDirection(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.CIRCULAR)//设置圆形区域
                .setBgColor(ContextCompat.getColor(this,R.color.transparent))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView.hide();
                        guideView2.show();
                    }
                } )
                .build();

        guideView2 = GuideView.Builder
                .newInstance(this)
                .setTargetView(btn1)
                .setCustomGuideView(tv)//使用文字的textView
                .setDirection(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.ELLIPSE)//设置椭圆形
                .setBgColor(ContextCompat.getColor(this,R.color.transparent))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView2.hide();
                        guideView3.show();
                    }
                })
                .build();

        guideView3 = GuideView.Builder
                .newInstance(this)
                .setTargetView(btn2)
                .setCustomGuideView(tv2)
                .setDirection(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.RECTANGULAR)//设置矩形
                .setRadius(30)// 设置圆形或矩形透明区域半径，默认是targetView的显示矩形的半径，如果是矩形，这里是设置矩形圆角大小
                .setBgColor(ContextCompat.getColor(this,R.color.transparent))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView3.hide();
                        guideView.show();
                    }
                })
                .build();
        guideView.show();//启动默认的View
    }

    @Override
    protected void onResume() {
        super.onResume();
        setGuideView();
    }
}
