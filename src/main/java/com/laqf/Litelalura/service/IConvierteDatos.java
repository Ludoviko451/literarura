package com.laqf.Litelalura.service;

public interface IConvierteDatos {

    <T> T convertir(String json, Class<T> clase);
}
