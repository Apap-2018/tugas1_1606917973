package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//JabatanPegawaiModel

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	long id;
	
	@Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pegawai")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private PegawaiModel pegawai;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jabatan")
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private JabatanModel jabatan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PegawaiModel getPegawai() {
		return pegawai;
	}

	public void setPegawai(PegawaiModel pegawai) {
		this.pegawai = pegawai;
	}

	public JabatanModel getJabatan() {
		return jabatan;
	}

	public void setJabatan(JabatanModel jabatan) {
		this.jabatan = jabatan;
	}
    
    
    
    /**
	@OneToMany(mappedBy = "jabatanPegawai", fetch = FetchType.LAZY)
	private List<JabatanModel> listJabatan = new ArrayList<JabatanModel>();
	
	@OneToMany(mappedBy = "jabatanPegawai", fetch = FetchType.LAZY)
	private List<PegawaiModel> listPegawai = new ArrayList<PegawaiModel>();
	*/
	
}
