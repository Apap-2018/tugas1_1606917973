package com.apap.tugas1.repository;

import com.apap.tugas1.model.PegawaiModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//FlightDb

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, String> {
	PegawaiModel findByNip(String nip);
	
}
