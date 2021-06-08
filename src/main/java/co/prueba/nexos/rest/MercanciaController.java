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

import co.prueba.nexos.domain.Cargo;
import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.dto.CargoDTO;
import co.prueba.nexos.dto.MercanciaDTO;
import co.prueba.nexos.mapper.MercanciaMapper;
import co.prueba.nexos.service.MercanciaService;

@RestController
@RequestMapping("/api/mercancia")
@CrossOrigin("*")
public class MercanciaController {
	private static Logger log = LoggerFactory.getLogger(MercanciaController.class);

	@Autowired
	MercanciaService mercanciaService;

	@Autowired
	MercanciaMapper mercanciaMapper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody MercanciaDTO mercanciaDTO) throws Exception {

		Mercancia mercancia = mercanciaMapper.toMercancia(mercanciaDTO);
		mercancia = mercanciaService.save(mercancia);
		mercanciaDTO = mercanciaMapper.toMercanciaDTO(mercancia);

		return ResponseEntity.ok().body(mercanciaDTO);

	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody MercanciaDTO mercanciaDTO) throws Exception {

		Mercancia mercancia = mercanciaMapper.toMercancia(mercanciaDTO);
		mercancia = mercanciaService.update(mercancia);
		mercanciaDTO = mercanciaMapper.toMercanciaDTO(mercancia);

		return ResponseEntity.ok().body(mercanciaDTO);

	}

	@DeleteMapping("/delete/{idMercancia}/{idUsuario}")
	public ResponseEntity<?> delete(@PathVariable("idMercancia") Integer idMercancia,
			@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		Optional<Mercancia> mercanciaOptional = mercanciaService.findById(idMercancia);
		Mercancia mercancia = new Mercancia();
		if (mercanciaOptional.isPresent()) {
			mercancia = mercanciaOptional.get();
		}else {
			throw new Exception("Mercanccia no encontrada");
		}
		mercanciaService.eliminarMercanciaPorUsuario(mercancia, idUsuario);
		return ResponseEntity.ok().build();

	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {

		// Lista de cargos
		List<Mercancia> mercancias = mercanciaService.findAll();
		// Declaro arreglo de DTOS
		List<MercanciaDTO> mercanciasDTO = mercanciaMapper.toMercanciasDTO(mercancias);

		return ResponseEntity.ok().body(mercanciasDTO);

	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception {
		Optional<Mercancia> mercanciaOptional = mercanciaService.findById(id);
		if (!mercanciaOptional.isPresent()) {
			return ResponseEntity.ok().body("Mercancia no se encontro");
		}
		Mercancia mercancia = mercanciaOptional.get();
		MercanciaDTO mercanciaDTO = mercanciaMapper.toMercanciaDTO(mercancia);
		return ResponseEntity.ok().body(mercanciaDTO);

	}
}
