package flappybird.failedcoder.com.demo2;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class getstarted extends Activity {
    private ViewPager Slideviewpager;
    private LinearLayout Dotlayout;
    private Button back;
    private Button finish;
    private int currentpage;
    private TextView []Dots;
    private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_getstarted);
        back = findViewById(R.id.back);
        finish = findViewById(R.id.finish);
        Slideviewpager = findViewById(R.id.sliderviewlayout);
        Dotlayout = findViewById(R.id.dotslayout);
        sliderAdapter = new SliderAdapter(this);

        Slideviewpager.setAdapter(sliderAdapter);
        addDotsIndicater(0);
        Slideviewpager.addOnPageChangeListener(viewListener);

    }

    public void addDotsIndicater(int position){
        Dots = new TextView[5];
        Dotlayout.removeAllViews();
        for(int i=0;i<Dots.length;i++){
            Dots[i] = new TextView(this);
            Dots[i].setText(Html.fromHtml("&#8226"));
            Dots[i].setTextSize(35);
            Dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            Dotlayout.addView(Dots[i]);
        }
        if(Dots.length > 0){
            Dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Slideviewpager.setCurrentItem(currentpage-1);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Slideviewpager.setCurrentItem(currentpage+1);
            }
        });


    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicater(position);
            currentpage = position;
            if(position == 0){
                back.setEnabled(false);
                finish.setEnabled(true);
                back.setVisibility(View.INVISIBLE);
                finish.setText("Next");
                back.setText("");
            }else if(position == Dots.length - 1){
                back.setEnabled(true);
                finish.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                finish.setText("Finish");
                back.setText("Back");
                finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(finish.getText() == "Finish") {
                            startActivity(new Intent(getstarted.this, LoginActivity.class));
                        }
                    }
                });
            }else {
                back.setEnabled(true);
                finish.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                finish.setText("Next");
                back.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}