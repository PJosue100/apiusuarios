package com.pjdev.apiusuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;


@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Size(max = 100)
    @NotNull
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @NotNull
    @Lob
    @Column(name = "direccion_envio", nullable = false)
    private String direccionEnvio;

    @Size(max = 150)
    @NotNull
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("'cliente'")
    @Column(name = "rol", nullable = false, length = 100)
    private String rol;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "creado_en")
    private Instant creadoEn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(max = 100) @NotNull String getNombres() {
        return nombres;
    }

    public void setNombres(@Size(max = 100) @NotNull String nombres) {
        this.nombres = nombres;
    }

    public @Size(max = 100) @NotNull String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@Size(max = 100) @NotNull String apellidos) {
        this.apellidos = apellidos;
    }

    public @NotNull String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(@NotNull String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public @Size(max = 150) @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Size(max = 150) @NotNull String email) {
        this.email = email;
    }

    public @NotNull LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@NotNull LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public @Size(max = 255) @NotNull String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@Size(max = 255) @NotNull String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public @Size(max = 100) @NotNull String getRol() {
        return rol;
    }

    public void setRol(@Size(max = 100) @NotNull String rol) {
        this.rol = rol;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }
}