package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//JabatanModel

@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name = "gaji_pokok", nullable = false)
	private double gajiPokok;
	
	
	@OneToMany(mappedBy = "jabatan", fetch = FetchType.LAZY)
	private List<JabatanPegawaiModel> listJabatanPegawai = new ArrayList<JabatanPegawaiModel>();


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNama() {
		return nama;
	}


	public void setNama(String nama) {
		this.nama = nama;
	}


	public String getDeskripsi() {
		return deskripsi;
	}


	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}


	public double getGajiPokok() {
		return gajiPokok;
	}


	public void setGajiPokok(double gajiPokok) {
		this.gajiPokok = gajiPokok;
	}


	public List<JabatanPegawaiModel> getListJabatanPegawai() {
		return listJabatanPegawai;
	}


	public void setListJabatanPegawai(List<JabatanPegawaiModel> listJabatanPegawai) {
		this.listJabatanPegawai = listJabatanPegawai;
	}

	
	
	/**
	@ManyToMany(mappedBy = "jabatan", fetch = FetchType.LAZY)
	public Set<PegawaiModel> getStocks() {
		return this.pegawai;
	}
	*/
	
	/**
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_pegawai", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JabatanPegawaiModel jabatanPegawai;
	*/
	
}
