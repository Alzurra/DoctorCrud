package com.thiagocostafatec.doctor.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagocostafatec.doctor.dto.DoctorDTO;
import com.thiagocostafatec.doctor.entities.Doctor;
import com.thiagocostafatec.doctor.repositories.DoctorRepository;
import com.thiagocostafatec.doctor.services.exceptions.DataBaseException;
import com.thiagocostafatec.doctor.services.exceptions.ResourceNotFoundException;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository repository;

	@Transactional(readOnly = true)
	public Page<DoctorDTO> findAllPaged(PageRequest pageRequest) {
		Page<Doctor> list = repository.findAll(pageRequest);
		return list.map(x -> new DoctorDTO(x));
	}

	@Transactional(readOnly = true)
	public DoctorDTO findById(Long id) {
		Optional<Doctor> obj = repository.findById(id);
		Doctor entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new DoctorDTO(entity);
	}

	@Transactional
	public DoctorDTO insert(DoctorDTO dto) {
		Doctor entity = new Doctor();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new DoctorDTO(entity);
	}

	@Transactional
	public DoctorDTO update(Long id, DoctorDTO dto) {
		try {
			Doctor entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new DoctorDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id/Doctor not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id/Doctor not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(DoctorDTO dto, Doctor entity) {
		entity.setName(dto.getName());
		entity.setSpecialty(dto.getSpecialty());
		entity.setCpf(dto.getCpf());
		entity.setCrm(dto.getCrm());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
	}
}