package com.krushit.service;

import java.util.List;

import com.krushit.entity.JobSeekerInfo;

public interface IJobSeekerMgmtService {
     public  String  saveJobSeeker(JobSeekerInfo  info);
     public  List<JobSeekerInfo>  showAllJobSeekers();
     
     public String  fetchResumePathById(Integer id);
     public String  fetchPhotoPathById(Integer id);
     
}
