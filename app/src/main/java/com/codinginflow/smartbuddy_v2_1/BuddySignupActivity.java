package com.codinginflow.smartbuddy_v2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BuddySignupActivity extends AppCompatActivity {

    private EditText Name, Password, Email,Reference,Subject;
    private TextView Junioroption,signup_signin_buddy;
    private Button Register;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String email, name, password, reference, course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_signup);

        Name = (EditText)findViewById(R.id.buddy_signup_name);
        Password = (EditText)findViewById(R.id.buddy_signup_password);
        Email = (EditText)findViewById(R.id.buddy_signup_email);
        Register = (Button)findViewById(R.id.buddy_signup_button);
        Junioroption=(TextView) findViewById(R.id.signup_junior_option);
        signup_signin_buddy=(TextView) findViewById(R.id.signup_signin_buddy);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = Email.getText().toString().trim();
                    String user_password = Password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                sendBuddyData();
                                firebaseAuth.signOut();
                                Toast.makeText(BuddySignupActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(BuddySignupActivity.this, LoginSignupActivity.class));
                            }else{
                                Toast.makeText(BuddySignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


        Junioroption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJuniorSignupActivity();
            }
        });

        signup_signin_buddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginsignup();
            }
        });


    }

    // signup activity function
    public void  openJuniorSignupActivity()
    {
        Intent intent=new Intent(BuddySignupActivity.this,SignupActivity.class);//JuniorSigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void  openLoginsignup()
    {
        Intent intent=new Intent(BuddySignupActivity.this,LoginSignupActivity.class);
        startActivity(intent);
    }

    private Boolean validate(){
        Boolean result = false;
        name = Name.getText().toString();
        password = Password.getText().toString();
        email = Email.getText().toString();
        reference = Reference.getText().toString();
        course = Subject.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || reference.isEmpty() || course.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }


    private void sendBuddyData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        BuddyProfile Profile = new BuddyProfile(name, email,course,reference);
        myRef.setValue(Profile);
    }



}
