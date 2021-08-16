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

public class Experience extends AppCompatActivity {

    private RadioButton Q14_yes, Q14_no, Q14_notSure, Q16_yes, Q16_no, Q16_sometimes, Q18_yes, Q18_no, Q18_slightly, Q19_yes, Q19_no, Q19_slightly;
    private RadioButton Q20_yes, Q20_no, Q20_slightly, Q21_yes, Q21_no, Q21_slightly, Q22_yes, Q22_no, Q22_slightly, Q23_1, Q23_2, Q23_3, Q23_4, Q23_5;
    private RadioButton Q24_yes, Q24_no, Q24_slightly, Q25_yes, Q25_no, Q25_maybe, Q25_no_clue, Q26_yes, Q26_no, Q26_maybe, Q26_no_clue, Q30_yes, Q30_no, Q30_maybe, Q31_yes, Q31_no, Q31_little;
    private CheckBox Q15_func, Q15_aud, Q15_visual, Q15_info, Q15_notSure, Q17_man, Q17_adv, Q17_govt, Q17_ISP, Q17_rand;
    private CheckBox Smartphone, Router, SmartTV, SecuritySystem, Health, Sensor, SmartWatch, SmartLight, HomeAssist, Others;
    private CheckBox Q28_personal, Q28_monitor, Q28_safety, Q28_attention, Q29_none, Q29_pass, Q29_soft, Q29_scan;

    private String MD5, devMac;
    private String Get_14, Get_16, Get_18, Get_19, Get_20, Get_21, Get_22, Get_23, Get_24, Get_25, Get_26, Get_30, Get_31;
    private String get_city, get_gender, get_age, get_region, get_ISP, get_MAC,get_degree, get_industry, get_security, get_course, get_sec_course, get_language, get_priority;
    private List<String> Get_SmartDevices = new ArrayList<String>();
    private List<String> Get_15 = new ArrayList<String>();
    private List<String> Get_17 = new ArrayList<String>();
    private List<String> Get_27 = new ArrayList<String>();
    private List<String> Get_28 = new ArrayList<String>();
    private List<String> Get_29 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        devMac = Utils.getMacAddr();
        MD5 = Utils.md5(devMac);
        // Get from intent
        get_city = getIntent().getStringExtra("City");
        get_gender = getIntent().getStringExtra("Gender");
        get_age = getIntent().getStringExtra("Age");
        get_region = getIntent().getStringExtra("Region");
        get_ISP = getIntent().getStringExtra("ISP");
        get_MAC = getIntent().getStringExtra("MAC");

        get_degree = getIntent().getStringExtra("Degree");
        get_industry = getIntent().getStringExtra("Industry");
        get_security = getIntent().getStringExtra("Field");
        get_course = getIntent().getStringExtra("Courses");
        get_sec_course = getIntent().getStringExtra("SecurityCourse");
        get_language = getIntent().getStringExtra("Language");

        Get_SmartDevices = getIntent().getStringArrayListExtra("SmartDevices");
        get_priority = getIntent().getStringExtra("FirstPriority");

        // -----------------------------------------------------------------------------------------

        // Get Q14
        Q14_yes = (RadioButton) findViewById(R.id.Q14_yes);
        Q14_no = (RadioButton) findViewById(R.id.Q14_no);
        Q14_notSure = (RadioButton) findViewById(R.id.Q14_NotSure);

        // Get Q15
        Q15_func = (CheckBox) findViewById(R.id.checkBox);
        Q15_aud = (CheckBox) findViewById(R.id.checkBox2);
        Q15_visual = (CheckBox) findViewById(R.id.checkBox3);
        Q15_info = (CheckBox) findViewById(R.id.checkBox14);
        Q15_notSure = (CheckBox) findViewById(R.id.checkBox15);

