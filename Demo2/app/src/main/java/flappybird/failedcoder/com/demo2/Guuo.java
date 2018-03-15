package flappybird.failedcoder.com.demo2;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Guuo extends AppCompatActivity {

    private TextView Score;
    private TextView Start;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;
    private int frameHeight;
    private int boxSize;
    private int screenWidth;
    private int screeHeight;
    private Button button;
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;
    private int boxSpeed;
    private int orangeSpeed;
    private int pinkSpeed;
    private int blackSpeed;
    private boolean action_flg = false;
    private boolean start_flg = false;
    private boolean paa=false;
    private int score;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlayer sound;
    private boolean fff=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guuo);
        sound = new SoundPlayer(this);
        Score = (TextView) findViewById(R.id.score);
        Start = (TextView) findViewById(R.id.start);
        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);
        button=(Button)findViewById(R.id.button);
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screeHeight = size.y;
        screenWidth = size.x;
        boxSpeed = Math.round(screeHeight / 60);
        orangeSpeed = Math.round(screenWidth / 60);
        pinkSpeed = Math.round(screenWidth / 36);
        blackSpeed = Math.round(screenWidth / 45);

        button.setVisibility(View.INVISIBLE);
        orange.setX(-80);
        orange.setY(-80);
        black.setX(-80);
        black.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        Score.setText("Score : ");
    }

    public void changePos() {
        hitCheck();
        orangeX -= orangeSpeed;
        if (orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);


        blackX -= blackSpeed;
        if (blackX < 0) {
            blackX = screenWidth + 10;
            blackY = (int) Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);
        pinkX -= pinkSpeed;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);
        if (action_flg == true) {
            // Touching
            if(score<50)
                boxY -= boxSpeed/2;
            else if(score<200)
                boxY-=boxSpeed;
            else
                boxY-=boxSpeed;

        } else {
            // Releasing

            if(score<50)
                boxY += boxSpeed/2;
            else if(score<200)
                boxY+=boxSpeed;
            else
                boxY+=boxSpeed;}
        if (boxY < 0) boxY = 0;
        if (boxY > frameHeight - boxSize) boxY = frameHeight - boxSize;
        box.setY(boxY);
        Score.setText("Score : " + score);

    }

    public void hitCheck() {
        int orangeCenterX = orangeX + orange.getWidth() / 2;
        int orangeCenterY = orangeY + orange.getHeight() / 2;
        if (0 <= orangeCenterX && orangeCenterX <= boxSize && boxY <= orangeCenterY && orangeCenterY <= boxY + boxSize) {
            score += 10;
            orangeX = -10;
            sound.playHitSound();
        }
        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;
        if (0 <= pinkCenterX && pinkCenterX <= boxSize && boxY <= pinkCenterY && pinkCenterY <= boxY + boxSize) {
            score += 10;
            pinkX = -10;
            sound.playHitSound();
        }
        int blackCenterX = blackX + black.getWidth() / 2;
        int blackCenterY = blackY + black.getHeight() / 2;
        if (0 <= blackCenterX && blackCenterX <= boxSize && boxY <= blackCenterY && blackCenterY <= boxY + boxSize) {
            timer.cancel();
            timer = null;
            sound.playOverSound();
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("Score", score);
            startActivity(intent);
        }

    }

    public boolean onTouchEvent(MotionEvent me) {
        if (start_flg == false) {
            start_flg = true;
            button.setVisibility(View.VISIBLE);
            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();
            boxY = (int) box.getY();
            boxSize = box.getHeight();
            Start.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);
        }else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) action_flg=true;
            else if(me.getAction()==MotionEvent.ACTION_UP) action_flg=false;
        }


        return true;
    }
    public void pause(View view){
        if(paa==false)
        {
            paa=true;
            timer.cancel();
            timer=null;
            button.setText(">");
            fff=true;
        }
        else
        {button.setText("||");
            paa=false;
            timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);
            fff=true;
        }
    }
}