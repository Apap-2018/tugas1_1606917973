package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;

//InstansiService

public interface InstansiService {
	List<InstansiModel> getAll();
	InstansiModel getInstansiDetailById(long id);
	
}

