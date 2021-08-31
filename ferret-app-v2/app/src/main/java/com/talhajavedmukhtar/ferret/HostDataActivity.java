package com.talhajavedmukhtar.ferret;

import android.content.Intent;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.talhajavedmukhtar.ferret.Model.VulnDetailsData;
import com.talhajavedmukhtar.ferret.Util.Tags;
import com.talhajavedmukhtar.ferret.Util.VulnDetailsAdapter;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HostDataActivity extends AppCompatActivity {
    TextView vendor;
    TextView deviceName;
    TextView ipAddress;
    TextView vulnerable;
    TextView no_of_vulns;
    TextView vulndetailsheading;
    TextView open_ports;

    private String TAG = Tags.makeTag("HostDataActivity");
    private List<String> Threats = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostdata);
        vendor = findViewById(R.id.vendor);
        deviceName = findViewById(R.id.deviceName);
        ipAddress = findViewById(R.id.ipAddress);
        vulnerable = findViewById(R.id.vulnerable);
        no_of_vulns = findViewById(R.id.numvulnerable);
        vulndetailsheading = findViewById(R.id.vulndetailsheading);
        // open_ports = findViewById(R.id.open_ports);

//        Threats.add("Applications can behave unexpectedly. They can crash, exit or restart forcefully");
//        Threats.add("An attacker can read, change or delete files on a system");
//        Threats.add("System has degraded performance which results in slowed response or incomplete functionality");


//        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String receivedVendor = intent.getStringExtra("vendor");
        String receivedDeviceName = intent.getStringExtra("name");
        String receivedIpAddress = intent.getStringExtra("ip");
        Boolean receivedVulnerable = intent.getBooleanExtra("vulnerable", false);


        ArrayList<String> idents = new ArrayList<String>();
        ArrayList<String> descs = new ArrayList<String>();

        ArrayList<Integer> open_ports_list = new ArrayList<Integer>();
        open_ports_list = intent.getIntegerArrayListExtra("open_ports");
//        if (open_ports_list != null) {
//            open_ports.setText(open_ports_list.toString().trim());
//        } else {
//            open_ports.setText("None");
//        }


        int numofvulnerable = intent.getIntExtra("numofvulnerable", 0);


        Log.d(TAG, receivedVendor);
        Log.d("ReceivedDev", receivedDeviceName);
        Log.d(TAG, receivedIpAddress);
//        int receivedImage = intent.getIntExtra("image",0);
        vendor.setText(receivedVendor.trim());
        deviceName.setText(receivedDeviceName.trim());
        ipAddress.setText(receivedIpAddress.trim());

        if (receivedVulnerable == true) {
            vulnerable.setText("Yes");
            vulnerable.setTextColor(Color.parseColor("#f44336"));
            no_of_vulns.setTextColor(Color.parseColor("#f44336"));
            idents = intent.getStringArrayListExtra("idents");
            descs = intent.getStringArrayListExtra("descs");


        } else {
            vulnerable.setText("No");
            vulnerable.setTextColor(Color.parseColor("#4CAF50"));
            no_of_vulns.setTextColor(Color.parseColor("#4CAF50"));
            vulndetailsheading.setVisibility(View.GONE);


        }

        VulnDetailsData[] vulndetails = new VulnDetailsData[idents.size()];

        for (int i = 0; i < idents.size(); i++) {
            vulndetails[i] = new VulnDetailsData(idents.get(i), descs.get(i));
            Threats.add(descs.get(i));
        }
        Set<String> uniqueThreats = new HashSet<String>(Threats);
        List<String> targetList = new ArrayList<>(uniqueThreats);
        no_of_vulns.setText(Integer.toString(targetList.size()).trim());
// comment this block of code to disable vulnerability details
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vulndetails);
        VulnDetailsAdapter adapter = new VulnDetailsAdapter(targetList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "in hostdataactivity");

//        imageView.setImageResource(receivedImage);
        //enable back Button
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //getting back to listview
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

