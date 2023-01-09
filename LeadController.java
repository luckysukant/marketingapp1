package com.marketingapp.controller;

import java.util.List;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import com.marketingapp.DTO.LeadData;
import com.marketingapp.entities.Lead;
import com.marketingapp.services.LeadService;
import com.marketingapp.services.LeadServiceimpl;
import com.marketingapp.util.EmailService;
import com.marketingapp.util.EmailServiceImpl;




@Controller
public class LeadController {
	
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private LeadService leadService;

	//Handler methods
	@RequestMapping("/viewCreateLeadPage")
	public String viewCreateLeadPage() {
		return "CreateLead";
	}
	@RequestMapping(value = "/saveLead", method = RequestMethod.POST)
     public String savelead (@ModelAttribute("lead") Lead l ,  ModelMap model) {
		leadService.saveOneLead(l);
		emailService.sendSimpleMail(l.getEmail(), "Test", "testemail");
		
		model.addAttribute("msg", "Lead is saved!!");
	 return "CreateLead";
	}
//	@RequestMapping(value = "/savelead", method = RequestMethod.POST)
//			
//  public String savelead (@RequestParam("name") String name,@RequestParam("lastname") String lname,@RequestParam("email") String email,@RequestParam("mobile") String mobile)  {
//		Lead lead= new Lead();
//		lead.setFirstname(name);
//		lead.setLastname(lname);
//		lead.setEmail(email);
//		lead.setMobile(mobile);
//		
//		leadservice.SaveoneLead(lead);
//	return "createlead";
//	}
//	@RequestMapping(value = "/savelead", method = RequestMethod.POST)
//	  public String savelead (LeadData data) {	
//		
//		Lead l =new Lead();
//		l.setFirstname(data.getFirstname());
//		l.setLastname(data.getLastname());
//		l.setEmail(data.getEmail());
//		l.setMobile(data.getMobile());
//		
//		LeadService.SaveoneLead(l);
//	return "createlead";
//	}
@RequestMapping("/listAllLeads")
	public String listAllLeads(ModelMap model) {
	 List<Lead> leads = leadService.listAllLeads();
	 model.addAttribute("leads", leads);
	 return "list_leads";
}
@RequestMapping("/deleteLead")
public String deleteLead(@RequestParam("id") long id, ModelMap model) {
	leadService.deleteoneLead(id);
	 List<Lead> leads = leadService.listAllLeads();
	 model.addAttribute("leads", leads);
	
	 return "list_leads";
	
}
@RequestMapping("/updateLead")
public String updateLead(@RequestParam("id") long id, Model model) {
	Lead lead = leadService.getById(id);
	model.addAttribute("lead", lead);
	return"update_lead";
	
}
@RequestMapping(value= "/updateOneLead" , method = RequestMethod.POST)
public String updateLead(@ModelAttribute("lead") Lead l , ModelMap model) {
	leadService.saveOneLead(l);
	List<Lead> leads = leadService.listAllLeads();
	model.addAttribute("leads",leads);
	return"list_leads";
}
}

	


