package com.marketingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketingapp.entities.Lead;

public interface LeadsRepository extends JpaRepository<Lead, Long> {

}
