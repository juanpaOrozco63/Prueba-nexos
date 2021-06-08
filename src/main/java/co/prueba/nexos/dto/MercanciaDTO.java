package co.prueba.nexos.dto;

import java.util.Date;

public class MercanciaDTO {

	private Integer mercanciaId;
	private Integer cantidad;
	private Date fechaIngreso;
	private String nombre;
	private Integer total;
	private Integer productoId;
	private Integer usuarioId;
	
	public MercanciaDTO() {

	}

	public MercanciaDTO(Integer mercanciaId, Integer cantidad,  Date fechaIngreso,
		 String nombre,  Integer total, Integer productoId,
			Integer usuarioId) {
		super();
		this.mercanciaId = mercanciaId;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.nombre = nombre;
		this.total = total;
		this.productoId = productoId;
		this.usuarioId = usuarioId;
	}

	public Integer getMercanciaId() {
		return mercanciaId;
	}

	public void setMercanciaId(Integer mercanciaId) {
		this.mercanciaId = mercanciaId;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

}
