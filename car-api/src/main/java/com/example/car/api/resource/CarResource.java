package com.example.car.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.car.api.model.Car;
import com.example.car.api.repository.CarRepository;
import com.example.car.api.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarResource {

		@Autowired
		private CarRepository carRepository;
		
		@Autowired
		private CarService carService;
		
		@CrossOrigin(maxAge=10)
		@GetMapping
		public List<Car> listar() {
			return carRepository.findAll();
		}
		
		@CrossOrigin(maxAge=10)
		@PostMapping
		public ResponseEntity<Car> criar(@Valid @RequestBody Car car, HttpServletResponse response) {
			Car carSalva = carRepository.save(car);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(carSalva.getCodigo()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(carSalva);
		}
		
		@CrossOrigin(maxAge=10)
		@GetMapping("/{codigo}")
		public ResponseEntity<Car> buscarPeloCodigo(@PathVariable Long codigo) {
			Car car = carRepository.findOne(codigo);
			 return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
		}
		
		@CrossOrigin(maxAge=10)
		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable Long codigo) {
			carRepository.delete(codigo);
		}
		
		@CrossOrigin(maxAge=10)
		@PutMapping("/{codigo}")
		public ResponseEntity<Car> atualizar(@PathVariable Long codigo, @Valid @RequestBody Car car) {
			Car carSalvo = carService.atualizar(codigo, car);
			return ResponseEntity.ok(carSalvo);
		}
		
}
