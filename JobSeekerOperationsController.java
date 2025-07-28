package com.krushit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.krushit.entity.JobSeekerInfo;
import com.krushit.model.JobSeekerDetails;
import com.krushit.service.IJobSeekerMgmtService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class JobSeekerOperationsController {
	@Autowired
	private IJobSeekerMgmtService jsService;
	@Autowired
	private Environment env;

	@GetMapping("/")
	public String showHome() {
		return "welcome";
	}

	@GetMapping("/js_register")
	public String showFormPage(@ModelAttribute("js") JobSeekerDetails details) {
		// return LVN
		return "js_register_form";
	}

	@PostMapping("/js_register")
	public String registerJobSeeker(Map<String, Object> map, @ModelAttribute("js") JobSeekerDetails details)
			throws Exception {
		// get folder location of file uploading from the application properties file
		String storeLocation = env.getRequiredProperty("upload.store");
		// create StoreLocation folder if it is not already there
		File storeLocationFolder = new File(storeLocation);
		if (!storeLocationFolder.exists())
			storeLocationFolder.mkdir(); // creates the directory

		// get MultiPartFile Objects representing the uploaded files
		MultipartFile file1 = details.getResume();
		MultipartFile file2 = details.getPhoto();
		// create inputStream from the MultiPartFile objects
		InputStream file1Is = file1.getInputStream();
		InputStream file2Is = file2.getInputStream();
		// get orginal file names from the list of uploaded files
		String fileName1 = file1.getOriginalFilename();
		String fileName2 = file2.getOriginalFilename();
		// create OutputStreams having destination file names
		OutputStream file1Os = new FileOutputStream(storeLocationFolder.getAbsolutePath() + "/" + fileName1);
		OutputStream file2Os = new FileOutputStream(storeLocationFolder.getAbsolutePath() + "/" + fileName2);
		// copy the content of uploaded files to Dest files
		IOUtils.copy(file1Is, file1Os);
		IOUtils.copy(file2Is, file2Os);
		// close streams
		file1Is.close();
		file2Is.close();
		file1Os.close();
		file2Os.close();

		// create Entity Class object having content of Model class obj
		JobSeekerInfo info = new JobSeekerInfo();
		info.setJsName(details.getJsName());
		info.setJsQlfy(details.getJsQlfy());
		info.setJsAddrs(details.getJsAddrs());

		String file1Path = (storeLocationFolder.getAbsolutePath() + "/" + fileName1).replace("/", "\\");
		String file2Path = (storeLocationFolder.getAbsolutePath() + "/" + fileName2).replace("/", "\\");

		info.setPhotoPath(file1Path);
		info.setResumePath(file2Path);

		// use service class
		String resultMsg = jsService.saveJobSeeker(info);
		// keep reuslts in model attributes
		map.put("resultMsg", resultMsg);
		map.put("fileName1", fileName1);
		map.put("fileName2", fileName2);

		// return LVN
		return "show_result";
	}

	@GetMapping("/js_report")
	public String showAllJobSeeker(Map<String, Object> map) {
		// invoke the service method
		List<JobSeekerInfo> list = jsService.showAllJobSeekers();
		// add to model attribute
		map.put("jsInfo", list);
		// return LVN
		return "show_report";

	}

	@GetMapping("/download")
	public String downloadFile(@RequestParam("id") Integer id, @RequestParam("type") String type,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// get the path of file to be downloaded from the DB s/w
		String filePath = null;
		if (type.equalsIgnoreCase("resume"))
			filePath = jsService.fetchResumePathById(id);
		else
			filePath = jsService.fetchPhotoPathById(id);
		// create java.io.File class obj representing the file to be downloaded
		File file = new File(filePath);
		// get the file length and make it as response content length
		res.setContentLengthLong(file.length());
		// get ServletContext object
		ServletContext sc = req.getServletContext();
		// get MIME of the file and make it as the response content type
		String mimeType = sc.getMimeType(filePath);
		mimeType = (mimeType == null) ? "application/octet-stream" : mimeType;
		res.setContentType(mimeType);
		// create InputStream obj pointing to the file to db downloaded
		InputStream is = new FileInputStream(file);
		// create OutStream obj pointing to the response object
		OutputStream os = res.getOutputStream();
		// give instruction to browser to display the recieved content as downloadable
		// file
		res.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
		// copy file content to response object
		IOUtils.copy(is, os);

		// close the streams
		is.close();
		os.close();

		return null; // required if handler method wants to send the response/output to browser
						// directly
	}

}
