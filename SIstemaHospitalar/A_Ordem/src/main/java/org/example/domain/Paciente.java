package org.example.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Paciente",
        description = "Entidade que representa o paciente a ser triado no sistema hospitalar"
)
public class Paciente {

    @Schema(
            description = "Nome completo do paciente",
            example = "Vinícius Júnior",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private final String nome;

    @Schema(
            description = "Frequência cardíaca medida em batimentos por minuto (BPM)",
            example = "85",
            minimum = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
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