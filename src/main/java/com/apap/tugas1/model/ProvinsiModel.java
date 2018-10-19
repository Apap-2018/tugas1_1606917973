package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.apap.tugas1.model.InstansiModel;

//ProvinsiModel

@Entity
@Table(name = "provinsi")
public class ProvinsiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	int id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Column(name = "presentase_tunjangan", nullable = false)
	private double presentaseTunjangan;
	
	@OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY)
	private List<InstansiModel> listInstansi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public double getPresentaseTunjangan() {
		return presentaseTunjangan;
	}

	public void setPresentaseTunjungan(double presentaseTunjangan) {
		this.presentaseTunjangan = presentaseTunjangan;
	}

	public List<InstansiModel> getListInstansi() {
		return listInstansi;
	}

	public void setListInstansi(List<InstansiModel> listInstansi) {
		this.listInstansi = listInstansi;
	}

	
	
}
