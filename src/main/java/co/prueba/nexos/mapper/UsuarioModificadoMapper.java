package co.prueba.nexos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.prueba.nexos.domain.UsuarioModifica;
import co.prueba.nexos.dto.UsuarioModificaDTO;
@Mapper
public interface UsuarioModificadoMapper {
	public UsuarioModificaDTO toUsuarioModificaDTO(UsuarioModifica usuarioModifica);
	public List<UsuarioModificaDTO> toUsuariosModificaDTO(List<UsuarioModifica>usuariosModifica);
	public UsuarioModifica toUsuarioModifica(UsuarioModificaDTO usuarioModificaDTO);
	public List<UsuarioModifica> toUsuariosModifica(List<UsuarioModificaDTO>usuariosModificaDTO);

}
