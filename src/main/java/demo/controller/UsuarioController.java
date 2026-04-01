package demo.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.PageResponseDTO;
import demo.dto.UsuarioDTO;
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
        public ResponseEntity<PageResponseDTO> listar(Pageable pageable
        ) throws Exception {
            
        String machineId = InetAddress.getLocalHost().getHostAddress();

        return ResponseEntity.ok(
                new PageResponseDTO(machineId, usuarioService.obtenerUsuarios(pageable)
            ));
        }
}
