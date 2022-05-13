package com.example.justwork.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justwork.R;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.example.justwork.repository.JobRepository;

import java.util.List;

public class JobApplicationAdapter extends RecyclerView.Adapter<JobApplicationAdapter.ViewHolder> {

    private List<JobApplication> jobApplications;
    private JobAdapter.onClickListener onClickListener;
    private JobRepository jobRepository;

    public void setOnClickListener(JobAdapter.onClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.jobRepository = JobRepository.getInstance();
    }

    public JobApplicationAdapter(List<JobApplication> jobApplications){
        this.jobApplications = jobApplications;
    }

    public void setJobApplications(List<JobApplication> jobApplications){
        this.jobApplications = jobApplications;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_job_application_item, parent, false);
        return new JobApplicationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.companyLogo.setImageDrawable(jobs.get(position)); for image logo but we dont have that in class
        Job tempJob = jobRepository.getCompanyJobById(jobApplications.get(position).getJobId());

        holder.jobTitle.setText(tempJob.getTitle());
        holder.companyName.setText(tempJob.getCompanyName());
        holder.jobApplicationStatus.setText(tempJob.getJobType());
        holder.jobType.setText(tempJob.getLocation());
        holder.jobWage.setText(tempJob.getSalary() + "");
    }

    @Override
    public int getItemCount() {
        return jobApplications.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView companyLogo;
        TextView companyName;
        TextView jobTitle;
        TextView jobApplicationStatus;
        TextView jobType;
        TextView jobWage;
        Button optionButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogo = itemView.findViewById(R.id.job_application_logoId);
            companyName = itemView.findViewById(R.id.job_application_companyName);
            jobTitle = itemView.findViewById(R.id.job_application_job_title);
            jobApplicationStatus = itemView.findViewById(R.id.job_application_status);
            jobType = itemView.findViewById(R.id.job_application_job_type);
            jobWage = itemView.findViewById(R.id.job_application_salary);
            optionButton = itemView.findViewById(R.id.job_application_options_btn);
//            optionButton.setOnClickListener(v->{
//                onClickListener.onClick();
//            }); need to open some other view (Job info or company info?)
            itemView.setOnClickListener(v -> {
                //onClickListener.onClick(jobApplications.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface onClickListener{
        void onClick(Job job);
    }
}
