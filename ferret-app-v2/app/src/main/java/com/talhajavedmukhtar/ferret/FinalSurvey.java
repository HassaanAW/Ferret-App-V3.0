package com.talhajavedmukhtar.ferret;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.talhajavedmukhtar.ferret.Util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FinalSurvey extends AppCompatActivity {
    private RadioButton Q32_yes, Q32_no, Q32_little, Q33_yes, Q33_no, Q33_maybe, Q35_yes, Q35_no, Q35_slightly, Q36_yes, Q36_no, Q36_slightly, Q37_yes, Q37_no, Q37_slightly, Q38_yes, Q38_no, Q38_slightly, Q39_yes, Q39_no, Q39_slightly;
    private RadioButton Q40_yes, Q40_no, Q40_maybe, Q40_noClue, Q41_yes, Q41_no, Q41_maybe, Q41_noClue;
    private CheckBox Smartphone, Router, SmartTV, SecuritySystem, Health, Sensor, SmartWatch, SmartLight, HomeAssist, Others;
    private CheckBox Q43_personal, Q43_routine, Q43_safety, Q43_attention, Q43_scan;
    private RadioButton Q44_yes, Q44_no, Q44_maybe, Q45_yes, Q45_no, Q45_maybe, Q46_yes, Q46_no, Q46_maybe;

    private String Get_32, Get_33, Get_35, Get_36, Get_37, Get_38, Get_39, Get_40, Get_41, Get_44, Get_45, Get_46;
    private List<String> Get_42 = new ArrayList<String>();
    private List<String> Get_43 = new ArrayList<String>();
    private String MD5, devMac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_survey2);

        devMac = Utils.getMacAddr();
        MD5 = Utils.md5(devMac);

        // Get Q32
        Q32_yes = (RadioButton) findViewById(R.id.Q32_yes);
        Q32_no = (RadioButton) findViewById(R.id.Q32_No);
        Q32_little = (RadioButton) findViewById(R.id.Q32_Maybe);

        // Get Q33
        Q33_yes = (RadioButton) findViewById(R.id.Q33_yes);
        Q33_no = (RadioButton) findViewById(R.id.Q33_no);
        Q33_maybe = (RadioButton) findViewById(R.id.Q33_Maybe);

        // Get Q35
        Q35_yes = (RadioButton) findViewById(R.id.Q35_yes);
        Q35_no = (RadioButton) findViewById(R.id.Q35_no);
        Q35_slightly = (RadioButton) findViewById(R.id.Q35_Slightly);

        // Get Q36
        Q36_yes = (RadioButton) findViewById(R.id.Q36_yes);
        Q36_no = (RadioButton) findViewById(R.id.Q36_no);
        Q36_slightly = (RadioButton) findViewById(R.id.Q36_Slightly);

        // Get Q37
        Q37_yes = (RadioButton) findViewById(R.id.Q37_yes);
        Q37_no = (RadioButton) findViewById(R.id.Q37_no);
        Q37_slightly = (RadioButton) findViewById(R.id.Q37_Slightly);

        // Get Q38
        Q38_yes = (RadioButton) findViewById(R.id.Q38_yes);
        Q38_no = (RadioButton) findViewById(R.id.Q38_no);
        Q38_slightly = (RadioButton) findViewById(R.id.Q38_Slightly);

        // Get Q39
        Q39_yes = (RadioButton) findViewById(R.id.Q39_yes);
        Q39_no = (RadioButton) findViewById(R.id.Q39_no);
        Q39_slightly = (RadioButton) findViewById(R.id.Q39_Slightly);

        // Get Q40
        Q40_yes = (RadioButton) findViewById(R.id.Q40_yes);
        Q40_no = (RadioButton) findViewById(R.id.Q40_no);
        Q40_maybe = (RadioButton) findViewById(R.id.Q40_Maybe);
        Q40_noClue = (RadioButton) findViewById(R.id.Q40_NoClue);

        // Get Q41
        Q41_yes = (RadioButton) findViewById(R.id.Q41_yes);
        Q41_no = (RadioButton) findViewById(R.id.Q41_no);
        Q41_maybe = (RadioButton) findViewById(R.id.Q41_Maybe);
        Q41_noClue = (RadioButton) findViewById(R.id.Q41_NoClue);

        // Get Q42- which smart device most worried about
        Smartphone = (CheckBox) findViewById(R.id.checkBox4);
        Router = (CheckBox) findViewById(R.id.checkBox5);
        SmartTV = (CheckBox) findViewById(R.id.checkBox6);
        SecuritySystem = (CheckBox) findViewById(R.id.checkBox7);
        Health = (CheckBox) findViewById(R.id.checkBox8);
        Sensor = (CheckBox) findViewById(R.id.checkBox9);
        SmartWatch = (CheckBox) findViewById(R.id.checkBox10);
        SmartLight = (CheckBox) findViewById(R.id.checkBox11);
        HomeAssist = (CheckBox) findViewById(R.id.checkBox12);
        Others = (CheckBox) findViewById(R.id.checkBox13);

        // Get Q43
        Q43_personal = (CheckBox) findViewById(R.id.checkBox21);
        Q43_routine = (CheckBox) findViewById(R.id.checkBox22);
        Q43_safety = (CheckBox) findViewById(R.id.checkBox23);
        Q43_attention = (CheckBox) findViewById(R.id.checkBox24);
        Q43_scan = (CheckBox) findViewById(R.id.checkBox25);

        // Get Q44
        Q44_yes = (RadioButton) findViewById(R.id.Q44_yes);
        Q44_no = (RadioButton) findViewById(R.id.Q44_no);
        Q44_maybe = (RadioButton) findViewById(R.id.Q44_Maybe);

        // Get Q45
        Q45_yes = (RadioButton) findViewById(R.id.Q45_yes);
        Q45_no = (RadioButton) findViewById(R.id.Q45_no);
        Q45_maybe = (RadioButton) findViewById(R.id.Q45_maybe);

        // Get Q46
        Q46_yes = (RadioButton) findViewById(R.id.Q46_yes);
        Q46_no = (RadioButton) findViewById(R.id.Q46_no);
        Q46_maybe = (RadioButton) findViewById(R.id.Q46_Maybe);
    }

    public void onClick(View v){

        // Get_32
        if(Q32_yes.isChecked()){
            Get_32 = Q32_yes.getText().toString();
        }
        else if(Q32_no.isChecked()){
            Get_32 = Q32_no.getText().toString();
        }
        else if(Q32_little.isChecked()){
            Get_32 = Q32_little.getText().toString();
        }

        // Get_33
        if(Q33_yes.isChecked()){
            Get_33 = Q33_yes.getText().toString();
        }
        else if(Q33_no.isChecked()){
            Get_33 = Q33_no.getText().toString();
        }
        else if(Q33_maybe.isChecked()){
            Get_33 = Q33_maybe.getText().toString();
        }

        // Get_35
        if(Q35_yes.isChecked()){
            Get_35 = Q35_yes.getText().toString();
        }
        else if(Q35_no.isChecked()){
            Get_35 = Q35_no.getText().toString();
        }
        else if(Q35_slightly.isChecked()){
            Get_35 = Q35_slightly.getText().toString();
        }

        // Get_36
        if(Q36_yes.isChecked()){
            Get_36 = Q36_yes.getText().toString();
        }
        else if(Q36_no.isChecked()){
            Get_36 = Q36_no.getText().toString();
        }
        else if(Q36_slightly.isChecked()){
            Get_36 = Q36_slightly.getText().toString();
        }

        // Get_37
        if(Q37_yes.isChecked()){
            Get_37 = Q37_yes.getText().toString();
        }
        else if(Q37_no.isChecked()){
            Get_37 = Q37_no.getText().toString();
        }
        else if(Q37_slightly.isChecked()){
            Get_37 = Q37_slightly.getText().toString();
        }

        // Get_38
        if(Q38_yes.isChecked()){
            Get_38 = Q38_yes.getText().toString();
        }
        else if(Q38_no.isChecked()){
            Get_38 = Q38_no.getText().toString();
        }
        else if(Q38_slightly.isChecked()){
            Get_38 = Q38_slightly.getText().toString();
        }

        // Get_39
        if(Q39_yes.isChecked()){
            Get_39 = Q39_yes.getText().toString();
        }
        else if(Q39_no.isChecked()){
            Get_39 = Q39_no.getText().toString();
        }
        else if(Q39_slightly.isChecked()){
            Get_39 = Q39_slightly.getText().toString();
        }

        // Get_40
        if(Q40_yes.isChecked()){
            Get_40 = Q40_yes.getText().toString();
        }
        else if(Q40_no.isChecked()){
            Get_40 = Q40_no.getText().toString();
        }
        else if(Q40_maybe.isChecked()){
            Get_40 = Q40_maybe.getText().toString();
        }
        else if(Q40_noClue.isChecked()){
            Get_40 = Q40_noClue.getText().toString();
        }

        // Get_41
        if(Q41_yes.isChecked()){
            Get_41 = Q41_yes.getText().toString();
        }
        else if(Q41_no.isChecked()){
            Get_41 = Q41_no.getText().toString();
        }
        else if(Q41_maybe.isChecked()){
            Get_41 = Q41_maybe.getText().toString();
        }
        else if(Q41_noClue.isChecked()){
            Get_41 = Q41_noClue.getText().toString();
        }

        // Get 42
        if(Smartphone.isChecked()){
            Get_42.add(Smartphone.getText().toString());
        }
        if(Router.isChecked()){
            Get_42.add(Router.getText().toString());
        }
        if(SmartTV.isChecked()){
            Get_42.add(SmartTV.getText().toString());
        }
        if(SecuritySystem.isChecked()){
            Get_42.add(SecuritySystem.getText().toString());
        }
        if(Health.isChecked()){
            Get_42.add(Health.getText().toString());
        }
        if(Sensor.isChecked()){
            Get_42.add(Sensor.getText().toString());
        }
        if(SmartWatch.isChecked()){
            Get_42.add(SmartWatch.getText().toString());
        }
        if(SmartLight.isChecked()){
            Get_42.add(SmartLight.getText().toString());
        }
        if(HomeAssist.isChecked()){
            Get_42.add(HomeAssist.getText().toString());
        }
        if(Others.isChecked()){
            Get_42.add(Others.getText().toString());
        }

        // Get 43
        if(Q43_personal.isChecked()){
            Get_43.add(Q43_personal.getText().toString());
        }
        if(Q43_routine.isChecked()){
            Get_43.add(Q43_routine.getText().toString());
        }
        if(Q43_safety.isChecked()){
            Get_43.add(Q43_safety.getText().toString());
        }
        if(Q43_attention.isChecked()){
            Get_43.add(Q43_attention.getText().toString());
        }
        if(Q43_scan.isChecked()){
            Get_43.add(Q43_scan.getText().toString());
        }

        // Get44
        if(Q44_yes.isChecked()){
            Get_44 = Q44_yes.getText().toString();
        }
        else if(Q44_no.isChecked()){
            Get_44 = Q44_no.getText().toString();
        }
        else if(Q44_maybe.isChecked()){
            Get_44 = Q44_maybe.getText().toString();
        }

        // Get45
        if(Q45_yes.isChecked()){
            Get_45 = Q45_yes.getText().toString();
        }
        else if(Q45_no.isChecked()){
            Get_45 = Q45_no.getText().toString();
        }
        else if(Q45_maybe.isChecked()){
            Get_45 = Q45_maybe.getText().toString();
        }

        // Get46
        if(Q46_yes.isChecked()){
            Get_46 = Q46_yes.getText().toString();
        }
        else if(Q46_no.isChecked()){
            Get_46 = Q46_no.getText().toString();
        }
        else if(Q46_maybe.isChecked()){
            Get_46 = Q46_maybe.getText().toString();
        }

        if (TextUtils.isEmpty(Get_32) || TextUtils.isEmpty(Get_33) || TextUtils.isEmpty(Get_35) || TextUtils.isEmpty(Get_36) || TextUtils.isEmpty(Get_37) || TextUtils.isEmpty(Get_38) || TextUtils.isEmpty(Get_39)
                || TextUtils.isEmpty(Get_40) || TextUtils.isEmpty(Get_41) || TextUtils.isEmpty(Get_44) || TextUtils.isEmpty(Get_45) || TextUtils.isEmpty(Get_46)
                || (Get_42.size() == 0) || (Get_43.size() == 0)){
            Toast.makeText(FinalSurvey.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
        }
        else{
            HashMap<String, Object> map = new HashMap<>();
            map.put("PostScan_Concern", Get_32);
            map.put("PostScan_ShareResults", Get_33);
            map.put("PostScan_Manufacturer", Get_35);
            map.put("PostScan_Advertiser", Get_36);
            map.put("PostScan_Govt", Get_37);
            map.put("PostScan_ISP", Get_38);
            map.put("PostScan_Random", Get_39);
            map.put("PostScan_Obtain", Get_40);
            map.put("PostScan_Control", Get_41);
            map.put("PostScan_DevicesConcerned", Get_42);
            map.put("PostScan_WhyConcerned", Get_43);
            map.put("PostScan_BrandChoice", Get_44);
            map.put("PostScan_Steps", Get_45);
            map.put("PostScan_UseApp", Get_46);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(MD5).updateChildren(map);
            Toast.makeText(FinalSurvey.this, "Response Submitted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ThankYouActivity.class);
            startActivity(intent);
        }

    }
}