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

import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.domain.Producto;
import co.prueba.nexos.domain.UsuarioModifica;
import co.prueba.nexos.repository.UsuarioModificaRepository;
@Service
public class UsuarioModificaServiceImpl implements UsuarioModificaService{
	@Autowired
	UsuarioModificaRepository usuarioModificaRepository;
	@Autowired
	Validator validator;
	private static String excepcion = "El usuario que modifica con id: ";

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioModifica> findAll() {
		return usuarioModificaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UsuarioModifica save(UsuarioModifica entity) throws Exception {
		validate(entity);
		
		return usuarioModificaRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UsuarioModifica update(UsuarioModifica entity) throws Exception {
		validate(entity);
		if (!usuarioModificaRepository.existsById(entity.getUsuarioModificaId())) {
			throw new Exception(excepcion + entity.getUsuarioModificaId() + " no existe");
		}

		return usuarioModificaRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(UsuarioModifica entity) throws Exception {
		if(entity==null) {
			throw new Exception("El Usuario que modifica no existe");
		}
		if(!usuarioModificaRepository.existsById(entity.getUsuarioModificaId())) {
			throw new Exception(excepcion+entity.getUsuarioModificaId()+" no existe. No se pudo eliminar");
		}
		usuarioModificaRepository.deleteById(entity.getUsuarioModificaId());

		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if(id==null) {
			throw new Exception("El id es obligatorio ");
		}
		Optional<UsuarioModifica> usuarioModificaOptional = usuarioModificaRepository.findById(id);
		if(usuarioModificaOptional.isPresent()) {
			delete(usuarioModificaOptional.get());
		}else {
			throw new Exception(excepcion+id+" no existe");
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UsuarioModifica> findById(Integer id) throws Exception {
		return usuarioModificaRepository.findById(id);
	}

	@Override
	public void validate(UsuarioModifica entity) throws Exception {
		if (entity == null) {
			throw new Exception("El usuario que modifica es nulo");

		}
		Set<ConstraintViolation<UsuarioModifica>> constraintViolation = validator.validate(entity);
		if (!constraintViolation.isEmpty()) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return usuarioModificaRepository.count();
	}

}
