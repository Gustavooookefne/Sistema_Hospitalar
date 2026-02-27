package org.example.domain;


public class Paciente {

    // Entidade de dominio: representa o paciente triado.
    private final String nome;
    private final int frequenciaCardica;

    public Paciente(String nome, int frequenciaCardiaca) {
        this.nome = nome;
        this.frequenciaCardica = frequenciaCardiaca;

    }

    public String getNome() {
        return nome;
    }

    public int getFrequenciaCardica() {
        return frequenciaCardica;
    }
}
