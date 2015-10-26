package com.great.cms.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.great.cms.db.entity.Submission;
import com.great.cms.db.entity.Task;
import com.great.cms.service.ProjectGroupSubmitService;
import com.great.cms.service.SubmissionService;

@Controller
//@SessionAttributes("organization")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private ProjectGroupSubmitService projGrpSubService;
	
	private JSONArray jsonArray;
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET,value="/ajaxsubmissions")
	public @ResponseBody String getSubmissionList(Model model)
	{
		System.out.println("Submission Controller -> getSubmissionList");
		List<Submission> submissionList = null;
		
		submissionList =  projGrpSubService.findSubmissionListByProjectGroupId(2);
		System.out.println("sub list = " + submissionList);
		
		model.addAttribute("submissions",submissionList);
		
		jsonArray = new JSONArray();
		
		if(submissionList==null)
		 System.out.println("Submission Controller -> getSubmissionList : LIST IS NULL");
	
	    
	    for(Submission s: submissionList)
	    {
	    	JSONArray jsonObj = new JSONArray();
	    	jsonObj.add(s.getSubmissionId().toString());
	    	jsonObj.add(s.getSubmissionTime());
	    	jsonObj.add(s.getCommentTeacher());
	    	jsonObj.add("Dummy Url");
	    	
	    	/*if( s.getTaskTypeId().getTaskTypeId()==1)
	    	jsonObj.add("Project");
	    	else
		    jsonObj.add("Assignment");*/
	    	
	    	System.out.println("sub id : " + s.getSubmissionId());
	    	
	    	jsonArray.add(jsonObj);  
	}
	    
	    JSONObject parameters = new JSONObject();

    	parameters.put("draw", 1);

    	parameters.put("recordsTotal", 1 );
    	
    	parameters.put("recordsFiltered", 1 );
    	
    	parameters.put("data", jsonArray);
    	
    	String submissionJson = parameters.toJSONString();
    	
    	//System.out.print("DLSJDHSLKJDH:  "+taskJson);
		return submissionJson;
	
}
	
	@RequestMapping(value="/addsubmission",method=RequestMethod.POST)
    public @ResponseBody String addSubmission(Submission submission)
    {
		System.out.println("addsubmission method, submission teacher comment : " + submission.getCommentTeacher());
		projGrpSubService.addProjectGroupSubmit(submission, 1);
		return "{ \"success\" : true }";
    }
	
}
