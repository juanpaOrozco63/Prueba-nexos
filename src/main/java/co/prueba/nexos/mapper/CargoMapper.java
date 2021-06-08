package co.prueba.nexos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.prueba.nexos.domain.Cargo;
import co.prueba.nexos.dto.CargoDTO;

@Mapper
public interface CargoMapper {
	public CargoDTO toCargoDTO(Cargo cargo);
	public List<CargoDTO> toCargosDTO(List<Cargo> cargos);
	public Cargo toCargo(CargoDTO cargoDTO);
	public List<Cargo> toCargos(List<CargoDTO>cargosDTO);
}
