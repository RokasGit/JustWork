package com.example.justwork.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justwork.R;
import com.example.justwork.model.Job;
import com.example.justwork.model.JobApplication;
import com.example.justwork.model.User;
import com.example.justwork.repository.JobRepository;
import com.example.justwork.repository.ListRepository;

import java.util.List;

public class JobApplicantAdapter extends RecyclerView.Adapter<JobApplicantAdapter.ViewHolder>{

    private List<JobApplication> jobApplications;
    private onClickListener onClickListener;
    private JobRepository jobRepository;
    private ListRepository listRepository;

    public JobApplicantAdapter(List<JobApplication> jobApplications){
        this.jobApplications = jobApplications;
        this.jobRepository = JobRepository.getInstance();
        this.listRepository = ListRepository.getInstance();
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
        JobApplication application = jobApplications.get(position);
        Job tempJob = jobRepository.getJobById(application.getJobId());
        holder.jobTitle.setText(tempJob.getTitle());
        String fullName = jobApplications.get(position).getFirstName() + " " + jobApplications.get(position).getLastName();
        holder.fullName.setText(fullName);
        String salary = tempJob.getSalary() +"dkk/h";
        holder.salary.setText(salary);
        holder.address.setText(tempJob.getLocation());
        holder.postTime.setText(tempJob.getJobType());

    }

    @Override
    public int getItemCount() {
        return jobApplications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView jobTitle;
        TextView fullName;
        TextView salary;
        TextView address;
        TextView postTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.search_applicant_image);
            jobTitle = itemView.findViewById(R.id.job_applicant_job_position);
            fullName = itemView.findViewById(R.id.search_applicant_fullName);
            salary = itemView.findViewById(R.id.search_applicant_salary);
            address = itemView.findViewById(R.id.search_applicant_companyAddress);
            postTime = itemView.findViewById(R.id.search_applicant_postTime);
            itemView.setOnClickListener(v->{
                onClickListener.onClick(jobApplications.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface onClickListener{
        void onClick(JobApplication jobApplication);
    }
}
