package com.apap.tugas1.service;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//ProvinsiServiceImpl

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {

	@Autowired
	private ProvinsiDb provinsiDb;
	
	@Override
	public void addProvinsi(ProvinsiModel provinsi) {
		provinsiDb.save(provinsi);
	}
	
	@Override
	public Optional<ProvinsiModel> getProvinsiDetailById(Long id) {
		return provinsiDb.findById(id);
	}
	
	@Override
	public List<ProvinsiModel> getAll() {
		return provinsiDb.findAll();
	}
	
	@Override
	public ProvinsiModel getProvinsiDetailByName(String nama) {
		return provinsiDb.findByNama(nama).get(0);
	}
	
	
}
