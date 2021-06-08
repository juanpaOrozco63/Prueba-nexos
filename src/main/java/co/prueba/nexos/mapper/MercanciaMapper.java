package co.prueba.nexos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.dto.MercanciaDTO;

@Mapper
public interface MercanciaMapper {
	@Mapping(source = "producto.productoId", target = "productoId")
	@Mapping(source = "usuario.usuarioId", target = "usuarioId")
	public MercanciaDTO toMercanciaDTO(Mercancia mercancia);
	public List<MercanciaDTO> toMercanciasDTO (List<Mercancia> mercancias);
	@Mapping(target = "producto.productoId", source = "productoId")
	@Mapping(target = "usuario.usuarioId", source = "usuarioId")
	public Mercancia toMercancia(MercanciaDTO mercanciaDTO);
	public List<Mercancia> toMercancias (List<MercanciaDTO> mercanciasDTO);
}
