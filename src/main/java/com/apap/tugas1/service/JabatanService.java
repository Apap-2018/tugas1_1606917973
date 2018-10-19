package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.JabatanModel;

//JabatanService

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	Optional <JabatanModel> getJabatanDetailById(Long id);
	List<JabatanModel> getAll();
	JabatanModel updateJabatan(long id, String nama, String deskripsi, double gajiPokok);
	JabatanModel deleteJabatanById(long id);
	
}

