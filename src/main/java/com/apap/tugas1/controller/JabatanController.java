package com.apap.tugas1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
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
	private String viewJabatan(@RequestParam("idJabatan") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id).get();
		model.addAttribute("jabatan", jabatan);
		return "view-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	public String hapusJabatan(@RequestParam(value = "idJabatan", required = true) long idJabatan, Model model) {
		try {
			jabatanService.deleteJabatanById(idJabatan);
			return "hapus";
		}
		catch (Exception ex) {
			return "error-jabatan-hapus";
		}
		
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String updateJabatan(@RequestParam("idJabatan") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id).get();
		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateJabatanSubmit(@RequestParam("idJabatan") long id, @RequestParam("nama") String nama, @RequestParam("deskripsi") String deskripsi, @RequestParam("gajiPokok") double gajiPokok, Model model) {
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
