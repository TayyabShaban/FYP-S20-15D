package com.codinginflow.smartbuddy_v2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnvironmentActivity extends AppCompatActivity {

    Button onlinebuton,physicalbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);

        physicalbutton=findViewById(R.id.physicalButton);
        onlinebuton=findViewById(R.id.onlineButton);

        physicalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // function to open intent
                openPlaceSelectActivity();
            }
        });
        onlinebuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnlineActitvity();
            }
        });
    }
    public void openPlaceSelectActivity(){

    }
    public void openOnlineActitvity(){

    }
}
