package org.example.domain;

import java.util.List;

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

    List<Paciente> listarTodos();
}