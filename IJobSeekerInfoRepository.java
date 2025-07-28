package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.krushit.entity.JobSeekerInfo;

public interface IJobSeekerInfoRepository extends JpaRepository<JobSeekerInfo, Integer> {
    @Query("select resumePath from JobSeekerInfo where  jsId=:id")
	public  String   getResumePathByJsId(Integer id);
    
    @Query("select photoPath from JobSeekerInfo where  jsId=:id")
	public  String   getPhotoPathByJsId(Integer id);
}
