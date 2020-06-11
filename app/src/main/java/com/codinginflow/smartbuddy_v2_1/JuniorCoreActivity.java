package com.codinginflow.smartbuddy_v2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class JuniorCoreActivity extends AppCompatActivity {
    private TextView logout,welcome;
    private FirebaseAuth firebaseAuth;
    private Spinner spinner1,spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior_core);
        logout = (TextView) findViewById(R.id.JuniorCore_logout);
        welcome = (TextView) findViewById(R.id.Welcome_junior);

        firebaseAuth = FirebaseAuth.getInstance();
       spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        addItemsOnSpinner1();
        addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection1();
        addListenerOnSpinnerItemSelection2();

    welcome.setText("Hello Junior");
       // Info.setText("Log Out");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent intent=new Intent(JuniorCoreActivity.this, LoginSignupActivity.class);
                startActivity(intent);
            }
        });
    }


    public void addItemsOnSpinner1() {

       //spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("CS449");
        list.add("MT543");
        list.add("EE732");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    public void addItemsOnSpinner2() {

      //  spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Ali Akbar");
        list.add("Asad");
        list.add("Tayyab");
        list.add("Manal");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection1() {

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnSpinnerItemSelection2() {
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
