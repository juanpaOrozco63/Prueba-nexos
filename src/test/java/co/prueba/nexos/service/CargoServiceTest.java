package co.prueba.nexos.service;

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
class CargoServiceTest {
	@Autowired
	CargoService cargoService;
	private final static Logger log = LoggerFactory.getLogger(CargoService.class);

	private final static Integer cargoId=1;
	private final static String excepcion = "El cargo con id: "+cargoId;


	@Test
	@Order(1)
	void save() throws Exception {
		log.info("Save");
		Cargo cargo = new Cargo();
		cargo.setNombre("Prueba");
		cargo.setCargoId(cargoId);
		cargoService.save(cargo);
	}
	@Test
	@Order(2)
	void findById() throws Exception {
		log.info("findById");
		Optional<Cargo> cargoOptional = cargoService.findById(cargoId);
		assertTrue(cargoOptional.isPresent(), excepcion+" no existe");
	}
	@Test
	@Order(3)
	void update()  throws Exception{
		log.info("update");
		Optional<Cargo> cargoOptional = cargoService.findById(cargoId);
		assertTrue(cargoOptional.isPresent(), excepcion+" no existe");
		Cargo cargo = cargoOptional.get();
		cargo.setNombre("Administrador");
		cargoService.update(cargo);
	}
	@Test
	@Order(4)
	void delete() throws Exception {
		log.info("delete");
		Optional<Cargo> cargoOptional = cargoService.findById(cargoId);
		assertTrue(cargoOptional.isPresent(), excepcion+" no existe");
		Cargo cargo = cargoOptional.get();
		cargoService.delete(cargo);
	}

}
