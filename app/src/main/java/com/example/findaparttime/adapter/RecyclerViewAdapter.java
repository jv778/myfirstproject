package com.example.findaparttime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findaparttime.R;
import com.example.findaparttime.data.DataFile;
import com.example.findaparttime.onClickInterface;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DataFile> allJobs;
    onClickInterface onClickinterface;
    public RecyclerViewAdapter(Context context, ArrayList<DataFile> allJobs,onClickInterface onClickinterface) {
        this.context = context;
        this.allJobs = allJobs;
        this.onClickinterface = onClickinterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataFile dataFile = allJobs.get(position);

        holder.job_tit.setText(dataFile.getFname1());
        holder.job_provider.setText(dataFile.getEmail_id());
        holder.location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickinterface.setClick(dataFile.getEmail_id(),dataFile.getFname1(),dataFile.getLatitude_val(),dataFile.getLongitude_val());
            }
        });
        holder.viewmoretext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickinterface.AllInformation(dataFile.getEmail_id(),dataFile.getFname1(),dataFile.getEmail1(),dataFile.getPass1(),dataFile.getPhn1());
            }
        });
    }

    @Override
    public int getItemCount() {
        return allJobs.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageButton location1;
        public TextView job_tit;
        public TextView job_provider;
        public TextView viewmoretext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            location1 = itemView.findViewById(R.id.location_button);
            job_tit = itemView.findViewById(R.id.Job_title);
            job_provider = itemView.findViewById(R.id.Provider_info);
            viewmoretext = itemView.findViewById(R.id.view_more);
        }
    }
}
