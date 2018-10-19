package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//PegawaiDb

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
	PegawaiModel findById(long id);
	String deleteByNip(String nip);
	List<PegawaiModel> findByNip(String nip);
	List<PegawaiModel> findByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir, String tahunMasuk);
	List<PegawaiModel> findByInstansi(InstansiModel instansi);
	List<PegawaiModel> findByJabatan(JabatanModel jabatan);

}
