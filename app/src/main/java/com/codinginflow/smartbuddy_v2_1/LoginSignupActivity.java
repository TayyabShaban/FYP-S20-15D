package com.codinginflow.smartbuddy_v2_1;

// 1st interact of user
// login r sighn up

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSignupActivity extends AppCompatActivity {

    Button juniorsignin,buddysignin;
    TextView helpview,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsignup);

        juniorsignin=findViewById(R.id.juniorSigninButton);
        buddysignin=findViewById(R.id.buddySigninButton);
        signup=findViewById(R.id.signupButton);
        helpview=findViewById(R.id.helpIcon);

        //onclicklistener to sign in as junior
        juniorsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // function to open intent
                openJuniorSigninActivity();
            }
        });
        // onclicklistener to signin as buddy
        buddysignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuddySigninActivity();
            }

        });
        // onclicklistener for sign up activity
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });
        // onclicklistener for help activity
        helpview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpActivity();
            }
        });

    }
   public  void openJuniorSigninActivity(){
        Intent prevIntent = getIntent();
        Boolean isJunior =prevIntent.getBooleanExtra("isJunior",false);
       Intent intent=new Intent(LoginSignupActivity.this, JuniorSigninActivity.class);

       intent.putExtra("isJunior",isJunior);
       startActivity(intent);
    }
    public void openBuddySigninActivity(){
        Intent intent=new Intent(LoginSignupActivity.this, BuddySigninActivity.class);
        startActivity(intent);
    }
    public void openSignupActivity()
    {
        Intent intent=new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
    public void openHelpActivity()
    {
        Intent intent=new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
}
