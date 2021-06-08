package co.prueba.nexos.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario",schema = "public")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usuario_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioId;
	
	@Column(name = "edad", nullable = false)
	@Min(1)
	private Integer edad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso", nullable = false)
	@PastOrPresent
	private Date fechaIngreso;
	
	@Column(name="nombre", nullable = false)
	@NotNull
	@NotEmpty
	private String nombre;

	//bi-directional many-to-one association to Mercancia
	@OneToMany(mappedBy="usuario")
	private List<Mercancia> mercancias;

	//bi-directional many-to-one association to Cargo
    @ManyToOne()
	@JoinColumn(name="cargo_id")
	private Cargo cargo;

	//bi-directional many-to-one association to UsuarioModifica
	@OneToMany(mappedBy="usuario",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UsuarioModifica> usuarioModificas;

	public Usuario() {
	}

	public Usuario(Integer usuarioId, Integer edad, Date fechaIngreso, String nombre, List<Mercancia> mercancias,
			Cargo cargo, List<UsuarioModifica> usuarioModificas) {
		super();
		this.usuarioId = usuarioId;
		this.edad = edad;
		this.fechaIngreso = fechaIngreso;
		this.nombre = nombre;
		this.mercancias = mercancias;
		this.cargo = cargo;
		this.usuarioModificas = usuarioModificas;
	}

	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
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

	public List<Mercancia> getMercancias() {
		return this.mercancias;
	}

	public void setMercancias(List<Mercancia> mercancias) {
		this.mercancias = mercancias;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<UsuarioModifica> getUsuarioModificas() {
		return this.usuarioModificas;
	}

	public void setUsuarioModificas(List<UsuarioModifica> usuarioModificas) {
		this.usuarioModificas = usuarioModificas;
	}
	

}