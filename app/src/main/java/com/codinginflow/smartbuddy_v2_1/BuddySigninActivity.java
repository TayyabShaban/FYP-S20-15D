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


public class BuddySigninActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private TextView Register;
    private TextView junior_signin;
    private Button login;
    private int counter = 3;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_signin);

        Email = (EditText) findViewById(R.id.buddy_signin_email);
        Password = (EditText) findViewById(R.id.buddy_signin_password);
        login = (Button) findViewById(R.id.buddy_signin_button);
        Info = (TextView) findViewById(R.id.buddy_signin_info);
        Register=(TextView) findViewById(R.id.buddy_signin_register);
        junior_signin=(TextView) findViewById(R.id.buddy_signin_junior_signin);

        Info.setText("No of attempts remaining: 3");
        // get current instance of user
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(BuddySigninActivity.this, BuddyCoreActivity.class));
        }
        //onclick when user tries to login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });
        //onclick for navigation to juniorSignin activity
        junior_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJuniorSigninActivity();
            }
        });

    }

    private void validate(String userName, String userPassword) {

        // progressDialog.setMessage("You can subscribe to my channel until you are verified!");
        //progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // progressDialog.dismiss();
                    Toast.makeText(BuddySigninActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                } else {
                    Toast.makeText(BuddySigninActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
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

    public void openSignupActivity()
    {
        Intent intent=new Intent(BuddySigninActivity.this,SignupActivity.class);//JuniorSigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }
    //juniorsignin activity function
    public void openJuniorSigninActivity()
    {
        Intent intent=new Intent(this, JuniorSigninActivity.class);
        startActivity(intent);
    }
    // validating email to proceed
    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        startActivity(new Intent(BuddySigninActivity.this, BuddyCoreActivity.class));

    }
}
