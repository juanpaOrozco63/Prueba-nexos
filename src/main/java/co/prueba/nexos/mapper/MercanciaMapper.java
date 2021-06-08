package co.prueba.nexos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.dto.MercanciaDTO;

@Mapper
public interface MercanciaMapper {
	public MercanciaDTO toMercanciaDTO(Mercancia mercancia);
	public List<MercanciaDTO> toMercanciasDTO (List<Mercancia> mercancias);
	public Mercancia toMercancia(MercanciaDTO mercanciaDTO);
	public List<Mercancia> toMercancias (List<MercanciaDTO> mercanciasDTO);
}
