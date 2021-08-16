package com.talhajavedmukhtar.ferret;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FinalSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_survey2);
    }

    public void onClick(View v){
        Intent intent = new Intent(getApplicationContext(), ThankYouActivity.class);
        startActivity(intent);
    }
}