        // Get Q16
        Q16_yes = (RadioButton) findViewById(R.id.Q16_yes);
        Q16_no = (RadioButton) findViewById(R.id.Q16_no);
        Q16_sometimes = (RadioButton) findViewById(R.id.Q16_Sometimes);

        // Get Q17
        Q17_man = (CheckBox) findViewById(R.id.checkBox16);
        Q17_adv = (CheckBox) findViewById(R.id.checkBox17);
        Q17_govt = (CheckBox) findViewById(R.id.checkBox18);
        Q17_ISP = (CheckBox) findViewById(R.id.checkBox19);
        Q17_rand = (CheckBox) findViewById(R.id.checkBox20);

        // Get Q18
        Q18_yes = (RadioButton) findViewById(R.id.Q18_yes);
        Q18_no = (RadioButton) findViewById(R.id.Q18_no);
        Q18_slightly = (RadioButton) findViewById(R.id.Q18_Slightly);

        // Get Q19
        Q19_yes = (RadioButton) findViewById(R.id.radioButton14);
        Q19_no = (RadioButton) findViewById(R.id.radioButton15);
        Q19_slightly = (RadioButton) findViewById(R.id.radioButton16);

        // Get Q20
        Q20_yes = (RadioButton) findViewById(R.id.radioButton17);
        Q20_no = (RadioButton) findViewById(R.id.radioButton18);
        Q20_slightly = (RadioButton) findViewById(R.id.radioButton19);

        // Get Q21
        Q21_yes = (RadioButton) findViewById(R.id.radioButton20);
        Q21_no = (RadioButton) findViewById(R.id.radioButton21);
        Q21_slightly = (RadioButton) findViewById(R.id.radioButton22);

        // Get Q22
        Q22_yes = (RadioButton) findViewById(R.id.radioButton23);
        Q22_no = (RadioButton) findViewById(R.id.radioButton24);
        Q22_slightly = (RadioButton) findViewById(R.id.radioButton25);

        // Get Q23
        Q23_1 = (RadioButton) findViewById(R.id.radioButton26);
        Q23_2 = (RadioButton) findViewById(R.id.radioButton27);
        Q23_3 = (RadioButton) findViewById(R.id.radioButton28);
        Q23_4 = (RadioButton) findViewById(R.id.radioButton29);
        Q23_5 = (RadioButton) findViewById(R.id.radioButton30);

        // Get Q24
        Q24_yes = (RadioButton) findViewById(R.id.radioButton31);
        Q24_no = (RadioButton) findViewById(R.id.radioButton32);
        Q24_slightly = (RadioButton) findViewById(R.id.radioButton33);

        // Get Q25
        Q25_yes = (RadioButton) findViewById(R.id.radioButton34);
        Q25_no = (RadioButton) findViewById(R.id.radioButton35);
        Q25_maybe = (RadioButton) findViewById(R.id.radioButton36);
        Q25_no_clue = (RadioButton) findViewById(R.id.radioButton37);

        // Get 26
        Q26_yes = (RadioButton) findViewById(R.id.radioButton38);
        Q26_no = (RadioButton) findViewById(R.id.radioButton39);
        Q26_maybe = (RadioButton) findViewById(R.id.radioButton40);
        Q26_no_clue = (RadioButton) findViewById(R.id.radioButton41);

        // Get Q27- which smart device most worried about
        Smartphone = (CheckBox) findViewById(R.id.checkBox4);
        Router = (CheckBox) findViewById(R.id.checkBox6);
        SmartTV = (CheckBox) findViewById(R.id.checkBox5);
        SecuritySystem = (CheckBox) findViewById(R.id.checkBox7);
        Health = (CheckBox) findViewById(R.id.checkBox8);
        Sensor = (CheckBox) findViewById(R.id.checkBox9);
        SmartWatch = (CheckBox) findViewById(R.id.checkBox10);
        SmartLight = (CheckBox) findViewById(R.id.checkBox11);
        HomeAssist = (CheckBox) findViewById(R.id.checkBox12);
        Others = (CheckBox) findViewById(R.id.checkBox13);

