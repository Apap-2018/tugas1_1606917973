package com.apap.tugas1.repository;

import com.apap.tugas1.model.ProvinsiModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ProvinsiDb

@Repository
public interface ProvinsiDb extends JpaRepository<ProvinsiModel, Long> {
	ProvinsiModel findById(long id);
	ProvinsiModel findByNama(String nama);
	
}
