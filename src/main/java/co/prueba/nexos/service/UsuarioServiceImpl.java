package co.prueba.nexos.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.prueba.nexos.domain.Usuario;
import co.prueba.nexos.domain.UsuarioModifica;
import co.prueba.nexos.repository.UsuarioRepository;
@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	Validator validator;
	private static String excepcion = "El usuario con id: ";

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario entity) throws Exception {
		validate(entity);
		if (usuarioRepository.existsById(entity.getUsuarioId())) {
			throw new Exception(excepcion + entity.getUsuarioId() + " ya existe");
		}

		return usuarioRepository.save(entity);
	}

	@Override
	public Usuario update(Usuario entity) throws Exception {
		validate(entity);
		if (!usuarioRepository.existsById(entity.getUsuarioId())) {
			throw new Exception(excepcion + entity.getUsuarioId() + " no existe");
		}

		return usuarioRepository.save(entity);
	}

	@Override
	public void delete(Usuario entity) throws Exception {
		if(entity==null) {
			throw new Exception("El Usuario que modifica no existe");
		}
		if(!usuarioRepository.existsById(entity.getUsuarioId())) {
			throw new Exception(excepcion+entity.getUsuarioId()+" no existe. No se pudo eliminar");
		}
		usuarioRepository.deleteById(entity.getUsuarioId());

		
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		if(id==null) {
			throw new Exception("El id es obligatorio ");
		}
		Optional<Usuario> usuarioModificaOptional = usuarioRepository.findById(id);
		if(usuarioModificaOptional.isPresent()) {
			delete(usuarioModificaOptional.get());
		}else {
			throw new Exception(excepcion+id+" no existe");
		}
		
		
	}

	@Override
	public Optional<Usuario> findById(Integer id) throws Exception {
		return usuarioRepository.findById(id);
	}

	@Override
	public void validate(Usuario entity) throws Exception {
		if (entity == null) {
			throw new Exception("El usuario es nulo");

		}
		Set<ConstraintViolation<Usuario>> constraintViolation = validator.validate(entity);
		if (!constraintViolation.isEmpty()) {
			throw new ConstraintViolationException(constraintViolation);
		}
		
	}

	@Override
	public Long count() {
		return usuarioRepository.count();
	}

}
