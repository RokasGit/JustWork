package com.example.justwork.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justwork.R;
import com.example.justwork.model.JobApplication;
import com.example.justwork.model.User;

import java.util.ArrayList;
import java.util.List;

public class JobApplicantAdapter extends RecyclerView.Adapter<JobApplicantAdapter.ViewHolder>{

    private List<JobApplication> jobApplications;
    private onClickListener onClickListener;

    public JobApplicantAdapter(List<JobApplication> jobApplications){
        this.jobApplications = jobApplications;
    }

    public void setJobApplications(List<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

    public void setOnClickListener(onClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_job_applicant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // holder.userImage.setImageDrawable(users.get(position).getPicture()); Picture problems
        holder.companyName.setText(jobApplications.get(position).getCompanyName());
        holder.jobTitle.setText(jobApplications.get(position).getTitle());
        holder.salary.setText(jobApplications.get(position).getSalary()+"");
        holder.address.setText(jobApplications.get(position).getLocation());
        holder.postTime.setText(jobApplications.get(position).getJobType());

    }

    @Override
    public int getItemCount() {
        return jobApplications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView companyName;
        TextView jobTitle;
        TextView salary;
        TextView address;
        TextView postTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.search_applicant_image);
            companyName = itemView.findViewById(R.id.search_applicant_companyName);
            jobTitle = itemView.findViewById(R.id.search_applicant_jobTitle);
            salary = itemView.findViewById(R.id.search_applicant_salary);
            address = itemView.findViewById(R.id.search_applicant_companyAddress);
            postTime = itemView.findViewById(R.id.search_applicant_postTime);
            jobTitle.setOnClickListener(v->{
                onClickListener.onClick(jobApplications.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface onClickListener{
        void onClick(JobApplication jobApplication);
    }
}