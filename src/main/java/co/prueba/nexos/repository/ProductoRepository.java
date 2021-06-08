package co.prueba.nexos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.prueba.nexos.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
