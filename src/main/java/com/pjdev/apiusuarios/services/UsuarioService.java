package com.pjdev.apiusuarios.services;

import com.pjdev.apiusuarios.DTO.FormularioCambioContrasenia;
import com.pjdev.apiusuarios.config.JwtUtil;
import com.pjdev.apiusuarios.model.Usuario;
import com.pjdev.apiusuarios.repository.UsuarioRepository;
import com.pjdev.apiusuarios.utileria.Emails;
import com.pjdev.apiusuarios.utileria.GeneradorClave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;



@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @Autowired
    private Emails emails;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    public Usuario obtenerPorEmail(String email) {

        Usuario user =
         usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        System.out.printf("User"+user.getEmail()+" "+user.getNombres());
        return user;

    }

    public String autenticar(Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPasswordHash()));
        System.out.println("Autenticado con éxito: " + authentication.isAuthenticated());
        return jwtUtil.generateToken(authentication.getName(),authentication.getAuthorities());
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setCreadoEn(Instant.now());
        usuario.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));
        return usuarioRepository.save(usuario);
    }

    public Usuario modificarUsuario(Long id, Usuario usuario) {

        Usuario existente = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setNombres(usuario.getNombres());
        existente.setApellidos(usuario.getApellidos());
        existente.setDireccionEnvio(usuario.getDireccionEnvio());
        existente.setEmail(usuario.getEmail());
        existente.setFechaNacimiento(usuario.getFechaNacimiento());
        existente.setRol(usuario.getRol());

        if (usuario.getPasswordHash() != null && !usuario.getPasswordHash().isEmpty()) {
            existente.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));
        }

        return usuarioRepository.save(existente);
    }



    public String recuperarContraseniaUsuario(String emailUsuario) {
        String resultado = "Error";
        Usuario existente = usuarioRepository.findByEmail(emailUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        try {
            GeneradorClave clave = new GeneradorClave();
            String nuevaContrasenia = clave.generarCadenaAleatoria(8);
            System.out.println(nuevaContrasenia);
            existente.setPasswordHash(passwordEncoder.encode(nuevaContrasenia));
            usuarioRepository.save(existente);


            emails.enviarCorreoHtml("juanchorivera100@gmail.com", "Recuperación de Contrasaña Sport Store ", "<p>Su nueva contraseña para Sport Store es: <strong>"+nuevaContrasenia+"</strong></p>");

            resultado = "Se recuperó la contraseña revise su correo";
        }catch (Exception e) {
            System.out.println(e.getMessage());
            resultado = "Error en la recuperación de contraseña";
        }


        return resultado;
    }



    public String cambiarContraseniaUsuario(Long id, FormularioCambioContrasenia formularioCambioContrasenia) {
        String resultado = "Contraseña actual incorrecta";
        Usuario existente = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        try {
            System.out.println(formularioCambioContrasenia.getContraseniaActual());
            System.out.println(passwordEncoder.encode(formularioCambioContrasenia.getContraseniaActual()));
            System.out.println(existente.getPasswordHash());

            if(passwordEncoder.matches(formularioCambioContrasenia.getContraseniaActual(), existente.getPasswordHash())){
                existente.setPasswordHash(passwordEncoder.encode(formularioCambioContrasenia.getContraseniaNueva()));
                usuarioRepository.save(existente);
                resultado = "Se cambio la contraseña";
            }
        }catch (Exception e) {
            resultado = "Error al procesar la solicitud";
        }
        return resultado;
    }




}

