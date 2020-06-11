package com.codinginflow.smartbuddy_v2_1;

import androidx.annotation.NonNull;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class JuniorSigninActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private TextView Register;
    private TextView buddy_signin;
    private Button login;
    private int counter = 3;
    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior_signin);
        // set variables to respective ones in xml
        Email = (EditText) findViewById(R.id.junior_signin_email);
        Password = (EditText) findViewById(R.id.junior_signin_password);
        login = (Button) findViewById(R.id.junior_signin_button);
        Info = (TextView) findViewById(R.id.junior_signin_info);
        Register=(TextView) findViewById(R.id.junior_signin_register);
        buddy_signin=(TextView) findViewById(R.id.junior_signin_buddy_signin);


        Info.setText("No of attempts remaining: 3");
        // get current instance of user
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Intent intent = getIntent();
        Boolean isJunior = intent.getBooleanExtra("isJunior",false);
        if(isJunior==false){

        }

        if (user != null && isJunior==false) {

            finish();

            startActivity(new Intent(JuniorSigninActivity.this, JuniorCoreActivity.class));
        }
        //onclick when user tries to login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });


        //onclick for navigation to signup activity
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });
        //onclick for navigation to buddySignin activity
        buddy_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuddySigninActivity();
            }
        });


    }

    // user validation function
    private void validate(String userName, String userPassword) {

        // progressDialog.setMessage("You can subscribe to my channel until you are verified!");
        //progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                   // progressDialog.dismiss();
                    Toast.makeText(JuniorSigninActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                } else {
                    Toast.makeText(JuniorSigninActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No of attempts remaining: " + counter);
                    //progressDialog.dismiss();
                    if (counter == 0) {
                        login.setEnabled(false);
                    }
                }
            }
        });


    }


    // signup activity function
    public void openSignupActivity()
    {
        Intent intent=new Intent(JuniorSigninActivity.this,SignupActivity.class);//JuniorSigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }
    //buddysignin activity function
    public void openBuddySigninActivity()
    {
        Intent intent=new Intent(this, BuddySigninActivity.class);
        startActivity(intent);
    }
    // validating email to proceed
    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent(JuniorSigninActivity.this, JuniorCoreActivity.class));

        //   if(emailflag){
//          finish();
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        }else{
//            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
    }
}
