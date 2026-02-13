package org.example.domain;

public interface IProtocoloAtendimento {

    String priorizar (Paciente paciente);

    public class Paciente {

        String nome;
        public int frequenciaCardica;

        public Paciente (String nome, int frequenciaCardica) {
            this.nome = nome;
            this.frequenciaCardica = frequenciaCardica;
        }
    }
}
