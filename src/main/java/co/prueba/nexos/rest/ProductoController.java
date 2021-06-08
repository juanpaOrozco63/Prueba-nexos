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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.prueba.nexos.domain.Producto;
import co.prueba.nexos.dto.ProductoDTO;
import co.prueba.nexos.mapper.ProductoMapper;
import co.prueba.nexos.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("*")
public class ProductoController {
private  static Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	ProductoMapper productoMapper;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ProductoDTO productoDTO) throws Exception {

		Producto producto = productoMapper.toProducto(productoDTO);
		producto = productoService.save(producto);
		productoDTO = productoMapper.toProductoDTO(producto);

		return ResponseEntity.ok().body(productoDTO);
		

	}
	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ProductoDTO productoDTO) throws Exception {

		Producto producto = productoMapper.toProducto(productoDTO);
		producto = productoService.update(producto);
		productoDTO = productoMapper.toProductoDTO(producto);

		return ResponseEntity.ok().body(productoDTO);
		

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception{
		productoService.deleteById(id);
			return ResponseEntity.ok().build();
			
			
		
	}
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//Lista de cargos
			List<Producto> productos=productoService.findAll();
			//Declaro arreglo de DTOS
			List<ProductoDTO> productosDTO = productoMapper.toProductosDTO(productos);
			
			return ResponseEntity.ok().body(productosDTO);
			
		
	}
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception{
			Optional<Producto>productoOptional=productoService.findById(id);
			if(!productoOptional.isPresent()) {
				return ResponseEntity.ok().body("Producto no se encontro");
			}
			Producto producto=productoOptional.get();
			ProductoDTO productoDTO=productoMapper.toProductoDTO(producto);
			return ResponseEntity.ok().body(productoDTO);
			
			
		
	}
}
