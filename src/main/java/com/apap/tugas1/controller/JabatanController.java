package com.apap.tugas1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.JabatanService;

//JabatanController

@Controller
public class JabatanController {

	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new JabatanModel());
		return "tambah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan) {
		jabatanService.addJabatan(jabatan);
		return "tambah";
	}
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("idJabatan") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		return "view-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	public String deletePilot(@RequestParam("idJabatan") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		for ( JabatanPegawaiModel jp : jabatan.getListJabatanPegawai() ) {
			if (jp.getJabatan().getId() == jabatan.getId()) {
				if (jp.getPegawai() == null) {
					jabatanService.deleteJabatan(id);
					return "hapus";
				}
			}
		}
		return "error-jabatan-hapus";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String updatePilot(@RequestParam("idJabatan") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updatePilotSubmit(@RequestParam("idJabatan") long id, @RequestParam("nama") String nama, @RequestParam("deskripsi") String deskripsi, @RequestParam("gajiPokok") double gajiPokok, Model model) {
		JabatanModel updatedJabatan = jabatanService.updateJabatan(id, nama, deskripsi, gajiPokok);
		model.addAttribute("jabatan", updatedJabatan);
		return "ubahan-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	private String viewAllJabatan(Model model) {
		List<JabatanModel> semuaJabatan = jabatanService.getAll();
		model.addAttribute("semuaJabatan", semuaJabatan);
		return "viewall-jabatan";
	}

	
}
