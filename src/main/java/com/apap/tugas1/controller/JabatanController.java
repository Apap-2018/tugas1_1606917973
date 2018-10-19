package com.apap.tugas1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
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
		return "tambahJabatan";
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
	
	/**
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
	public String deletePilot(@PathVariable(value = "id") long id, Model model) {
		pilotService.deletePilot(id);
		return "delete";
	}
	
	@RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "id") long id, Model model) {
		PilotModel pilot = pilotService.getPilotDetailById(id);
		model.addAttribute("pilot", pilot);
		return "update-pilot";
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	private String updatePilotSubmit(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("flyHour") String flyHour, Model model) {
		PilotModel updatedPilot = pilotService.updatePilot(id, name, flyHour);
		model.addAttribute("pilot", updatedPilot);
		return "updated-pilot";
	}
	*/
	
}
