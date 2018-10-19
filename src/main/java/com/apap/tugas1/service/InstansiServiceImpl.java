package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.repository.InstansiDb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//JabatanServiceImpl

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
	@Autowired
	private InstansiDb instansiDb;
	
	/**
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	*/
	
	@Override
	public InstansiModel getInstansiDetailById(long id) {
		return instansiDb.findById(id);
	}
	
	@Override
	public List<InstansiModel> getAll() {
		List<InstansiModel> semuaInstansi = instansiDb.findAll();
		return semuaInstansi;
	}
	
	
}
