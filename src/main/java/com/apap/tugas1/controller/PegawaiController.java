package com.apap.tugas1.controller;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//PegawaiController

@Controller
public class PegawaiController {

	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> semuaJabatan = jabatanService.getAll();
		List<InstansiModel> semuaInstansi = instansiService.getAll();
		model.addAttribute("semuaJabatan", semuaJabatan);
		model.addAttribute("semuaInstansi", semuaInstansi);
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai", pegawai);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("idInstansi") long id, Model model) {
		InstansiModel instansi = instansiService.getInstansiDetailById(id);
		List<PegawaiModel> listPegawai = instansi.getListPegawai();
		
		PegawaiModel pegawaiTermuda = listPegawai.get(1);
		int umurPegawaiTermuda = LocalDate.now().getYear() - pegawaiTermuda.getTanggalLahir().getYear();
		
		PegawaiModel pegawaiTertua = listPegawai.get(1);
		int umurPegawaiTertua = LocalDate.now().getYear() - pegawaiTertua.getTanggalLahir().getYear();
		
		for (PegawaiModel pegawai : listPegawai) {
			int umurPegawai = LocalDate.now().getYear() - pegawai.getTanggalLahir().getYear();
			if (umurPegawaiTermuda > umurPegawai) {
				pegawaiTermuda = pegawai;
			}
			if (umurPegawaiTertua < umurPegawai) {
				pegawaiTertua = pegawai;
			}
		}
		
		model.addAttribute("instansi", instansi);
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		return "view-pegawai-termuda-tertua";
	}
	
	/**
	public int calculateAge(Date birthDate, LocalDate currentDate) {
		LocalDate birthDateLocal = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if ((birthDateLocal != null) && (currentDate != null)) {
            return Period.between(birthDateLocal, currentDate).getYears();
        } else {
            return 0;
        }
	}
	*/
}
