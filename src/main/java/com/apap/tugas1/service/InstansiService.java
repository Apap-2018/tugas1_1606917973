package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

//InstansiService

public interface InstansiService {
	List<InstansiModel> getAll();
	Optional<InstansiModel> getInstansiDetailById(Long id);
	List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi);
	void addInstansi(InstansiModel instansi);
	
}

