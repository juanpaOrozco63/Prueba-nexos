package co.prueba.nexos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.prueba.nexos.domain.Mercancia;

public interface MercanciaRepository extends JpaRepository<Mercancia, Integer> {
	//Lista las mercancias que su nombre sea el enviado por parametro
	public List<Mercancia> findByNombre(String nombre);
}
