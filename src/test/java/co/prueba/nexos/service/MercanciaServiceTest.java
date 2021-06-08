package co.prueba.nexos.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
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
import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.domain.Producto;
import co.prueba.nexos.domain.Usuario;
import co.prueba.nexos.repository.CargoRepository;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class MercanciaServiceTest {
	private final static Logger log = LoggerFactory.getLogger(CargoRepository.class);
	@Autowired
	MercanciaService mercanciaService;
	@Autowired
	ProductoService productoService;
	@Autowired
	UsuarioService usuarioService;
	
	
	private final static Integer mercanciaId=3;
	private final static Integer proId=1;
	private final static Integer usuarioId = 1;
	private final static String excepcion = "la mercancia con id: "+mercanciaId;
	private final static String excepcionProducto = "El producto con id: ";
	private final static String excepcionUsuario = "El usuario con id: ";

	@Test
	@Order(1)
	void save() throws Exception {
		log.info("Save");
		Mercancia mercancia = new Mercancia();
		Optional<Producto> producto = productoService.findById(proId);
		Optional<Usuario> usuario = usuarioService.findById(usuarioId);
		Date fecha = new Date();
		mercancia.setNombre("Cargas 2");
		mercancia.setProducto(producto.get());
		mercancia.setCantidad(2);
		mercancia.setFechaIngreso(fecha);
		mercancia.setUsuario(usuario.get());
		mercancia.setTotal(mercancia.getCantidad()* producto.get().getPrecio());
		mercanciaService.save(mercancia);
	}
	
	@Test
	@Order(2)
	void findById() throws Exception {
		log.info("findById");
		Optional<Mercancia> mercanciaOptional = mercanciaService.findById(mercanciaId);
		assertTrue(mercanciaOptional.isPresent(), excepcion+" no existe");
	}
	@Test
	@Order(3)
	void update()  throws Exception{
		log.info("update");
		Optional<Mercancia> mercanciaOptional = mercanciaService.findById(mercanciaId);
		assertTrue(mercanciaOptional.isPresent(), excepcion+" no existe");
		Mercancia mercancia = mercanciaOptional.get();	
		Optional<Producto> producto = productoService.findById(proId);
		Optional<Usuario> usuario = usuarioService.findById(usuarioId);
		Date fecha = new Date();
		mercancia.setNombre("Prueba sin id");
		mercancia.setProducto(producto.get());
		mercancia.setCantidad(2);
		mercancia.setFechaIngreso(fecha);
		mercancia.setUsuario(usuario.get());
		mercancia.setTotal(mercancia.getCantidad()* producto.get().getPrecio());
		mercancia.setMercanciaId(1);
		mercanciaService.update(mercancia);
	}
	@Test
	@Order(4)
	void delete() throws Exception {
		log.info("delete");
		Optional<Mercancia> mercanciaOptional = mercanciaService.findById(mercanciaId);
		assertTrue(mercanciaOptional.isPresent(), excepcion+" no existe");
		Mercancia mercancia = mercanciaOptional.get();
		mercanciaService.delete(mercancia);
	}
	@Test
	@Order(5)
	void deletePorUsuario() throws Exception {
		log.info("delete por usuario");
		Optional<Mercancia> mercanciaOptional = mercanciaService.findById(4);
		assertTrue(mercanciaOptional.isPresent(), excepcion+" no existe");
		Mercancia mercancia = mercanciaOptional.get();
		mercanciaService.eliminarMercanciaPorUsuario(mercancia,1);
	}

}
