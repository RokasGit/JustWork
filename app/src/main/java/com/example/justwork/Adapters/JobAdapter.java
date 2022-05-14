package com.example.justwork.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justwork.R;
import com.example.justwork.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private List<Job> jobs;
    private onClickListener onClickListener;

    public JobAdapter(List<Job> jobs){
        this.jobs = jobs;
    }

    public void setOnClickListener(onClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void setJobs(List<Job> jobs){
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.companyLogo.setImageDrawable(jobs.get(position)); for image logo but we dont have that in class
        holder.companyName.setText(jobs.get(position).getCompanyName());
        holder.jobTitle.setText(jobs.get(position).getTitle());
        holder.companyName.setText(jobs.get(position).getCompanyName());
        holder.jobDuration.setText(jobs.get(position).getJobType());
        holder.jobAddress.setText(jobs.get(position).getLocation());
        String salary = jobs.get(position).getSalary() + "dkk/h";
        holder.jobWage.setText(salary);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView companyLogo;
        TextView companyName;
        TextView jobTitle;
        TextView jobDuration;
        TextView jobAddress;
        TextView jobWage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.search_logoId);
            companyName = itemView.findViewById(R.id.search_companyName);
            jobTitle = itemView.findViewById(R.id.search_jobTitle);
            jobAddress = itemView.findViewById(R.id.search_companyAddress);
            jobDuration = itemView.findViewById(R.id.search_postTime);
            jobWage = itemView.findViewById(R.id.search_salary);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(jobs.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface onClickListener{
        void onClick(Job job);
    }

}
