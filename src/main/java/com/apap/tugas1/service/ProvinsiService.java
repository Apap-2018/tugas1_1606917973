package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.ProvinsiModel;

//ProvinsiService

public interface ProvinsiService {
	void addProvinsi(ProvinsiModel provinsi);
	ProvinsiModel getProvinsiDetailById(long id);
	ProvinsiModel getProvinsiDetailByName(String nama);
	List<ProvinsiModel> getAll();
	
}

