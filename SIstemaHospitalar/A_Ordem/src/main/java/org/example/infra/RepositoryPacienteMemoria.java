package org.example.infra;

import org.example.domain.IPacienteRepository;
import org.example.domain.Paciente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Camada de Infraestrutura (Infra):
 * É aqui que os "contratos" (interfaces) definidos no Domínio ganham vida.
 * Esta classe é a nossa simulação de Banco de Dados.
 */
public class RepositoryPacienteMemoria implements IPacienteRepository {

    // Repositório em memória para simulação/local de testes.
    // Usamos uma ArrayList (Lista) comum do Java para atuar como a nossa "Tabela do Banco".
    // A lista é 'final' para garantir que a referência do banco de dados não seja apagada ou substituída.
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void salvar(Paciente paciente) {
        // Operação simples de INSERT no nosso "banco de dados" falso.
        pacientes.add(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        // PROGRAMAÇÃO DEFENSIVA:
        // Em vez de devolver a lista original (o que permitiria que outra classe apagasse
        // um paciente do banco acidentalmente usando um .remove()), nós devolvemos uma
        // versão "Somente Leitura" (unmodifiableList) da nossa lista.
        // Assim, quem pedir a lista de pacientes só pode olhar, mas não pode alterar!
        return Collections.unmodifiableList(pacientes);
    }
}
