package com.example.car.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.car.api.model.Car;
import com.example.car.api.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository pessoaRepository;

	public Car atualizar(Long codigo, Car pessoa) {
		Car pessoaSalva = pessoaRepository.findOne(codigo);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}
	
}