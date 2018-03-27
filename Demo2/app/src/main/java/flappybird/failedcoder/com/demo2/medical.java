package flappybird.failedcoder.com.demo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class medical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.Bdisplay1) {
            Intent i = new Intent(medical.this, AIIMS.class);
            startActivity(i);
        } else if (v.getId() == R.id.Bdisplay2) {
            Intent i = new Intent(medical.this, AIIMS.class);
            startActivity(i);
        }
    }
}

