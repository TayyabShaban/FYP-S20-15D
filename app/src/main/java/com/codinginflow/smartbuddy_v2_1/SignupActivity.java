package com.codinginflow.smartbuddy_v2_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SignupActivity extends AppCompatActivity {

    private EditText Name, Password, Email;
    private TextView Buddyoption, signup_signin;
    private Button Register;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String email, name, password;
    Boolean isJunior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name = (EditText)findViewById(R.id.signup_name);
        Password = (EditText)findViewById(R.id.signup_password);
        Email = (EditText)findViewById(R.id.signup_email);
        Register = (Button)findViewById(R.id.signup_button);
        Buddyoption=(TextView) findViewById(R.id.signup_buddy_option);
        signup_signin=(TextView) findViewById(R.id.signup_signin);

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
                                //sendJuniorData();
                                firebaseAuth.signOut();
                                Toast.makeText(SignupActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                //finish();

                                Intent intent=new Intent(SignupActivity.this,LoginSignupActivity.class);
                                isJunior = true;
                                intent.putExtra("isJunior",isJunior);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        Buddyoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuddySignupActivity();
            }
        });

        signup_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginsignup();
            }
        });

    }
    // signup activity function
    public void  openBuddySignupActivity()
    {
        Intent intent=new Intent(SignupActivity.this,BuddySignupActivity.class);//JuniorSigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void  openLoginsignup()
    {
        Intent intent=new Intent(SignupActivity.this,LoginSignupActivity.class);
        startActivity(intent);
    }

    private Boolean validate(){
        Boolean result = false;
        name = Name.getText().toString();
        password = Password.getText().toString();
        email = Email.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendJuniorData();
                        Toast.makeText(SignupActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(SignupActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendJuniorData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
       // StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
       // UploadTask uploadTask = imageReference.putFile(imagePath);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(RegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                Toast.makeText(RegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
//            }
//        });
        isJunior=true;
        JuniorProfile Profile = new JuniorProfile(name, email,isJunior);
        myRef.setValue(Profile);
    }

}
