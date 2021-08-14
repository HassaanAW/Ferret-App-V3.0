package com.talhajavedmukhtar.ferret.Util;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.talhajavedmukhtar.ferret.Model.DataItem;
import com.talhajavedmukhtar.ferret.MyApp;

import java.util.ArrayList;

public class DataHandler {
    private Context context;
    private String id;
    private String devMac = Utils.getMacAddr();
    private String MD5;

    public DataHandler(Context ctx){
        context = ctx;
        id = MyApp.id(context);
    }

    public void pushData(ArrayList<DataItem> data){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        MD5 = Utils.md5(devMac);
        Log.d("MD5hash", MD5);

        if(!MyApp.getNameCollectionConsent()){
            //remove device names
            for(DataItem dataItem: data){
                dataItem.getHost().setDeviceName(null);
            }
        }

        String timestamp = Long.toString(System.currentTimeMillis());
        databaseReference.child(MD5).child("data").child(timestamp).setValue(data);

        MyApp.setLastTimeStamp(timestamp);
    }

    public void pushPaymentData(String message, String lastTimeStamp){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(MD5).child("data").child(lastTimeStamp).child("paymentOption").setValue(message);
        MyApp.setPaymentDataCollected(true);
    }

    public void pushPortsData(int index, String ip, ArrayList<Integer> ports,String lastTimeStamp){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference node = databaseReference.child(MD5).child("data").child(lastTimeStamp).child("extendedPortsData").child(Integer.toString(index));
        node.child("IP").setValue(ip);
        node.child("OpenPorts").setValue(ports);
    }

    public void pushName(String name){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(MD5).push().setValue(name);
    }

    public void pushLabelData(String ip, String deviceType, String deviceModel, String lastTimeStamp){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference = databaseReference.child(MD5).child("data").child(lastTimeStamp).child("labelData").push();
        databaseReference.child("ip").setValue(ip);
        databaseReference.child("deviceType").setValue(deviceType);
        databaseReference.child("deviceModel").setValue(deviceModel);
    }

    public void pushPlaceData(String where, String details, String lastTimeStamp){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference = databaseReference.child(MD5).child("data").child(lastTimeStamp).child("place");
        databaseReference.child("where").setValue(where);
        databaseReference.child("details").setValue(details);

    }

    public void pushPublicIP(String ip, String lastTimeStamp){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(id).child(MD5).child(lastTimeStamp).child("publicIp").setValue(ip);
    }


}
