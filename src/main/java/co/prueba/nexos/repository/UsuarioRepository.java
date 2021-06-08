package co.prueba.nexos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.prueba.nexos.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
