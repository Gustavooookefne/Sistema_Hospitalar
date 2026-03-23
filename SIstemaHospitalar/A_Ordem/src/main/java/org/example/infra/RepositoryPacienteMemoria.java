package org.example.infra;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.example.domain.IPacienteRepository;
import org.example.domain.Paciente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

<<<<<<< HEAD
@Schema(
        name = "RepositoryPacienteMemoria",
        description = "Implementação de repositório em memória (Volátil). Ideal para testes e simulações, pois os dados são perdidos ao encerrar a aplicação."
)
public class RepositoryPacienteMemoria implements IPacienteRepository {

    @Schema(description = "Lista interna que armazena os objetos Paciente durante a execução.")
=======
/**
 * Camada de Infraestrutura (Infra):
 * É aqui que os "contratos" (interfaces) definidos no Domínio ganham vida.
 * Esta classe é a nossa simulação de Banco de Dados.
 */
public class RepositoryPacienteMemoria implements IPacienteRepository {

    // Repositório em memória para simulação/local de testes.
    // Usamos uma ArrayList (Lista) comum do Java para atuar como a nossa "Tabela do Banco".
    // A lista é 'final' para garantir que a referência do banco de dados não seja apagada ou substituída.
>>>>>>> db9ba66ff9a57e6b787d109f2ad27ec0164b2ba1
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    @Operation(summary = "Adiciona um paciente à lista na memória.")
    public void salvar(Paciente paciente) {
        // Operação simples de INSERT no nosso "banco de dados" falso.
        pacientes.add(paciente);
    }

    @Override
    @Operation(
            summary = "Recupera a lista de pacientes.",
            description = "Retorna uma visão imutável da lista para garantir a integridade dos dados na memória."
    )
    public List<Paciente> listarTodos() {
        // PROGRAMAÇÃO DEFENSIVA:
        // Em vez de devolver a lista original (o que permitiria que outra classe apagasse
        // um paciente do banco acidentalmente usando um .remove()), nós devolvemos uma
        // versão "Somente Leitura" (unmodifiableList) da nossa lista.
        // Assim, quem pedir a lista de pacientes só pode olhar, mas não pode alterar!
        return Collections.unmodifiableList(pacientes);
    }
}