package co.prueba.nexos.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="producto",schema = "public")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="producto_id",unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productoId;

	@Column(name="codigo", nullable = false)
	@NotNull
	@NotEmpty
	@Size(min=3, max = 50)
	private String codigo;

	@Column(name="detalle", nullable = false)
	@NotNull
	@NotEmpty
	@Size(min=3, max = 150)
	private String detalle;

	@Column(name="nombre", nullable = false)
	@NotNull
	@NotEmpty
	private String nombre;
	
	@Column(name = "precio", nullable = false)
	@Min(1)
	private Integer precio;

	//bi-directional many-to-one association to Mercancia
	@OneToMany(mappedBy="producto")
	private List<Mercancia> mercancias;

	public Producto() {
	}

	public Producto(Integer productoId, String codigo, String detalle, String nombre, Integer precio,
			List<Mercancia> mercancias) {
		super();
		this.productoId = productoId;
		this.codigo = codigo;
		this.detalle = detalle;
		this.nombre = nombre;
		this.precio = precio;
		this.mercancias = mercancias;
	}

	public Integer getProductoId() {
		return this.productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return this.precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public List<Mercancia> getMercancias() {
		return this.mercancias;
	}

	public void setMercancias(List<Mercancia> mercancias) {
		this.mercancias = mercancias;
	}

	

}