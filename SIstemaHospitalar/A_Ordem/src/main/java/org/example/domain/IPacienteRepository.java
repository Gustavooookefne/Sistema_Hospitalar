package org.example.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(
        name = "IPacienteRepository",
        description = "Interface de persistência para gestão de dados dos pacientes triados"
)
public interface IPacienteRepository {

    /**
     * Persiste um novo paciente no sistema.
     */
    @Operation(summary = "Salvar paciente", description = "Armazena os dados do paciente no repositório configurado.")
    void salvar(
            @Schema(description = "Objeto do paciente a ser persistido")
            Paciente paciente
    );

    /**
     * Recupera todos os pacientes cadastrados.
     */
    @Operation(summary = "Listar pacientes", description = "Retorna uma lista de todos os pacientes que passaram pela triagem.")
    @ArraySchema(schema = @Schema(implementation = Paciente.class, description = "Lista de pacientes cadastrados"))
    List<Paciente> listarTodos();
}