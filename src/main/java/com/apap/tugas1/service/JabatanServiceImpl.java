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
	*/
	
	@Override
	public JabatanModel getJabatanDetailById(long id) {
		return jabatanDb.findById(id);
	}
	
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}
	
	@Override
	public List<JabatanModel> getAll() {
		List<JabatanModel> semuaJabatan = jabatanDb.findAll();
		return semuaJabatan;
	}
	
	@Override
	public JabatanModel deleteJabatan(long id) {
		JabatanModel deleted = jabatanDb.findById(id);
		jabatanDb.delete(jabatanDb.findById(id));
		return deleted;
	}
	
	@Override
	public JabatanModel updateJabatan(long id, String nama, String deskripsi, double gajiPokok) {
		jabatanDb.findById(id).setNama(nama);
		jabatanDb.findById(id).setDeskripsi(deskripsi);
		jabatanDb.findById(id).setGajiPokok(gajiPokok);
		JabatanModel updated = jabatanDb.findById(id);
		return updated;
	}
	
	
}
