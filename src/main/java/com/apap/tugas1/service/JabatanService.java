package com.apap.tugas1.service;

import java.util.List;
import com.apap.tugas1.model.JabatanModel;

//JabatanService

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	JabatanModel getJabatanDetailById(long id);
	List<JabatanModel> getAll();
	/**
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel deletePilot(long id);
	PilotModel updatePilot(long id, String name, String flyHour);
	*/

}

