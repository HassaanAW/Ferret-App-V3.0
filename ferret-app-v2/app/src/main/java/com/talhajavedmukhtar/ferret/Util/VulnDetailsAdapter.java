package com.talhajavedmukhtar.ferret.Util;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.talhajavedmukhtar.ferret.Model.VulnDetailsData;
import com.talhajavedmukhtar.ferret.R;

import java.util.List;


public class VulnDetailsAdapter  extends RecyclerView.Adapter<VulnDetailsAdapter.ViewHolder>{
    private List<String> vulndetailsdata;
    // RecyclerView recyclerView;
    public VulnDetailsAdapter(List<String> vulndetailsdata) {
        this.vulndetailsdata = vulndetailsdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View listItem = layoutInflater.inflate(R.layout.vulndetails_rowlayout, parent, false);
        View listItem = layoutInflater.inflate(R.layout.threat_cards, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Previous Version
//        final VulnDetailsData vulndetail = vulndetailsdata[position];
//        holder.ident.setText(vulndetailsdata[position].getidents());
//        holder.desc.setText(vulndetailsdata[position].getidentsdescs());

        // New Version
        String vulndetail = vulndetailsdata.get(position).trim();
        Log.d("SysMsg", vulndetail);
        holder.Threat_Desc.setText(vulndetail);
        holder.Threat_Num.setText("Weakness " + (position+1));
        if(vulndetail.equals("Consequences are unknown. They may range from unpredictable behavior of the system to sensitive information being revealed to attackers")){
            holder.Threat_Image.setImageResource(R.drawable.unknown);
        }
        else if(vulndetail.equals("Sensitive information can be visible to unauthorized users such as attackers or third party organizations")){
            holder.Threat_Image.setImageResource(R.drawable.personal);
        }
        else if(vulndetail.equals("An attacker can gain access to a system and control it remotely")){
            holder.Threat_Image.setImageResource(R.drawable.access);
        }
        else if(vulndetail.equals("Applications can behave unexpectedly. They can crash, exit or restart forcefully")){
            holder.Threat_Image.setImageResource(R.drawable.unpredictable);
        }
        else if(vulndetail.equals("Degraded performance resulting in slow response time or incomplete functionality of system")){
            holder.Threat_Image.setImageResource(R.drawable.degraded);
        }
        else if(vulndetail.equals("Applications can behave unexpectedly. They can crash, exit or restart forcefully")){
            holder.Threat_Image.setImageResource(R.drawable.unpredictable);
        }
        else if(vulndetail.equals("An attacker can hide activites from the user. There will be no way of determining an attack")){
            holder.Threat_Image.setImageResource(R.drawable.hide);
        }
        else if(vulndetail.equals("An attacker can read, change or delete files on a system")){
            holder.Threat_Image.setImageResource(R.drawable.files);
        }
        else{
            Log.d("Picture", "Not found");
        }

//        holder.imageView.setImageResource(listdata[position].getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return vulndetailsdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Previous Version
        // public TextView ident;
        // public TextView desc;

        // New version
        public TextView Threat_Num;
        public TextView Threat_Desc;
        public ImageView Threat_Image;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            // New Version
            this.Threat_Image = (ImageView) itemView.findViewById(R.id.imageView12);
            this.Threat_Num = (TextView) itemView.findViewById(R.id.threat_num);
            this.Threat_Desc = (TextView) itemView.findViewById(R.id.textView58);

//            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            this.ident = (TextView) itemView.findViewById(R.id.vulnident);
//            this.desc = (TextView) itemView.findViewById(R.id.vulndesc);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativelayout);
        }
    }


}


