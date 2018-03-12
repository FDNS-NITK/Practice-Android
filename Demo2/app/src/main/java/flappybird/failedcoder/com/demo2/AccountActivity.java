 package flappybird.failedcoder.com.demo2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

 public class AccountActivity extends AppCompatActivity {

     private FirebaseAuth mAuth;
    private Button mLogout;
    private FirebaseAuth.AuthStateListener mAuthListeneer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
        mAuthListeneer = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(AccountActivity.this,LoginActivity.class));
                }
            }
        };
        mLogout = findViewById(R.id.logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });
    }

     @Override
     protected void onStart() {
         super.onStart();
         mAuth.addAuthStateListener(mAuthListeneer);
     }
 }
