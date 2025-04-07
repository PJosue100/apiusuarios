package com.pjdev.apiusuarios.controller;



import com.pjdev.apiusuarios.DTO.FormularioCambioContrasenia;
import com.pjdev.apiusuarios.model.Usuario;
import com.pjdev.apiusuarios.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.obtenerPorEmail(email));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        String token = usuarioService.autenticar(usuario);
        System.out.println(token);
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/new")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.modificarUsuario(id, usuario));
    }

    @PutMapping("/recovery-pass")
    public ResponseEntity<?> recuperarContraseniaUsuario(@RequestBody String email) {
        return ResponseEntity.ok(usuarioService.recuperarContraseniaUsuario(email));
    }

    @PutMapping("/change-pass/{id}")
    public ResponseEntity<?> cambiarContraseniaUsuario(@PathVariable Long id, @RequestBody FormularioCambioContrasenia formularioCambioContrasenia) {
        return ResponseEntity.ok(usuarioService.cambiarContraseniaUsuario(id, formularioCambioContrasenia));
    }

}
