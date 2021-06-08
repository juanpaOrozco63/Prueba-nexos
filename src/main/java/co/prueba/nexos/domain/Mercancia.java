package co.prueba.nexos.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the mercancia database table.
 * 
 */
@Entity
@Table(name = "mercancia", schema = "public")
public class Mercancia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "mercancia_id",unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mercanciaId;
	
	@Column(name = "cantidad", nullable = false)
	@Min(1)
	private Integer cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso")
	@PastOrPresent
	private Date fechaIngreso;
	
	@Column(name = "nombre", nullable = false)
	@NotNull
	@NotEmpty
	@Size(min=3, max = 50)
	private String nombre;
	
	@Column(name = "total", nullable = false)
	@Min(1)
	private Integer total;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	// bi-directional many-to-one association to UsuarioModifica
	@OneToMany(mappedBy = "mercancia",cascade = CascadeType.ALL)
	private List<UsuarioModifica> usuarioModificas;

	public Mercancia() {
	}

	public Mercancia(Integer mercanciaId, Integer cantidad, Date fechaIngreso, String nombre, Integer total,
			Producto producto, Usuario usuario, List<UsuarioModifica> usuarioModificas) {
		super();
		this.mercanciaId = mercanciaId;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.nombre = nombre;
		this.total = total;
		this.producto = producto;
		this.usuario = usuario;
		this.usuarioModificas = usuarioModificas;
	}

	public Integer getMercanciaId() {
		return this.mercanciaId;
	}

	public void setMercanciaId(Integer mercanciaId) {
		this.mercanciaId = mercanciaId;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getTotal() {
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioModifica> getUsuarioModificas() {
		return this.usuarioModificas;
	}

	public void setUsuarioModificas(List<UsuarioModifica> usuarioModificas) {
		this.usuarioModificas = usuarioModificas;
	}

}