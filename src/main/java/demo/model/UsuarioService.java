package demo.model;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import demo.dto.UsuarioResponseDTO;
import demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioResponseDTO> obtenerUsuarios(Pageable pageable) {
        return repository.findBy(pageable)
            .stream()
            .map(u -> new UsuarioResponseDTO(
                u.getId(),
                u.getNombre(),
                u.getEmail()
            ))
            .toList();
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