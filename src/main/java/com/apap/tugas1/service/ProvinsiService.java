package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.ProvinsiModel;

//ProvinsiService

public interface ProvinsiService {
	void addProvinsi(ProvinsiModel provinsi);
	Optional<ProvinsiModel> getProvinsiDetailById(Long id);
	ProvinsiModel getProvinsiDetailByName(String nama);
	List<ProvinsiModel> getAll();
	
}

