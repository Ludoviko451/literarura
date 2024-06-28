package com.laqf.Litelalura.model;

import lombok.Getter;

@Getter
public enum Idioma {
    en("English"),
    es("Spanish"),
    fr("French"),
    de("German"),
    // Agrega más idiomas según sea necesario
    ;

    private final String nombreCompleto;

    Idioma(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public static Idioma fromAbreviatura(String abreviatura) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.name().equalsIgnoreCase(abreviatura)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no válido: " + abreviatura);
    }
}
