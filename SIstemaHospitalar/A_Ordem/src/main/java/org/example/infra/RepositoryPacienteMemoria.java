package org.example.infra;

import org.example.domain.IPacienteRepository;
import org.example.domain.Paciente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositoryPacienteMemoria implements IPacienteRepository {
    // Repositorio em memoria para simulacao/local de testes.
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void salvar(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return Collections.unmodifiableList(pacientes);
    }
}
