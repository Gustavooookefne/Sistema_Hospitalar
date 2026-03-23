package org.example.infra;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.example.domain.IPacienteRepository;
import org.example.domain.Paciente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Schema(
        name = "RepositoryPacienteMemoria",
        description = "Implementação de repositório em memória (Volátil). Ideal para testes e simulações, pois os dados são perdidos ao encerrar a aplicação."
)
public class RepositoryPacienteMemoria implements IPacienteRepository {

    @Schema(description = "Lista interna que armazena os objetos Paciente durante a execução.")
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    @Operation(summary = "Adiciona um paciente à lista na memória.")
    public void salvar(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    @Operation(
            summary = "Recupera a lista de pacientes.",
            description = "Retorna uma visão imutável da lista para garantir a integridade dos dados na memória."
    )
    public List<Paciente> listarTodos() {
        return Collections.unmodifiableList(pacientes);
    }
}