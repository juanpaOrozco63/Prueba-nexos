package co.prueba.nexos.service;

import java.util.List;

import co.prueba.nexos.domain.Mercancia;
import co.prueba.nexos.domain.Usuario;

public interface MercanciaService extends GenericService<Mercancia, Integer>{
	
	public List<Mercancia>findByName(String nombre);
	public void guardarUsuarioModifica(Usuario usuario,Mercancia mercancia) throws Exception;
	public void eliminarMercanciaPorUsuario(Mercancia mercancia, Integer usuarioId) throws Exception;

}
