package com.apap.tugas1.service;

import java.sql.Date;

import com.apap.tugas1.model.PegawaiModel;

//FlightService

public interface PegawaiService {
	PegawaiModel getPegawaiDetailByNip(String nip);
}
