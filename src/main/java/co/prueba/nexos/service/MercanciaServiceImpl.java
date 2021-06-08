package co.prueba.nexos.service;

import java.util.Date;
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
import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.domain.Usuario;
import co.prueba.nexos.domain.UsuarioModifica;
import co.prueba.nexos.repository.MercanciaRepository;

@Service
public class MercanciaServiceImpl implements MercanciaService {
	@Autowired
	MercanciaRepository mercanciaRepository;
	@Autowired
	Validator validator;
	private static String excepcion = "La mercancia con id: ";
	@Autowired
	UsuarioModificaService usuarioModificaService;
	@Override
	@Transactional(readOnly = true)
	public List<Mercancia> findAll() {
		return mercanciaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Mercancia save(Mercancia entity) throws Exception {
		List<Mercancia> mercancias = findByName(entity.getNombre()); 
		if(!mercancias.isEmpty()) {
			throw new Exception("Ya existe una mercancia con el nombre "+ entity.getNombre());
		}

		return mercanciaRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Mercancia update(Mercancia entity) throws Exception {
		if (!mercanciaRepository.existsById(entity.getMercanciaId())) {
			throw new Exception(excepcion + entity.getMercanciaId() + " no existe");
		}
		List<Mercancia> mercancias = findByName(entity.getNombre()); 
		if(!mercancias.isEmpty()) {
			throw new Exception("Ya existe una mercancia con el nombre"+ entity.getNombre());
		}
		Usuario usuario = entity.getUsuario();
		guardarUsuarioModifica(usuario,entity);
		
		return mercanciaRepository.save(entity);
	}
	// Debe recibir la mercancia y el id del usuario que quiere eliminarla
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Mercancia entity) throws Exception {
		if(entity==null) {
			throw new Exception("La mercancia no existe");
		}
		if(!mercanciaRepository.existsById(entity.getMercanciaId())) {
			throw new Exception(excepcion+entity.getMercanciaId()+" no existe. No se pudo eliminar");
		}
		
		mercanciaRepository.deleteById(entity.getMercanciaId());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new Exception("El id es obligatorio");
		}
		Optional<Mercancia> optionalMercancia = mercanciaRepository.findById(id);
		if(optionalMercancia.isPresent()) {
			delete(optionalMercancia.get());
		}else {
			throw new Exception(excepcion+id+" no existe");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Mercancia> findById(Integer id) throws Exception {
		
		return mercanciaRepository.findById(id);
	}

	@Override
	
	public void validate(Mercancia entity) throws Exception {
		if (entity == null) {
			throw new Exception("La mercancia es nulo");

		}
		Set<ConstraintViolation<Mercancia>> constraintViolation = validator.validate(entity);
		if (!constraintViolation.isEmpty()) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return mercanciaRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Mercancia> findByName(String nombre) {
		return mercanciaRepository.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void guardarUsuarioModifica(Usuario usuario,Mercancia mercancia) throws Exception {
		UsuarioModifica usuarioModifica = new UsuarioModifica();
		usuarioModifica.setFechaModificacion(new Date());
		usuarioModifica.setUsuario(usuario);
		usuarioModifica.setMercancia(mercancia);
		usuarioModificaService.save(usuarioModifica);
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eliminarMercanciaPorUsuario(Mercancia mercancia, Integer usuarioId) throws Exception {
		if(mercancia.getUsuario().getUsuarioId()!=usuarioId) {
			throw new Exception("El usuario que quiere eliminar no es el mismo que creo la mercancia");
		}
		delete(mercancia);
	}

}
