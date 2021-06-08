package co.prueba.nexos.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.prueba.nexos.domain.Cargo;
import co.prueba.nexos.repository.CargoRepository;

@Service
public class CargoServiceImpl implements CargoService {
	
	private static String excepcion = "El cargo con id:";
	
	@Autowired
	CargoRepository cargoRepository;

	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> findAll() {
		return cargoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Cargo save(Cargo entity) throws Exception {
		validate(entity);
		
		return cargoRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Cargo update(Cargo entity) throws Exception {
		validate(entity);
		// Si no existe lanza el error
		if (!cargoRepository.existsById(entity.getCargoId())) {
			throw new Exception(excepcion + entity.getCargoId() + " no existe");
		}
		return cargoRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(Cargo entity) throws Exception {
		if(entity==null) {
			throw new Exception("El cargo no existe");

		}
		if (!cargoRepository.existsById(entity.getCargoId())) {
			throw new Exception(excepcion + entity.getCargoId() + " no existe. No se puede eliminar");
		}
		// Valido referencias con otras tablas
		cargoRepository.findById(entity.getCargoId()).ifPresent(cargo -> {
							if (cargo.getUsuarios() != null && cargo.getUsuarios().isEmpty()== false) {
								throw new RuntimeException(
									"El cargo con id: " + entity.getCargoId() + " tiene usuarios no se puede borrar");
							}
				});
		cargoRepository.deleteById(entity.getCargoId());
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		
		if(id==null) {
			throw new Exception("El id es obligatorio");
		}
		Optional<Cargo> optionalCargo = cargoRepository.findById(id);
		if(optionalCargo.isPresent()) {
			delete(optionalCargo.get());
		}else {
			throw new Exception(excepcion+id+" no existe");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cargo> findById(Integer id) throws Exception {
		return cargoRepository.findById(id);
	}

	@Override
	public void validate(Cargo entity) throws Exception {

		if (entity == null) {
			throw new Exception("El cargo es nulo");
		}
		Set<ConstraintViolation<Cargo>> constraintViolation = validator.validate(entity);
		if (!constraintViolation.isEmpty()) {
			throw new ConstraintViolationException(constraintViolation);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return cargoRepository.count();
	}

}
