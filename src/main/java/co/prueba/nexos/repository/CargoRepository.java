package co.prueba.nexos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.prueba.nexos.domain.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
	

}
