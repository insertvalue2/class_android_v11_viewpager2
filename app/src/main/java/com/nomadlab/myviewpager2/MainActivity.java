package com.nomadlab.myviewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nomadlab.myviewpager2.adapter.ImageSliderAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 sliderImageViewPager;
    private LinearLayout linearLayoutIndicator;
    private ArrayList<String> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup sample data
        images.add("https://cdn.pixabay.com/photo/2021/12/01/15/10/happy-new-year-6838220__340.jpg");
        images.add("https://cdn.pixabay.com/photo/2021/12/19/02/29/celebration-6880059__340.png");
        images.add("https://cdn.pixabay.com/photo/2016/12/15/14/48/new-years-eve-1909061__340.jpg");
        images.add("https://cdn.pixabay.com/photo/2019/11/27/12/23/clock-4656853__340.jpg");

        sliderImageViewPager = findViewById(R.id.sliderViewPager2);
        linearLayoutIndicator = findViewById(R.id.layoutIndicators);

        sliderImageViewPager.setOffscreenPageLimit(4);
        sliderImageViewPager.setAdapter(new ImageSliderAdapter(this, images));

        sliderImageViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.size());
    }

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        // 뷰가 어떻게 배치 될지 정의하는 속성
        // xml 파일에서 layout_ 붙는 속성들을 말하며
        // 코드상에서 LayoutParams 객첵를 통해 파라미터 속성을
        // 다룰 수 있다.
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            linearLayoutIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int position) {
        int chileCount = linearLayoutIndicator.getChildCount();
        for (int i = 0; i < chileCount; i++) {
            ImageView imageView = (ImageView) linearLayoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_inactive));
            }
        }
    }


}