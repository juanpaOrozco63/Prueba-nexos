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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.prueba.nexos.domain.Usuario;
import co.prueba.nexos.dto.UsuarioDTO;
import co.prueba.nexos.mapper.UsuarioMapper;
import co.prueba.nexos.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class UsuarioController {
	private static Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioMapper usuarioMapper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {

		Usuario usuario = usuarioMapper.toUsuario(usuarioDTO);
		usuario = usuarioService.save(usuario);
		usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);

		return ResponseEntity.ok().body(usuarioDTO);

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {

		Usuario usuario = usuarioMapper.toUsuario(usuarioDTO);
		usuario = usuarioService.update(usuario);
		usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);

		return ResponseEntity.ok().body(usuarioDTO);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception {
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();

	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {

		// Lista de cargos
		List<Usuario> usuarios = usuarioService.findAll();
		// Declaro arreglo de DTOS
		List<UsuarioDTO> cargosDTO = usuarioMapper.toUsuariosDTO(usuarios);

		return ResponseEntity.ok().body(cargosDTO);

	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.ok().body("Usuario no se encontro");
		}
		Usuario usuario = usuarioOptional.get();
		UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);
		return ResponseEntity.ok().body(usuarioDTO);

	}
}
