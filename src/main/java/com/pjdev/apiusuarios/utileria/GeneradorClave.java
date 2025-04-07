package com.pjdev.apiusuarios.utileria;

import java.security.SecureRandom;


public class GeneradorClave {

    private final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "0123456789"
            + "!@#$%^&*()-_=+[]{}|;:,.<>?/";

    private final SecureRandom random = new SecureRandom();

    public String generarCadenaAleatoria(int longitud) {
        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(CARACTERES.length());
            sb.append(CARACTERES.charAt(index));
        }
        return sb.toString();
    }

}
