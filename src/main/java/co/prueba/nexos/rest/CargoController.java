package co.prueba.nexos.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.prueba.nexos.domain.Cargo;
import co.prueba.nexos.dto.CargoDTO;
import co.prueba.nexos.mapper.CargoMapper;
import co.prueba.nexos.service.CargoService;

@RestController
@RequestMapping("/api/cargo")
@CrossOrigin("*")
public class CargoController {
	private  static Logger log = LoggerFactory.getLogger(CargoController.class);

	@Autowired
	CargoService cargoService;

	@Autowired
	CargoMapper cargoMapper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody CargoDTO cargoDTO) throws Exception {

		Cargo cargo = cargoMapper.toCargo(cargoDTO);
		cargo = cargoService.save(cargo);
		cargoDTO = cargoMapper.toCargoDTO(cargo);

		return ResponseEntity.ok().body(cargoDTO);

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody CargoDTO cargoDTO) throws Exception {

		Cargo cargo = cargoMapper.toCargo(cargoDTO);
		cargo = cargoService.update(cargo);
		cargoDTO = cargoMapper.toCargoDTO(cargo);

		return ResponseEntity.ok().body(cargoDTO);

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception{
			cargoService.deleteById(id);
			return ResponseEntity.ok().build();
			
			
		
	}
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//Lista de cargos
			List<Cargo> cargos=cargoService.findAll();
			//Declaro arreglo de DTOS
			List<CargoDTO> cargosDTO = cargoMapper.toCargosDTO(cargos);
			
			return ResponseEntity.ok().body(cargosDTO);
			
		
	}
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception{
			Optional<Cargo>cargoOptional=cargoService.findById(id);
			if(!cargoOptional.isPresent()) {
				return ResponseEntity.ok().body("Cargo no se encontro");
			}
			Cargo cargo=cargoOptional.get();
			CargoDTO cargoDTO=cargoMapper.toCargoDTO(cargo);
			return ResponseEntity.ok().body(cargoDTO);
			
			
		
	}
	
}
