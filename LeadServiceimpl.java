package com.marketingapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketingapp.entities.Lead;
import com.marketingapp.repository.LeadsRepository;
@Service
public class LeadServiceimpl implements LeadService {

	@Autowired
	private LeadsRepository leadRepo;

		

	@Override
	public  List<Lead> listAllLeads() {
		 List<Lead> leads = leadRepo.findAll();
		return leads;
		
	}

	@Override
	public void saveOneLead(Lead l) {
		// TODO Auto-generated method stub
		leadRepo.save(l);
		
	}

	@Override
	public void deleteoneLead(long id) {
		leadRepo.deleteById(id);
		
	}

	@Override
	public Lead getById(long id) {
	Optional<Lead> findById = leadRepo.findById(id);
     Lead lead = findById.get();
     return lead;
     
	}

}
