package co.prueba.nexos.dto;

import java.util.Date;

public class UsuarioDTO {

	private Integer usuarioId;
	private Integer edad;
	private Date fechaIngreso;
	private String nombre;
	private Integer cargoId;

	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Integer usuarioId, Integer edad, Date fechaIngreso, String nombre, Integer cargoId) {
		super();
		this.usuarioId = usuarioId;
		this.edad = edad;
		this.fechaIngreso = fechaIngreso;
		this.nombre = nombre;
		this.cargoId = cargoId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCargoId() {
		return cargoId;
	}

	public void setCargoId(Integer cargoId) {
		this.cargoId = cargoId;
	}
	
	
}
