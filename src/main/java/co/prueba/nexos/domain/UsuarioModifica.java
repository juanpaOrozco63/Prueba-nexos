package co.prueba.nexos.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;

import java.util.Date;


/**
 * The persistent class for the usuario_modifica database table.
 * 
 */
@Entity
@Table(name="usuario_modifica",schema = "public")
public class UsuarioModifica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usuario_modifica_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioModificaId;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_modificacion")
	@PastOrPresent
	private Date fechaModificacion;

	//bi-directional many-to-one association to Mercancia
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mercancia_id")
	private Mercancia mercancia;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public UsuarioModifica() {
	}

	public UsuarioModifica(Integer usuarioModificaId, Date fechaModificacion, Mercancia mercancia, Usuario usuario) {
		super();
		this.usuarioModificaId = usuarioModificaId;
		this.fechaModificacion = fechaModificacion;
		this.mercancia = mercancia;
		this.usuario = usuario;
	}

	public Integer getUsuarioModificaId() {
		return this.usuarioModificaId;
	}

	public void setUsuarioModificaId(Integer usuarioModificaId) {
		this.usuarioModificaId = usuarioModificaId;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Mercancia getMercancia() {
		return this.mercancia;
	}

	public void setMercancia(Mercancia mercancia) {
		this.mercancia = mercancia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}