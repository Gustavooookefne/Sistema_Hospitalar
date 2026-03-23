package org.example.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

<<<<<<< HEAD
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
=======
/**
 * ISP (Princípio da Segregação de Interfaces):
 * Esta interface é focada APENAS em persistência (salvar e buscar dados).
 * Nós não misturamos regras de negócio aqui. Quem assinar este contrato
 * não será obrigado a implementar funções médicas que não fazem sentido para um banco de dados.
 */
public interface IPacienteRepository {

    // Abstração de persistência: garante o DIP (Inversão de Dependência) lá no Service.
    // O Service chama o "salvar", mas não faz ideia se vai salvar num TXT, MySQL ou na Memória.
    void salvar(Paciente paciente);

>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
    List<Paciente> listarTodos();
}