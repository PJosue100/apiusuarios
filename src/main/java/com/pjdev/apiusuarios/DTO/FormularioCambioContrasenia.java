package com.pjdev.apiusuarios.DTO;

import java.io.Serializable;

public class FormularioCambioContrasenia implements Serializable {

    private String contraseniaActual;
    private String contraseniaNueva;


    public FormularioCambioContrasenia(String contraseniaActual, String contraseniaNueva) {
        this.contraseniaActual = contraseniaActual;
        this.contraseniaNueva = contraseniaNueva;
    }

    public String getContraseniaActual() {
        return contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
    }

    public String getContraseniaNueva() {
        return contraseniaNueva;
    }

    public void setContraseniaNueva(String contraseniaNueva) {
        this.contraseniaNueva = contraseniaNueva;
    }

    @Override
    public String toString() {
        return "FormularioCambioContraseña{" +
                "contraseniaActual='" + contraseniaActual + '\'' +
                ", contraseniaNueva='" + contraseniaNueva + '\'' +
                '}';
    }
}
