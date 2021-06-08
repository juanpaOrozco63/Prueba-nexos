package co.prueba.nexos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.prueba.nexos.domain.Usuario;
import co.prueba.nexos.dto.UsuarioDTO;

@Mapper
public interface UsuarioMapper {
	public UsuarioDTO toUsuarioDTO(Usuario usuario);
	public List<UsuarioDTO> toUsuariosDTO (List<Usuario> usuarios);
	public Usuario toUsuario (UsuarioDTO usuarioDTO);
	public List<Usuario> toUsuarios (List<UsuarioDTO>usuariosDTO);
}