        // Get 28
        Q28_personal = (CheckBox) findViewById(R.id.checkBox21);
        Q28_monitor = (CheckBox) findViewById(R.id.checkBox22);
        Q28_safety = (CheckBox) findViewById(R.id.checkBox23);
        Q28_attention = (CheckBox) findViewById(R.id.checkBox24);

        // Get 29
        Q29_none = (CheckBox) findViewById(R.id.checkBox25);
        Q29_pass = (CheckBox) findViewById(R.id.checkBox26);
        Q29_soft = (CheckBox) findViewById(R.id.checkBox27);
        Q29_scan = (CheckBox) findViewById(R.id.checkBox28);

        // Get 30
        Q30_yes = (RadioButton) findViewById(R.id.Q30_yes);
        Q30_no = (RadioButton) findViewById(R.id.Q30_no);
        Q30_maybe = (RadioButton) findViewById(R.id.Q30_Maybe);

        // Get 31
        Q31_yes = (RadioButton) findViewById(R.id.Q31_yes);
        Q31_little = (RadioButton) findViewById(R.id.Q31_Maybe);
        Q31_no = (RadioButton) findViewById(R.id.Q31_No);
    }

    public void onClick(View v){

        // Get_14
        if(Q14_yes.isChecked()){
            Get_14 = Q14_yes.getText().toString();
        }
        else if(Q14_no.isChecked()){
            Get_14 = Q14_no.getText().toString();
        }
        else if(Q14_notSure.isChecked()){
            Get_14 = Q14_notSure.getText().toString();
        }

        // Get_15
        if(Q15_func.isChecked()){
          Get_15.add(Q15_func.getText().toString());
        }
        if(Q15_aud.isChecked()){
            Get_15.add(Q15_aud.getText().toString());
        }
        if(Q15_visual.isChecked()){
            Get_15.add(Q15_visual.getText().toString());
        }
        if(Q15_info.isChecked()){
            Get_15.add(Q15_info.getText().toString());
        }
        if(Q15_notSure.isChecked()){
            Get_15.add(Q15_notSure.getText().toString());
        }

        // Get_16
        if(Q16_yes.isChecked()){
            Get_16 = Q16_yes.getText().toString();
        }
        else if(Q16_no.isChecked()){
            Get_16 = Q16_no.getText().toString();
        }
        else if(Q16_sometimes.isChecked()){
            Get_16 = Q16_sometimes.getText().toString();
        }

        // Get_17
        if(Q17_man.isChecked()){
            Get_17.add(Q17_man.getText().toString());
        }
        if(Q17_adv.isChecked()){
            Get_17.add(Q17_adv.getText().toString());
        }
        if(Q17_govt.isChecked()){
            Get_17.add(Q17_govt.getText().toString());
        }
        if(Q17_ISP.isChecked()){
            Get_17.add(Q17_ISP.getText().toString());
        }
        if(Q17_rand.isChecked()){
            Get_17.add(Q17_rand.getText().toString());
        }

        // Get_18
        if(Q18_yes.isChecked()){
            Get_18 = Q18_yes.getText().toString();
        }
        else if(Q18_no.isChecked()){
            Get_18 = Q18_no.getText().toString();
        }
        else if(Q18_slightly.isChecked()){
            Get_18 = Q18_slightly.getText().toString();
        }

        // Get_19
        if(Q19_yes.isChecked()){
            Get_19 = Q19_yes.getText().toString();
        }
        else if(Q19_no.isChecked()){
            Get_19 = Q19_no.getText().toString();
        }
        else if(Q19_slightly.isChecked()){
            Get_19 = Q19_slightly.getText().toString();
        }

        // Get_20
        if(Q20_yes.isChecked()){
            Get_20 = Q20_yes.getText().toString();
        }
        else if(Q20_no.isChecked()){
            Get_20 = Q20_no.getText().toString();
        }
        else if(Q20_slightly.isChecked()){
            Get_20 = Q20_slightly.getText().toString();
        }

        // Get_21
        if(Q21_yes.isChecked()){
            Get_21 = Q21_yes.getText().toString();
        }
        else if(Q21_no.isChecked()){
            Get_21 = Q21_no.getText().toString();
        }
        else if(Q21_slightly.isChecked()){
            Get_21 = Q21_slightly.getText().toString();
        }

        // Get_22
        if(Q22_yes.isChecked()){
            Get_22 = Q22_yes.getText().toString();
        }
        else if(Q22_no.isChecked()){
            Get_22 = Q22_no.getText().toString();
        }
        else if(Q22_slightly.isChecked()){
            Get_22 = Q22_slightly.getText().toString();
        }

        // Get_23
        if(Q23_1.isChecked()){
            Get_23 = Q23_1.getText().toString();
        }
        else if(Q23_2.isChecked()){
            Get_23 = Q23_2.getText().toString();
        }
        else if(Q23_3.isChecked()){
            Get_23 = Q23_3.getText().toString();
        }
        else if(Q23_4.isChecked()){
            Get_23 = Q23_4.getText().toString();
        }
        else if(Q23_5.isChecked()){
            Get_23 = Q23_5.getText().toString();
        }

        // Get_24
        if(Q24_yes.isChecked()){
            Get_24 = Q24_yes.getText().toString();
        }
        else if(Q24_no.isChecked()){
            Get_24 = Q24_no.getText().toString();
        }
        else if(Q24_slightly.isChecked()){
            Get_24 = Q24_slightly.getText().toString();
        }

        // Get_25
        if(Q25_yes.isChecked()){
            Get_25 = Q25_yes.getText().toString();
        }
        else if(Q25_no.isChecked()){
            Get_25 = Q25_no.getText().toString();
        }
        else if(Q25_maybe.isChecked()){
            Get_25 = Q25_maybe.getText().toString();
        }
        else if(Q25_no_clue.isChecked()){
            Get_25 = Q25_no_clue.getText().toString();
        }

        // Get_26
        if(Q26_yes.isChecked()){
            Get_26 = Q26_yes.getText().toString();
        }
        else if(Q26_no.isChecked()){
            Get_26 = Q26_no.getText().toString();
        }
        else if(Q26_maybe.isChecked()){
            Get_26 = Q26_maybe.getText().toString();
        }
        else if(Q26_no_clue.isChecked()){
            Get_26 = Q26_no_clue.getText().toString();
        }

        if(Smartphone.isChecked()){
            Get_27.add(Smartphone.getText().toString());
        }
        if(Router.isChecked()){
            Get_27.add(Router.getText().toString());
        }
        if(SmartTV.isChecked()){
            Get_27.add(SmartTV.getText().toString());
        }
        if(SecuritySystem.isChecked()){
            Get_27.add(SecuritySystem.getText().toString());
        }
        if(Health.isChecked()){
            Get_27.add(Health.getText().toString());
        }
        if(Sensor.isChecked()){
            Get_27.add(Sensor.getText().toString());
        }
        if(SmartWatch.isChecked()){
            Get_27.add(SmartWatch.getText().toString());
        }
        if(SmartLight.isChecked()){
            Get_27.add(SmartLight.getText().toString());
        }
        if(HomeAssist.isChecked()){
            Get_27.add(HomeAssist.getText().toString());
        }
        if(Others.isChecked()){
            Get_27.add(Others.getText().toString());
        }

        // Get Q28
        if(Q28_personal.isChecked()){
            Get_28.add(Q28_personal.getText().toString());
        }
        if(Q28_monitor.isChecked()){
            Get_28.add(Q28_monitor.getText().toString());
        }
        if(Q28_safety.isChecked()){
            Get_28.add(Q28_safety.getText().toString());
        }
        if(Q28_attention.isChecked()){
            Get_28.add(Q28_attention.getText().toString());
        }

        // Get Q29
        if(Q29_none.isChecked()){
            Get_29.add(Q29_none.getText().toString());
        }
        if(Q29_pass.isChecked()){
            Get_29.add(Q29_pass.getText().toString());
        }
        if(Q29_soft.isChecked()){
            Get_29.add(Q29_soft.getText().toString());
        }
        if(Q29_scan.isChecked()){
            Get_29.add(Q29_scan.getText().toString());
        }

        // Get Q30
        if(Q30_yes.isChecked()){
            Get_30 = Q30_yes.getText().toString();
        }
        else if(Q30_no.isChecked()){
            Get_30 = Q30_no.getText().toString();
        }
        else if(Q30_maybe.isChecked()){
            Get_30 = Q30_maybe.getText().toString();
        }

        // Get Q31
        if(Q31_yes.isChecked()){
            Get_31 = Q31_yes.getText().toString();
        }
        else if(Q31_no.isChecked()){
            Get_31 = Q31_no.getText().toString();
        }
        else if(Q31_little.isChecked()){
            Get_31 = Q31_little.getText().toString();
        }

        if (TextUtils.isEmpty(Get_14) || TextUtils.isEmpty(Get_16) || TextUtils.isEmpty(Get_18) || TextUtils.isEmpty(Get_19) || TextUtils.isEmpty(Get_20) || TextUtils.isEmpty(Get_21) || TextUtils.isEmpty(Get_22) || TextUtils.isEmpty(Get_23)
        || TextUtils.isEmpty(Get_24) || TextUtils.isEmpty(Get_25) || TextUtils.isEmpty(Get_26) || TextUtils.isEmpty(Get_30) || TextUtils.isEmpty(Get_31)
        || (Get_15.size() == 0) || (Get_17.size() == 0) || (Get_27.size() == 0) || (Get_28.size() == 0) || (Get_29.size() == 0)){
            Toast.makeText(Experience.this, "Please fill missing fields", Toast.LENGTH_SHORT).show();
        }
        else{
            HashMap<String, Object> map = new HashMap<>();
            map.put("Gender", get_gender);
            map.put("Age", get_age);
            map.put("City", get_city);
            map.put("Region", get_region);
            map.put("ISP", get_ISP);

            map.put("CS Degree", get_degree);
            map.put("Tech Industry", get_industry);
            map.put("Security Field", get_security);
            map.put("Taken Courses", get_course);
            map.put("Security Courses", get_sec_course);
            map.put("Language", get_language);

            map.put("Smart Devices", Get_SmartDevices);
            map.put("Priority while Buying", get_priority);

            map.put("Data Collected", Get_14);
            map.put("Sort of Data", Get_15);
            map.put("ConcernedRecording", Get_16);
            map.put("CanAccess", Get_17);
            map.put("DeviceMan", Get_18);
            map.put("OnlineAdv", Get_19);
            map.put("Govt", Get_20);
            map.put("ISP_Agency", Get_21);
            map.put("RandomPerson", Get_22);
            map.put("SecurityCam", Get_23);
            map.put("Lifestyle", Get_24);
            map.put("ObtainData", Get_25);
            map.put("Control", Get_26);
            map.put("MostConcerned", Get_27);
            map.put("Why_Concerned", Get_28);
            map.put("StepsTaken", Get_29);
            map.put("UseApps", Get_30);
            map.put("WouldWorry", Get_31);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(MD5).child("Initial Survey").updateChildren(map);
            Toast.makeText(Experience.this, "Response Submitted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, First_Survey_Comp.class);
            startActivity(intent);
        }

    }

    public void Previous(View v){
        Intent intent = new Intent(this, SmartDevice.class);
        startActivity(intent);
    }
}