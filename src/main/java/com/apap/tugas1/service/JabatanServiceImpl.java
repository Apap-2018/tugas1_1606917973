package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//JabatanServiceImpl

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired
	private JabatanDb jabatanDb;
	
	/**
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	public PilotModel getPilotDetailById(long id) {
		return pilotDb.findById(id);
	}
	*/
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}
	
	/**
	@Override
	public PilotModel deletePilot(long id) {
		PilotModel deleted = pilotDb.findById(id);
		pilotDb.delete(pilotDb.findById(id));
		return deleted;
	}

	@Override
	public PilotModel updatePilot(long id, String name, String flyHour) {
		pilotDb.findById(id).setName(name);
		pilotDb.findById(id).setFlyHour(flyHour);
		PilotModel updated = pilotDb.findById(id);
		return updated;
	}
	*/
	
}
