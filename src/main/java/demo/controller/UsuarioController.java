package demo.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import demo.dto.UsuarioDTO;
import demo.dto.UsuarioResponseDTO;
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
        public ResponseEntity<Page<UsuarioResponseDTO>> listar(Pageable pageable
        ) throws Exception {

            System.out.print(pageable);

        String machineId = InetAddress.getLocalHost().getHostAddress();

        Page<UsuarioResponseDTO> usuarios = 
            usuarioService.obtenerUsuarios(pageable)
                .map(u -> new UsuarioResponseDTO(
                    u.getId(),
                    u.getNombre(),
                    u.getEmail()
                ));

        return ResponseEntity.ok()
                .header("X-Machine-Id", machineId)
                .body(usuarios);
        }
}
