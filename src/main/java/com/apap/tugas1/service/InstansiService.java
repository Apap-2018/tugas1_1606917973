package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

//InstansiService

public interface InstansiService {
	List<InstansiModel> getAll();
	InstansiModel getInstansiDetailById(long id);
	List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi);
	void addInstansi(InstansiModel instansi);
	
}

