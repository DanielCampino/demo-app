package demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}