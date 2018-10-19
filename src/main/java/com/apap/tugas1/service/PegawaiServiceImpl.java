package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//PegawaiServiceImpl

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	
	@Autowired
	private PegawaiDb pegawaiDb;

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findByNip(nip).get(0);
	}
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		InstansiModel instansi = pegawai.getInstansi();
		Date tanggalLahir = pegawai.getTanggalLahir();
		String tahunMasuk = pegawai.getTahunMasuk();
		int pegawaiKe = 1;
		
		List<PegawaiModel> listPegawaiNIPMirip = this.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(instansi, tanggalLahir, tahunMasuk);
		if (!listPegawaiNIPMirip.isEmpty()) {
			pegawaiKe = (int) (Long.parseLong(listPegawaiNIPMirip.get(listPegawaiNIPMirip.size()-1).getNip())%100) + 1;
		}
		
		String kodeInstansi = Long.toString(instansi.getId());
		
		String pattern = "dd-MM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String tanggalLahirString = simpleDateFormat.format(tanggalLahir).replaceAll("-", "");
		String pegawaiKeString = pegawaiKe/10 == 0 ? ("0" + Integer.toString(pegawaiKe)) : (Integer.toString(pegawaiKe));
		String nip = kodeInstansi + tanggalLahirString + tahunMasuk + pegawaiKeString;
		
		pegawai.setNip(nip);
		pegawaiDb.save(pegawai);
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir, String tahunMasuk){
		return pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(instansi, tanggalLahir, tahunMasuk);		
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByProvinsi(Long provinsiId) {
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : pegawaiDb.findAll()) {
			if (pegawai.getInstansi().getProvinsi().getId() == provinsiId) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findByInstansi(instansi);
	}
	
	@Override 
	public List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan) {
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : pegawaiDb.findByInstansi(instansi)) {
			if (pegawai.getJabatan().contains(jabatan)) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByProvinsiAndJabatan(Long provinsiId, JabatanModel jabatan){
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : this.getPegawaiByProvinsi(provinsiId)) {
			if (pegawai.getJabatan().contains(jabatan)) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan){
		return pegawaiDb.findByJabatan(jabatan);
	}
	
	@Override
	public void deletePegawaiByNip(String nip) {
		pegawaiDb.deleteByNip(nip);
	}
	
	@Override
	public void updatePegawai(String nip, PegawaiModel newPegawai) {
		PegawaiModel oldPegawai = pegawaiDb.findByNip(nip).get(0);
		int pegawaiKe = 1;
		
		String pattern = "dd-MM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		oldPegawai.setNama(newPegawai.getNama());
		oldPegawai.setTempatLahir(newPegawai.getTempatLahir());
		if (oldPegawai.getInstansi().equals(newPegawai.getInstansi())) {
			String tanggalLahirLama = simpleDateFormat.format(oldPegawai.getTanggalLahir());
			String tanggalLahirBaru = simpleDateFormat.format(newPegawai.getTanggalLahir());
			if (tanggalLahirLama.equals(tanggalLahirBaru)) {
				
				if (oldPegawai.getTahunMasuk().equals(newPegawai.getTahunMasuk())) {
					pegawaiKe = (int) (Long.parseLong(oldPegawai.getNip())%100);
				}
				else {
					oldPegawai.setTahunMasuk(newPegawai.getTahunMasuk());
					List<PegawaiModel> listPegawaiNIPMirip = this.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(oldPegawai.getInstansi(), oldPegawai.getTanggalLahir(), oldPegawai.getTahunMasuk());
					if (!listPegawaiNIPMirip.isEmpty()) {
						pegawaiKe = (int) (Long.parseLong(listPegawaiNIPMirip.get(listPegawaiNIPMirip.size()-1).getNip())%100) + 1;
					}
				}
			
			}
			else {
				oldPegawai.setTanggalLahir(newPegawai.getTanggalLahir());
				oldPegawai.setTahunMasuk(newPegawai.getTahunMasuk());
				List<PegawaiModel> listPegawaiNIPMirip = this.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(oldPegawai.getInstansi(), oldPegawai.getTanggalLahir(), oldPegawai.getTahunMasuk());
				if (!listPegawaiNIPMirip.isEmpty()) {
					pegawaiKe = (int) (Long.parseLong(listPegawaiNIPMirip.get(listPegawaiNIPMirip.size()-1).getNip())%100) + 1;
				}
			}
		
		}
		else {
			oldPegawai.setTanggalLahir(newPegawai.getTanggalLahir());
			oldPegawai.setTahunMasuk(newPegawai.getTahunMasuk());
			oldPegawai.setInstansi(newPegawai.getInstansi());
			List<PegawaiModel> listPegawaiNIPMirip = this.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(oldPegawai.getInstansi(), oldPegawai.getTanggalLahir(), oldPegawai.getTahunMasuk());
			if (!listPegawaiNIPMirip.isEmpty()) {
				pegawaiKe = (int) (Long.parseLong(listPegawaiNIPMirip.get(listPegawaiNIPMirip.size()-1).getNip())%100) + 1;
			}
		}
		
		oldPegawai.setJabatan(newPegawai.getJabatan());
		
		InstansiModel instansi = oldPegawai.getInstansi();
		Date tanggalLahir = oldPegawai.getTanggalLahir();
		String tahunMasuk = oldPegawai.getTahunMasuk();
		
		String kodeInstansi = Long.toString(instansi.getId());
		
		String tanggalLahirString = simpleDateFormat.format(tanggalLahir).replaceAll("-", "");
		String pegawaiKeString = pegawaiKe/10 == 0 ? ("0" + Integer.toString(pegawaiKe)) : (Integer.toString(pegawaiKe));
		String nipBaru = kodeInstansi + tanggalLahirString + tahunMasuk + pegawaiKeString;
		
		oldPegawai.setNip(nipBaru);
		newPegawai.setNip(nipBaru);
	}
	
}
