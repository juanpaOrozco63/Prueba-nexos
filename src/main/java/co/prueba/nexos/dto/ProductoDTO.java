package co.prueba.nexos.dto;



public class ProductoDTO {

	
	private Integer productoId;
	private String codigo;
	private String detalle;
	private String nombre;
	private Integer precio;
	
	public ProductoDTO() {
		
	}
	public ProductoDTO(Integer productoId, String codigo, String detalle, String nombre, Integer precio) {
		super();
		this.productoId = productoId;
		this.codigo = codigo;
		this.detalle = detalle;
		this.nombre = nombre;
		this.precio = precio;
	}
	public Integer getProductoId() {
		return productoId;
	}
	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	
	
}
