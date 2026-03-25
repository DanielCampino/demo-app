package demo.model;

import java.util.List;

import org.springframework.stereotype.Service;
import demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> obtenerUsuarios() {
        return repository.findAll();
    }

    public Usuario crearUsuario(String nombre, String email) {
        Usuario usuario = new Usuario(nombre, email);
        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }
}