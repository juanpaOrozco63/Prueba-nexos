package co.prueba.nexos.dto;

import java.util.Date;

public class UsuarioModificaDTO {

	
	private Integer usuarioModificaId;	
	private Date fechaModificacion;	
	private Integer mercanciaId;	
	private Integer usuarioId;
	
	public UsuarioModificaDTO() {
		
	}
	public UsuarioModificaDTO(Integer usuarioModificaId, Date fechaModificacion, Integer mercanciaId, Integer usuarioId) {
		super();
		this.usuarioModificaId = usuarioModificaId;
		this.fechaModificacion = fechaModificacion;
		this.mercanciaId = mercanciaId;
		this.usuarioId = usuarioId;
	}
	
	public Integer getUsuarioModificaId() {
		return usuarioModificaId;
	}
	public void setUsuarioModificaId(Integer usuarioModificaId) {
		this.usuarioModificaId = usuarioModificaId;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public Integer getMercanciaId() {
		return mercanciaId;
	}
	public void setMercanciaId(Integer mercanciaId) {
		this.mercanciaId = mercanciaId;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
	
}
