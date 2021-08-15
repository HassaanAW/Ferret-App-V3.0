package com.talhajavedmukhtar.ferret;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Experience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
    }

    public void Previous(View v){
        Intent intent = new Intent(this, SmartDevice.class);
        startActivity(intent);
    }
}