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

import co.prueba.nexos.domain.Cargo;
import co.prueba.nexos.domain.Usuario;
import co.prueba.nexos.domain.UsuarioModifica;
import co.prueba.nexos.dto.CargoDTO;
import co.prueba.nexos.dto.UsuarioDTO;
import co.prueba.nexos.dto.UsuarioModificaDTO;
import co.prueba.nexos.mapper.UsuarioModificadoMapper;
import co.prueba.nexos.service.UsuarioModificaService;

@RestController
@RequestMapping("/api/usuarioModificado")
@CrossOrigin("*")
public class UsuarioModificadoController {
	private  static Logger log = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired
	UsuarioModificaService usuarioModificaService;
	@Autowired
	UsuarioModificadoMapper
	usuarioModificaMapper;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody UsuarioModificaDTO usuarioModificaDTO) throws Exception {

		UsuarioModifica usuarioModifica = usuarioModificaMapper.toUsuarioModifica(usuarioModificaDTO);
		usuarioModifica = usuarioModificaService.save(usuarioModifica);
		usuarioModificaDTO = usuarioModificaMapper.toUsuarioModificaDTO(usuarioModifica);

		return ResponseEntity.ok().body(usuarioModificaDTO);

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody UsuarioModificaDTO usuarioModificaDTO) throws Exception {

		UsuarioModifica usuarioModifica = usuarioModificaMapper.toUsuarioModifica(usuarioModificaDTO);
		usuarioModifica = usuarioModificaService.update(usuarioModifica);
		usuarioModificaDTO = usuarioModificaMapper.toUsuarioModificaDTO(usuarioModifica);

		return ResponseEntity.ok().body(usuarioModificaDTO);


	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception{
		usuarioModificaService.deleteById(id);
			return ResponseEntity.ok().build();
			
			
		
	}
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//Lista de cargos
			List<UsuarioModifica> usuariosModifica=usuarioModificaService.findAll();
			//Declaro arreglo de DTOS
			List<UsuarioModificaDTO> usuariosModificaDTO = usuarioModificaMapper.toUsuariosModificaDTO(usuariosModifica);
			
			return ResponseEntity.ok().body(usuariosModificaDTO);
			
		
	}
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception{
			Optional<UsuarioModifica>usuarioModificaOptional=usuarioModificaService.findById(id);
			if(!usuarioModificaOptional.isPresent()) {
				return ResponseEntity.ok().body("Usuario que modifica no se encontro");
			}
			UsuarioModifica usuarioModifica=usuarioModificaOptional.get();
			UsuarioModificaDTO usuarioModificaDTO=usuarioModificaMapper.toUsuarioModificaDTO(usuarioModifica);
			return ResponseEntity.ok().body(usuarioModificaDTO);
			
			
		
	}
}
