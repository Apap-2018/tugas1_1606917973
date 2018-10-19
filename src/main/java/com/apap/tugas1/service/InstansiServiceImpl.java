package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//InstansiServiceImpl

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
	
	@Autowired
	private InstansiDb instansiDb;
	
	@Override
	public InstansiModel getInstansiDetailById(long id) {
		return instansiDb.findById(id);
	}
	
	@Override
	public List<InstansiModel> getAll() {
		List<InstansiModel> semuaInstansi = instansiDb.findAll();
		return semuaInstansi;
	}
	
	@Override
	public void addInstansi(InstansiModel instansi) {
		instansiDb.save(instansi);
	}
	
	@Override
	public List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi) {
		return instansiDb.findByProvinsi(provinsi);
	}
	
	
}
