package org.example.domain;

import java.util.List;

public interface IPacienteRepository {
    // Abstracao de persistencia para nao acoplar o service ao tipo de armazenamento.
    void salvar(Paciente paciente);
    List<Paciente> listarTodos();
}
