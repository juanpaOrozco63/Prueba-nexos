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
import co.prueba.nexos.repository.ProductoRepository;
@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	Validator validator;
	@Autowired
	ProductoRepository productoRepository;
	
	private static String excepcion = "El producto con id: ";

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Producto save(Producto entity) throws Exception {
		validate(entity);
		
		return productoRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Producto update(Producto entity) throws Exception {
		validate(entity);
		if(!productoRepository.existsById(entity.getProductoId())) {
			throw new Exception(excepcion+" no existe");
		}
		return productoRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Producto entity) throws Exception {
		if(entity==null) {
			throw new Exception("El producto no existe");
		}
		if(!productoRepository.existsById(entity.getProductoId())) {
			throw new Exception(excepcion+entity.getProductoId()+" no existe. No se pudo eliminar");
		}
		productoRepository.deleteById(entity.getProductoId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if(id==null) {
			throw new Exception("El id es obligatorio ");
		}
		Optional<Producto> optionalProducto = productoRepository.findById(id);
		if(optionalProducto.isPresent()) {
			delete(optionalProducto.get());
		}else {
			throw new Exception(excepcion+id+" no existe");
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Integer id) throws Exception {
		return productoRepository.findById(id);
	}

	@Override
	public void validate(Producto entity) throws Exception {
		if(entity==null) {
			throw new Exception("El  producto es nulo");
		}
		Set<ConstraintViolation<Producto>> constraintViolation = validator.validate(entity);
		if (!constraintViolation.isEmpty()) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return productoRepository.count();
	}

}
