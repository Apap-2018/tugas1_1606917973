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
public class JabatanModel implements Serializable, Comparable<JabatanModel> {
	
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
	
	@Override
	public int compareTo(JabatanModel other) {
	    if (this.id < other.getId()) {
	      return -1;
	    } else if (this.id > other.getId()) {
	      return 1;
	    } else {
	      return 0;
	    }
	 }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JabatanModel)) return false;

        JabatanModel jabatan = (JabatanModel) o;

        if (this.id != jabatan.getId()) return false;

        return true;
    }
	
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
