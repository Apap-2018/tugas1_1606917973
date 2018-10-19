package com.apap.tugas1.controller;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> semuaJabatan = jabatanService.getAll();
		List<InstansiModel> semuaInstansi = instansiService.getAll();
		model.addAttribute("semuaJabatan", semuaJabatan);
		model.addAttribute("semuaInstansi", semuaInstansi);
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	public String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai", pegawai);
		
		InstansiModel instansiPegawai = pegawai.getInstansi();
		String namaInstansiPegawai = instansiPegawai.getNama() + " - " + instansiPegawai.getProvinsi().getNama();
		model.addAttribute("namaInstansiPegawai", namaInstansiPegawai);
		
		List<JabatanModel> setJabatan = pegawai.getJabatan();
		model.addAttribute("setJabatan", setJabatan);
		
		String gaji = "Rp" + Integer.toString(pegawai.getGaji());
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("idInstansi") long id, Model model) {
		InstansiModel instansi = instansiService.getInstansiDetailById(id).get();
		List<PegawaiModel> listPegawai = instansi.getListPegawai();
		
		PegawaiModel pegawaiTermuda = listPegawai.get(0);
		int umurPegawaiTermuda = LocalDate.now().getYear() - pegawaiTermuda.getTanggalLahir().getYear();
		
		PegawaiModel pegawaiTertua = listPegawai.get(0);
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
		
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		
		InstansiModel instansiPegawaiTermuda = pegawaiTermuda.getInstansi();
		String namaInstansiPegawaiTermuda = instansiPegawaiTermuda.getNama() + " - " + instansiPegawaiTermuda.getProvinsi().getNama();
		model.addAttribute("namaInstansiPegawaiTermuda", namaInstansiPegawaiTermuda);
		
		List<JabatanModel> setJabatanTermuda = pegawaiTermuda.getJabatan();
		model.addAttribute("setJabatanTermuda", setJabatanTermuda);
		
		String gajiTermuda = "Rp" + Integer.toString(pegawaiTermuda.getGaji());
		model.addAttribute("gajiTermuda", gajiTermuda);
		
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		
		InstansiModel instansiPegawaiTertua = pegawaiTertua.getInstansi();
		String namaInstansiPegawaiTertua = instansiPegawaiTertua.getNama() + " - " + instansiPegawaiTertua.getProvinsi().getNama();
		model.addAttribute("namaInstansiPegawaiTertua", namaInstansiPegawaiTertua);
		
		List<JabatanModel> setJabatanTertua = pegawaiTertua.getJabatan();
		model.addAttribute("setJabatanTertua", setJabatanTertua);
		
		String gajiTertua = "Rp" + Integer.toString(pegawaiTertua.getGaji());
		model.addAttribute("gajiTertua", gajiTertua);
		
		return "view-pegawai-termuda-tertua";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	public String tambahPegawai (Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setJabatan(new ArrayList<JabatanModel>());
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		List<InstansiModel> listInstansi = instansiService.getAll();
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		model.addAttribute("tanggalLahir", dateFormat.format(date));
		return "tambah-pegawai";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"addJabatan"})
	private String addRow(@ModelAttribute PegawaiModel pegawai, Model model) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"deleteJabatan"})
	private String deleteRow(@ModelAttribute PegawaiModel pegawai, Model model, HttpServletRequest req) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		Integer rowId =  Integer.valueOf(req.getParameter("deleteJabatan"));
		pegawai.getJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	public String tambahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.addPegawai(pegawai);
		
		model.addAttribute("pegawai", pegawai);
		return "tambahan-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	public String cariPegawai (@RequestParam(value="provinsiId", required = false) Optional<Long> provinsiId, @RequestParam(value="instansiId", required = false) Optional<Long> instansiId, @RequestParam(value="jabatanId", required = false) Optional<Long> jabatanId, Model model) {
		
		ProvinsiModel provinsi = null;
		InstansiModel instansi = null;
		JabatanModel jabatan = null;
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<PegawaiModel> hasilPencarian = null;
		if (provinsiId.isPresent()) {
			provinsi = provinsiService.getProvinsiDetailById(provinsiId.get()).get();
			if (instansiId.isPresent()) {
				instansi = instansiService.getInstansiDetailById(instansiId.get()).get();	
				if (jabatanId.isPresent()) {
					jabatan = jabatanService.getJabatanDetailById(jabatanId.get()).get();	
					hasilPencarian = pegawaiService.getPegawaiByInstansiAndJabatan(instansi, jabatan);
				}
				else {
					hasilPencarian = pegawaiService.getPegawaiByInstansi(instansi);
				}
			}
			else if (jabatanId.isPresent()) {
				jabatan = jabatanService.getJabatanDetailById(jabatanId.get()).get();	
				hasilPencarian = pegawaiService.getPegawaiByProvinsiAndJabatan(provinsiId.get(), jabatan);
			}
			else {
				hasilPencarian = pegawaiService.getPegawaiByProvinsi(provinsiId.get());
			}
		}
		else {
			if (jabatanId.isPresent()) {
				jabatan = jabatanService.getJabatanDetailById(jabatanId.get()).get();	
				hasilPencarian = pegawaiService.getPegawaiByJabatan(jabatan);
			}
		}
		model.addAttribute("provinsi", provinsi);
		model.addAttribute("instansi", instansi);
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("hasilPencarian", hasilPencarian);
		return "cari-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	public String tambahPegawai (@RequestParam String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai", pegawai);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		return "ubah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params={"addJabatan"})
	private String addRowUbah(@ModelAttribute PegawaiModel pegawai, Model model) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		return "ubah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params={"deleteJabatan"})
	private String deleteRowUbah(@ModelAttribute PegawaiModel pegawai, Model model, HttpServletRequest req) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		Integer rowId =  Integer.valueOf(req.getParameter("deleteJabatan"));
		pegawai.getJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAll();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAll();
		model.addAttribute("listProvinsi", listProvinsi);
		return "ubah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	public String ubahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
		String oldNip = pegawai.getNip();
		pegawaiService.updatePegawai(oldNip, pegawai);
		model.addAttribute("pegawai", pegawai);
		return "ubahan-pegawai";
	}
	
}
