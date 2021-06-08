package co.prueba.nexos.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.prueba.nexos.domain.Cargo;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CargoRepositoryTest {
	private final static Logger log = LoggerFactory.getLogger(CargoRepository.class);
	@Autowired
	CargoRepository cargoRepository;
	private final static Integer id=4;
	private final static String excepcion = "El cargo con id: "+id;

	@Test
	@Order(1)
	void save() {
		log.info("Save");
		Optional<Cargo> cargoOptional = cargoRepository.findById(id);
		assertFalse(cargoOptional.isPresent(), excepcion+" ya existe");
		Cargo cargo = new Cargo();
		cargo.setNombre("Prueba");
		cargoRepository.save(cargo);
	}
	@Test
	@Order(2)
	void findById() {
		log.info("findById");
		Optional<Cargo> cargoOptional = cargoRepository.findById(id);
		assertTrue(cargoOptional.isPresent(), excepcion+" no existe");
	}
	@Test
	@Order(3)
	void update() {
		log.info("update");
		Optional<Cargo> cargoOptional = cargoRepository.findById(id);
		assertTrue(cargoOptional.isPresent(), excepcion+" no existe");
		Cargo cargo = cargoOptional.get();
		cargo.setNombre("Director");
		cargoRepository.save(cargo);
	}
	@Test
	@Order(4)
	void delete() {
		log.info("delete");
		Optional<Cargo> cargoOptional = cargoRepository.findById(id);
		assertTrue(cargoOptional.isPresent(), excepcion+" no existe");
		Cargo cargo = cargoOptional.get();
		cargoRepository.delete(cargo);
	}

}
