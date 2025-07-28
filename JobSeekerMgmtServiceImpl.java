package com.krushit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushit.entity.JobSeekerInfo;
import com.krushit.repository.IJobSeekerInfoRepository;

@Service("jsService")
public class JobSeekerMgmtServiceImpl implements IJobSeekerMgmtService {
	@Autowired
	private  IJobSeekerInfoRepository  jsInfoRepo;

	@Override
	public String saveJobSeeker(JobSeekerInfo info) {
		Integer idVal=jsInfoRepo.save(info).getJsId();
		return "JobSeeker is registered with the id value::"+idVal;
	}

	@Override
	public List<JobSeekerInfo> showAllJobSeekers() {
		//invoke the service method
		return jsInfoRepo.findAll();
	}

	@Override
	public String fetchResumePathById(Integer id) {
		//use repo
		return  jsInfoRepo.getResumePathByJsId(id);
	}

	@Override
	public String fetchPhotoPathById(Integer id) {
		return  jsInfoRepo.getPhotoPathByJsId(id);
	}

}
