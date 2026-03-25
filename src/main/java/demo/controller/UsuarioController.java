package demo.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.UsuarioDTO;
import demo.dto.UsuarioResponseDTO;
import demo.model.Usuario;
import demo.model.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> crearUsuario(@Valid @RequestBody UsuarioDTO request) {
        String serverIp = "unknown";
        try {
            serverIp = java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }

        try {
            usuarioService.crearUsuario(request.getNombre(), request.getEmail());
            return ResponseEntity.ok("success - " + serverIp);
        } catch (Exception e) {
            return ResponseEntity.ok("fail - " + serverIp);
        }
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() throws Exception {

        String ip = InetAddress.getLocalHost().getHostAddress();

        return usuarioService.obtenerUsuarios()
                .stream()
                .map((Usuario r) -> new UsuarioResponseDTO(
                        r.getId(),
                        r.getNombre(),
                        r.getEmail(),
                        ip
                ))
                .toList();
    }
}
