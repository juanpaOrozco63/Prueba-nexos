package co.prueba.nexos.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the cargo database table.
 * 
 */
@Entity
@Table(name="cargo",schema = "public")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cargo_id",unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cargoId;
	
	@Column(name="nombre",nullable = false)
	@NotNull
	@NotEmpty
	@Size(min=3, max = 50)
	private String nombre;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="cargo")
	private List<Usuario> usuarios;

	public Cargo() {
	}

	public Cargo(Integer cargoId, String nombre, List<Usuario> usuarios) {
		super();
		this.cargoId = cargoId;
		this.nombre = nombre;
		this.usuarios = usuarios;
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


}