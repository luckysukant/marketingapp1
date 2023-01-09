package com.marketingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketingapp.entities.Lead;
import com.marketingapp.repository.LeadsRepository;

@RestController
@RequestMapping("/api/leads")
public class LeadRestController {

	
	@Autowired
	private LeadsRepository LeadRepo;
	@GetMapping
	public List<Lead>getAllLeads(){
		List<Lead> leads = LeadRepo.findAll();
		return leads;
	}
	@PostMapping
	public void saveLead(@RequestBody Lead lead) {
		LeadRepo.save(lead);
	}
	@PutMapping
	public void updateLead(@RequestBody Lead lead) {
		LeadRepo.save(lead);
	}
	//http://localhost:8083/api/leads/delete/9
	@DeleteMapping("/delete/{id}")
	public void deleteLead(@PathVariable("id")long id) {
		LeadRepo.deleteById(id);
	}
	@RequestMapping("lead/{id}")
	public Lead getOneLead(@PathVariable("id") long id) {
		Optional<Lead> findById = LeadRepo.findById(id);
		Lead lead = findById.get();
		return lead;
	}

}