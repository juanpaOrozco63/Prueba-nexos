package co.prueba.nexos.dto;


public class CargoDTO {

	private Integer cargoId;
	private String nombre;
	
	public CargoDTO() {
	}

	public CargoDTO(Integer cargoId, String nombre) {
		super();
		this.cargoId = cargoId;
		this.nombre = nombre;
	}

	public Integer getCargoId() {
		return this.cargoId;
	}

	public void setCargoId(Integer cargoId) {
		this.cargoId = cargoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
