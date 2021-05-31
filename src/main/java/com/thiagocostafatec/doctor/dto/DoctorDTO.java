package com.thiagocostafatec.doctor.dto;

import java.io.Serializable;
import java.time.Instant;

import com.thiagocostafatec.doctor.entities.Doctor;

public class DoctorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String specialty;
	private String cpf;
	private Integer crm;
	private Double income;
	private Instant birthDate;

	public DoctorDTO() {

	}

	public DoctorDTO(Long id, String name, String specialty, String cpf, Integer crm, Double income,
			Instant birthDate) {

		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.cpf = cpf;
		this.crm = crm;
		this.income = income;
		this.birthDate = birthDate;
	}

	public DoctorDTO(Doctor entity) {
		id = entity.getId();
		name = entity.getName();
		specialty = entity.getSpecialty();
		cpf = entity.getCpf();
		crm = entity.getCrm();
		income = entity.getIncome();
		birthDate = entity.getBirthDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getCrm() {
		return crm;
	}

	public void setCrm(Integer crm) {
		this.crm = crm;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}
}
