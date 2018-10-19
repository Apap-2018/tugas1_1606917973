package com.apap.tugas1.service;

import java.util.List;
import com.apap.tugas1.model.JabatanModel;

//JabatanService

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	JabatanModel getJabatanDetailById(long id);
	List<JabatanModel> getAll();
	JabatanModel updateJabatan(long id, String nama, String deskripsi, double gajiPokok);
	JabatanModel deleteJabatan(long id);
	
}

