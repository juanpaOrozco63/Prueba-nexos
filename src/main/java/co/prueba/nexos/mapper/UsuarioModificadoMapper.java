package co.prueba.nexos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.prueba.nexos.domain.UsuarioModifica;
import co.prueba.nexos.dto.UsuarioModificaDTO;
@Mapper
public interface UsuarioModificadoMapper {
	@Mapping(source = "usuario.usuarioId", target = "usuarioId")
	@Mapping(source = "mercancia.mercanciaId", target = "mercanciaId")
	public UsuarioModificaDTO toUsuarioModificaDTO(UsuarioModifica usuarioModifica);
	public List<UsuarioModificaDTO> toUsuariosModificaDTO(List<UsuarioModifica>usuariosModifica);
	@Mapping(target = "usuario.usuarioId", source = "usuarioId")
	@Mapping(target = "mercancia.mercanciaId", source = "mercanciaId")
	public UsuarioModifica toUsuarioModifica(UsuarioModificaDTO usuarioModificaDTO);
	public List<UsuarioModifica> toUsuariosModifica(List<UsuarioModificaDTO>usuariosModificaDTO);

}
