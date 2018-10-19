package com.apap.tugas1.service;

import java.util.Date;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

//PegawaiService

public interface PegawaiService {
	PegawaiModel getPegawaiDetailByNip(String nip);
	void addPegawai(PegawaiModel pegawai);
	void deletePegawaiByNip(String nip);
	void updatePegawai(String nip, PegawaiModel pegawai);
	List<PegawaiModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir, String tahunMasuk);
	List<PegawaiModel> getPegawaiByProvinsi(Long provinsiId);
	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
	List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);
	List<PegawaiModel> getPegawaiByProvinsiAndJabatan(Long provinsiId, JabatanModel jabatan);
	List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan);
	
	/**
	PegawaiModel deletePegawai(long id);
	PegawaiModel updatePegawai(long id, String name, String flyHour);
	*/
}

