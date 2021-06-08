package co.prueba.nexos.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.domain.Producto;
import co.prueba.nexos.domain.Usuario;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class MercanciaRepositoryTest {

	private final static Logger log = LoggerFactory.getLogger(CargoRepository.class);
	@Autowired
	MercanciaRepository mercanciaRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	private final static Integer mercanciaId=2;
	private final static Integer proId=1;
	private final static Integer usuarioId = 1;
	private final static String excepcion = "la mercancia con id: "+mercanciaId;
	private final static String excepcionProducto = "El producto con id: ";
	private final static String excepcionUsuario = "El usuario con id: ";

	@Test
	@Order(1)
	void save() {
		log.info("Save");
		Optional<Mercancia> mercanciaOptional = mercanciaRepository.findById(mercanciaId);
		Optional<Producto> productoOptional = productoRepository.findById(proId);
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
		assertFalse(mercanciaOptional.isPresent(), excepcion+" ya existe");
		assertTrue(productoOptional.isPresent(), excepcionProducto+" no existe");
		assertTrue(usuarioOptional.isPresent(), excepcionUsuario+" no existe");
		Usuario usuario = usuarioOptional.get();
		Producto producto = productoOptional.get();
		Mercancia mercancia = new Mercancia();
		Date fecha = new Date();
		mercancia.setNombre("Prueba");
		mercancia.setProducto(producto);
		mercancia.setCantidad(2);
		mercancia.setFechaIngreso(fecha);
		mercancia.setUsuario(usuario);
		mercancia.setTotal(mercancia.getCantidad()* producto.getPrecio());
		mercanciaRepository.save(mercancia);
	}
	@Test
	@Order(2)
	void findById() {
		log.info("findById");
		Optional<Mercancia> mercanciaOptional = mercanciaRepository.findById(mercanciaId);
		assertTrue(mercanciaOptional.isPresent(), excepcion+" no existe");
	}
	@Test
	@Order(3)
	void update() {
		log.info("update");
		Optional<Mercancia> mercanciaOptional = mercanciaRepository.findById(mercanciaId);
		assertTrue(mercanciaOptional.isPresent(), excepcion+" no existe");
		Mercancia mercancia = mercanciaOptional.get();
		Producto producto = mercancia.getProducto();
		Usuario usuario = mercancia.getUsuario();
		Date fecha = new Date();
		mercancia.setNombre("Prueba 2");
		mercancia.setProducto(producto);
		mercancia.setCantidad(1);
		mercancia.setFechaIngreso(fecha);
		mercancia.setUsuario(usuario);
		mercancia.setTotal(mercancia.getCantidad()*producto.getPrecio());
		mercanciaRepository.save(mercancia);
	}
	@Test
	@Order(4)
	void delete() {
		log.info("delete");
		Optional<Mercancia> mercanciaOptional = mercanciaRepository.findById(5);
		assertTrue(mercanciaOptional.isPresent(), excepcion+" no existe");
		Mercancia mercancia = mercanciaOptional.get();
		mercanciaRepository.delete(mercancia);
	}
	

}
