package flappybird.failedcoder.com.demo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DATA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.Bdisplay1) {
            Intent i = new Intent(DATA.this, LL.class);
            startActivity(i);
        } else if (v.getId() == R.id.Bdisplay2) {
            Intent i = new Intent(DATA.this, ST.class);
            startActivity(i);
        } else if (v.getId() == R.id.Bdisplay3) {
            Intent i = new Intent(DATA.this, QU.class);
            startActivity(i);
        } else if (v.getId() == R.id.Bdisplay4) {
            Intent i = new Intent(DATA.this, TR.class);
            startActivity(i);
        } else if (v.getId() == R.id.Bdisplay6) {
            Intent i = new Intent(DATA.this, GR.class);
            startActivity(i);
        }
    }
}
